package com.exam.ptitexam.controller.admin.Question;

import com.exam.ptitexam.domain.Exam;
import com.exam.ptitexam.domain.Question;
import com.exam.ptitexam.repository.ExamRepository;
import com.exam.ptitexam.repository.QuestionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestQuestionController {
    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;

    public RestQuestionController(ExamRepository examRepository, QuestionRepository questionRepository) {
        this.examRepository = examRepository;
        this.questionRepository = questionRepository;
    }

    @GetMapping("/api/admin/exam/update/question/{examId}")
    public List<Question> getQuestionByExamId(@PathVariable("examId") String examId) {
        Exam foundExam = examRepository.findFirstById(examId);
        return questionRepository.findByExam(foundExam);
    }

    @PostMapping("/api/admin/exam/question/create_question/{examId}")
    public ResponseEntity<String> createQuestions(@RequestBody List<Question> questions, @PathVariable("examId") String examId) {
        try {
            Exam foundExam = examRepository.findFirstById(examId);
            for (Question question : questions) {
                question.setExam(foundExam);
            }
            questionRepository.saveAll(questions);
            return ResponseEntity.ok("Questions saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save questions.");
        }
    }
}
