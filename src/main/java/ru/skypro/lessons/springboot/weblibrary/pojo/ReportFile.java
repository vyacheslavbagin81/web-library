package ru.skypro.lessons.springboot.weblibrary.pojo;

import jakarta.persistence.*;

import java.io.File;

@Entity
@Table(name = "report_files")
public class ReportFile {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    String path;

    public ReportFile(String path) {
        this.path = path;
    }

    public ReportFile() {

    }

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }
}
