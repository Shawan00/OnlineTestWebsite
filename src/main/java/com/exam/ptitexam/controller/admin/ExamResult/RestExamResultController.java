package com.exam.ptitexam.controller.admin.ExamResult;

import com.exam.ptitexam.domain.*;
import com.exam.ptitexam.domain.dto.ExamDTO;
import com.exam.ptitexam.domain.dto.ExamResultDTO;
import com.exam.ptitexam.domain.dto.Student;
import com.exam.ptitexam.repository.ExamRepository;
import com.exam.ptitexam.repository.QuestionRepository;
import com.exam.ptitexam.repository.RoleRepository;
import com.exam.ptitexam.service.ExamResultService;
import com.exam.ptitexam.service.ExamService;
import com.exam.ptitexam.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RestExamResultController {
    private final ExamService examService;
    private final QuestionRepository questionRepository;
    private final HttpServletRequest request;
    private final UserService userService;
    private final ExamResultService examResultService;
    private final RoleRepository roleRepository;


    public RestExamResultController(ExamService examService,
                                    QuestionRepository questionRepository,
                                    HttpServletRequest request,
                                    UserService userService,
                                    ExamResultService examResultService, RoleRepository roleRepository){
        this.examService = examService;
        this.questionRepository = questionRepository;
        this.request = request;
        this.userService = userService;
        this.examResultService = examResultService;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/api/exam/question/doExam/{examId}")
    public ResponseEntity<?> doExam(@RequestBody List<Question> questions, @PathVariable("examId") String examId) {
        try {
            Exam foundExam = this.examService.getExamById(examId);
            for (Question question : questions) {
                question.setExam(foundExam);
            }
            this.questionRepository.saveAll(questions);
            return ResponseEntity.ok("You are complete the exam.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to do exam.");
        }
    }

    @GetMapping("/api/exam/result/{userId}/{examId}")
    public ResponseEntity<?> getResult(@PathVariable("examId") String examId, @PathVariable("userId") long userId) {
        try {
            Exam foundExam = this.examService.getExamById(examId);
            List<Question> questions = this.questionRepository.findByExam(foundExam);
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

    @GetMapping("/api/admin/thongke/student/{userId}")
    public ResponseEntity<?> getExamResultByUserPage (Model model, @PathVariable("userId") long userId) {
        try {
            User foundUser = this.userService.findFirstById(userId);
            List<Exam> exams = this.examService.getAllExam();
            List<ExamDTO> examDTOS = new ArrayList<>();
            for (Exam exam : exams) {
                List<ExamResult> examResults = this.examResultService.findListExamResultByUserAndExam(foundUser, exam);
                for(ExamResult examResult : examResults){
                    if(exam.getId().equals(examResult.getExam().getId())){
                        String id = exam.getId();
                        String name = exam.getName();
                        double score = examResult.getScore();
                        String status = "Hoàn thành";
                        Timestamp time = examResult.getTimeDoExam();
                        examDTOS.add(new ExamDTO(id, name, score, status, time));
                    }
                }
            }
            Student student = new Student(foundUser.getFullName(), examDTOS);
            model.addAttribute("student", student);
            return ResponseEntity.ok(student);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get exam result.");
        }

    }

    @GetMapping("/api/admin/thongke/alluser/examresult")
    public ResponseEntity<?> getThongKePage (Model model) {
       try{
           List<ExamResultDTO> examResultDTOS = new ArrayList<>();
           int numberOfExam = this.examService.getAllExam().size();

           Role use = this.roleRepository.findByName("USER");
           List<User> allUser = this.userService.findByRole(use);
           for (User user : allUser) {
               List<ExamResult> examResults = this.examResultService.findByUser(user);
               int soLanThamGia = examResults.size();
               double tiLeHoanThanh = (double)soLanThamGia / numberOfExam * 100;
               tiLeHoanThanh = Math.round(tiLeHoanThanh * 100.0) / 100.0;
               List<Double> diem = new ArrayList<>();
               for (ExamResult examResult : examResults) {
                   diem.add(Math.round(examResult.getScore() * 100.0) / 100.0);
               }
               String ten = user.getFullName();
               long userId = user.getId();
               examResultDTOS.add(new ExamResultDTO(userId, ten, soLanThamGia, tiLeHoanThanh, diem));
           }
           return ResponseEntity.ok(examResultDTOS);
       }catch(Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get exam result.");
       }

    }



}
