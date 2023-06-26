package ru.skypro.lessons.springboot.weblibrary.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.dto.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary.exceptions.ExceptionNoId;
import ru.skypro.lessons.springboot.weblibrary.pojo.ReportFile;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary.repository.ReportRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


@Service
public class ReportSeviceImpl implements ReportService {
    private final EmployeeRepository employeeRepository;
    private final ReportRepository reportRepository;

    public ReportSeviceImpl(EmployeeRepository employeeRepository, ReportRepository reportRepository) {
        this.employeeRepository = employeeRepository;
        this.reportRepository = reportRepository;
    }

    @Override
    public int downloadFile() throws IOException {
        String fileName = newName();
        ObjectMapper objectMapper = new ObjectMapper();
        List<ReportDTO> report = employeeRepository.getReport();
        // Создаем JSON-строку
        String json = objectMapper.writeValueAsString(report);
        System.out.println(json);
        // сщздаем и записываем файл
        File file = new File("src/report/" + fileName);
        Files.writeString(file.toPath(), json);
        ReportFile reportFile = new ReportFile(file);
        reportRepository.save(reportFile);
        return reportFile.getId();
    }

    @Override
    public ResponseEntity<Resource> getFile(int id) throws IOException, ExceptionNoId {
        ReportFile reportFile = reportRepository.findById(id).orElseThrow(ExceptionNoId::new);
        File file = reportFile.getFile();
        String fileName = file.getName();
        Resource resource = new ByteArrayResource(Files.readAllBytes(file.toPath()));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(resource);
    }

    //    метод для создания нового имени файла
    private String newName() {
        int id = (int) reportRepository.count();
        return "report" + id + ".json";
    }
}
