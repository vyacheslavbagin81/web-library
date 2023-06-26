package ru.skypro.lessons.springboot.weblibrary.pojo;

import jakarta.persistence.*;

import java.io.File;

@Entity
@Table(name = "report_files")
public class ReportFile {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    File file;

    public ReportFile(File file) {
        this.file = file;
    }

    public ReportFile() {

    }

    public int getId() {
        return id;
    }

    public File getFile() {
        return file;
    }
}
