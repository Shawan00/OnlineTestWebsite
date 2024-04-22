package com.exam.ptitexam.service;

import com.exam.ptitexam.domain.Exam;
import com.exam.ptitexam.domain.ExamResult;
import com.exam.ptitexam.domain.User;
import com.exam.ptitexam.repository.ExamResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamResultService {
    private final ExamResultRepository examResultRepository;

    public ExamResultService(ExamResultRepository examResultRepository) {
        this.examResultRepository = examResultRepository;
    }

    public ExamResult handleSaveExamResult(ExamResult examResult) {
        return this.examResultRepository.save(examResult);
    }

    public List<ExamResult> findByUser(User user){
        return this.examResultRepository.findByUser(user);
    }

    public List<ExamResult> findByExam(Exam exam){
        return this.examResultRepository.findByExam(exam);
    }

    public ExamResult findByUserAndExam(User user, Exam exam){
        return this.examResultRepository.findFirstByUserAndExamOrderByTimeDoExamDesc(user, exam);
    }

    public List<ExamResult> findListExamResultByUserAndExam(User user, Exam exam){
        return this.examResultRepository.findByUserAndExam(user, exam);
    }

}
