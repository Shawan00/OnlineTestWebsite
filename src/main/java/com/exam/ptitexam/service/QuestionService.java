package com.exam.ptitexam.service;

import com.exam.ptitexam.domain.Exam;
import com.exam.ptitexam.domain.Question;
import com.exam.ptitexam.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> findAllQuestion() {
        return this.questionRepository.findAll();
    }


    public List<Question> findQuestionByExam(Exam exam) {
        return this.questionRepository.findByExam(exam);
    }


    public Question findQuestionById(long id) {
        return this.questionRepository.findById(id);
    }


    public Void deleteQuestionByExam(Exam exam) {
        return this.questionRepository.deleteByExam(exam);
    }

}
