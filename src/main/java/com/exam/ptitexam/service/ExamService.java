package com.exam.ptitexam.service;

import java.util.List;

import com.exam.ptitexam.domain.Question;
import org.springframework.stereotype.Service;

import com.exam.ptitexam.domain.Exam;
import com.exam.ptitexam.repository.ExamRepository;

@Service
public class ExamService {
    private final ExamRepository examRepository;
    private final QuestionService questionService;
    public ExamService(ExamRepository examRepository,
                       QuestionService questionService) {
        this.examRepository = examRepository;
        this.questionService = questionService;
    }

    public List<Exam> getAllExam() {
        return this.examRepository.findAll();
    }

    public Exam handleSaveExam(Exam exam) {
        return this.examRepository.save(exam);
    }

    public Exam getExamById(String id) {
        return this.examRepository.findFirstById(id);
    }


    public void deleteExamById(String id) {
        this.examRepository.deleteById(id);
    }

    public int countCorrectExam(Exam exam, List<Integer> answers){
        List<Question> questions = this.questionService.findQuestionByExam(exam);
        int count = 0;
        int index = 0;
        for (Question question : questions){
            if (question.getCorrectOptionIndex() == answers.get(index)){
                count++;
            }
            index++;
        }
        return count;
    }

}

