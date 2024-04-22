package com.exam.ptitexam.controller.admin.ExamResult;

import com.exam.ptitexam.domain.Exam;
import com.exam.ptitexam.domain.ExamResult;
import com.exam.ptitexam.domain.Question;
import com.exam.ptitexam.domain.User;
import com.exam.ptitexam.repository.ExamRepository;
import com.exam.ptitexam.repository.QuestionRepository;
import com.exam.ptitexam.service.ExamResultService;
import com.exam.ptitexam.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class RestExamResultController {
    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;
    private final HttpServletRequest request;
    private final UserService userService;
    private final ExamResultService examResultService;

    public RestExamResultController(ExamRepository examRepository,
                                    QuestionRepository questionRepository,
                                    HttpServletRequest request,
                                    UserService userService,
                                    ExamResultService examResultService){
        this.examRepository = examRepository;
        this.questionRepository = questionRepository;
        this.request = request;
        this.userService = userService;
        this.examResultService = examResultService;
    }

    @PostMapping("/api/admin/exam/question/doExam/{examId}")
    public ResponseEntity<?> doExam(@RequestBody List<Question> questions, @PathVariable("examId") String examId) {
        try {
            Exam foundExam = this.examRepository.findFirstById(examId);
            for (Question question : questions) {
                question.setExam(foundExam);
            }
            this.questionRepository.saveAll(questions);
            return ResponseEntity.ok("You are complete the exam.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to do exam.");
        }
    }

    @GetMapping("/api/admin/exam/result/{examId}")
    public ResponseEntity<?> getResult(@PathVariable("examId") String examId) {
        try {
            Exam foundExam = this.examRepository.findFirstById(examId);
            List<Question> questions = this.questionRepository.findByExam(foundExam);
            Long userId = (Long) this.request.getSession().getAttribute("id");
            User user = this.userService.findFirstById(userId);
            ExamResult examResult = new ExamResult();
            int correctAnswer = 0;
            for (Question question : questions){
                if (question.getCorrectOptionIndex() == question.getSelectedOptionIndex()){
                    correctAnswer++;
                }
            }
            double sroce = (double) correctAnswer / questions.size() * 10;
            examResult.setExam(foundExam);
            examResult.setUser(user);
            examResult.setNumberOfCorrectQuestion(correctAnswer);
            examResult.setScore(sroce);
            examResult.setTimeDoExam(new Timestamp(System.currentTimeMillis()));
            this.examResultService.handleSaveExamResult(examResult);
            return ResponseEntity.ok(examResult);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get result.");
        }
    }



}
