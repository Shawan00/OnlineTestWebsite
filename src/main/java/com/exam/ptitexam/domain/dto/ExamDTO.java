package com.exam.ptitexam.domain.dto;

import java.time.LocalDateTime;

public class ExamDTO {
    private String id;
    private String name;
    private double score;
    private String status;
    private String localDateTime;

    public ExamDTO() {
    }

    public ExamDTO(String id, String name, double score, String status, String localDateTime) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.status = status;
        this.localDateTime = localDateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }
}
