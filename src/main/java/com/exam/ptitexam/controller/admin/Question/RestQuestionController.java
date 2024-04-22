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

    @GetMapping("/api/admin/exam/question/{examId}")
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

    @PutMapping("/api/admin/exam/question/update_question/{id}")
    public ResponseEntity<?> updateQuestions(@RequestBody Question question, @PathVariable("id") long id){
        try {
            Question foundQuestion = this.questionRepository.findById(id);
            if (foundQuestion == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found.");
            }
            else{
                foundQuestion.setQuestionContent(question.getQuestionContent());
                foundQuestion.setOptionA(question.getOptionA());
                foundQuestion.setOptionB(question.getOptionB());
                foundQuestion.setOptionC(question.getOptionC());
                foundQuestion.setOptionD(question.getOptionD());
                foundQuestion.setCorrectOptionIndex(question.getCorrectOptionIndex());
                this.questionRepository.save(foundQuestion);
                return ResponseEntity.ok("Questions updated successfully.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update questions.");
        }
    }

    @DeleteMapping("/api/admin/exam/question/delete_question/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") long id) {
        try {
            Question foundQuestion = this.questionRepository.findById(id);
            if (foundQuestion == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found.");
            }
            else {
                this.questionRepository.delete(foundQuestion);
                return ResponseEntity.ok("Question deleted successfully.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete question.");
        }
    }

}
