package com.exam.ptitexam.service;

import java.io.IOException;
import java.util.List;

import com.exam.ptitexam.domain.Question;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.exam.ptitexam.domain.Exam;
import com.exam.ptitexam.domain.ExamResult;
import com.exam.ptitexam.repository.ExamRepository;

@Service
public class ExamService {
    private final ExamRepository examRepository;
    private final QuestionService questionService;
    private final ExamResultService examResultService;
    private final uploadFileService uploadFileService;
    public ExamService(ExamRepository examRepository,
                       QuestionService questionService,
                        uploadFileService uploadFileService,
                        ExamResultService examResultService) {
        this.examRepository = examRepository;
        this.questionService = questionService;
        this.uploadFileService = uploadFileService;
        this.examResultService = examResultService;
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
        Exam exam = this.examRepository.findFirstById(id);
        List<Question> questions = this.questionService.findQuestionByExam(exam);
        for(Question question : questions){
            this.questionService.deleteQuestionById(question.getId());
        }

        List<ExamResult> examResults = this.examResultService.findByExam(exam);
        for(ExamResult examResult : examResults){
            this.examResultService.deleteExamResultById(examResult.getId());
        }

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

    public void saveQuestionsToDB(MultipartFile file, Exam exam) {
        List<Question> questions;
        try {
            questions = uploadFileService.getQuestionsDataFromExcel(file.getInputStream());
            for (Question question : questions){
                question.setExam(exam);
            }
            this.questionService.saveQuestion(questions);
        } catch (IOException e) {
            throw new IllegalArgumentException("File is invalid!");
        }
        
    }



}

