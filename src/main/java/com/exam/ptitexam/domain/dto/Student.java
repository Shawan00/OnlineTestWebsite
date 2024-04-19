package com.exam.ptitexam.domain.dto;

import java.util.List;

public class Student {
    private String fullName;
    private List<ExamDTO> examDTOS;

    public Student() {
    }

    public Student(String fullName, List<ExamDTO> examDTOS) {
        this.fullName = fullName;
        this.examDTOS = examDTOS;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<ExamDTO> getExamDTOS() {
        return examDTOS;
    }

    public void setExamDTOS(List<ExamDTO> examDTOS) {
        this.examDTOS = examDTOS;
    }
}
