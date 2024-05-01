package com.exam.ptitexam.controller.admin.ExamResult;

import com.exam.ptitexam.domain.*;
import com.exam.ptitexam.domain.dto.ExamDTO;
import com.exam.ptitexam.domain.dto.ExamResultDTO;
import com.exam.ptitexam.domain.dto.Student;
import com.exam.ptitexam.repository.RoleRepository;
import com.exam.ptitexam.service.ExamResultService;
import com.exam.ptitexam.service.ExamService;
import com.exam.ptitexam.service.QuestionService;
import com.exam.ptitexam.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;


@Controller
public class ExamResultController {

    private final RoleRepository roleRepository;
    private final ExamResultService examResultService;
    private final UserService userService;
    private final ExamService examService;
    private final QuestionService questionService;
    private final HttpServletRequest request;

    public ExamResultController(ExamResultService examResultService,
                                UserService userService,
                                ExamService examService,
                                QuestionService questionService,
                                HttpServletRequest request, RoleRepository roleRepository) {
        this.examResultService = examResultService;
        this.userService = userService;
        this.examService = examService;
        this.questionService = questionService;
        this.request = request;
        this.roleRepository = roleRepository;
    }


    @GetMapping("/doexam/{examId}")
    public String getDoExamPage (Model model, @PathVariable("examId") String examId) {
        Exam foundExam = this.examService.getExamById(examId);
        List<Question> questions = this.questionService.findQuestionByExam(foundExam);
        QuestionListWrapper questionListWrapper = new QuestionListWrapper();
        questionListWrapper.setQuestions(questions);
        model.addAttribute("questionListWrapper", questionListWrapper);
        model.addAttribute("examId", examId);
        model.addAttribute("exam", foundExam);
        return "client/doExam/doExam";
    }

    @PostMapping("/doExam/examResult")
    public String postExamResutl(Model model, @ModelAttribute("questions") QuestionListWrapper questionListWrapper){
        List<Question> questions = questionListWrapper.getQuestions();
        String examId = questions.get(0).getExam().getId();
        Exam exam = this.examService.getExamById(examId);
        Long userId = (Long) this.request.getSession().getAttribute("id");
        User user = this.userService.findFirstById(userId);
        ExamResult examResult = new ExamResult();
        int correctAnswer = 0;
        for (Question question : questions){
            if (question.getCorrectOptionIndex() == question.getSelectedOptionIndex()){
                correctAnswer++;
            }
            // Retrieve the existing question from the database
            Question existingQuestion = this.questionService.findQuestionById(question.getId());
            if (existingQuestion != null) {
                // Update the selected option index
                existingQuestion.setSelectedOptionIndex(question.getSelectedOptionIndex());
                // Save the updated question
                this.questionService.saveAQuestion(existingQuestion);
            }
        }
        double sroce = (double) correctAnswer / questions.size() * 10;
        examResult.setExam(exam);
        examResult.setUser(user);
        examResult.setNumberOfCorrectQuestion(correctAnswer);
        examResult.setScore(sroce);
        examResult.setTimeDoExam(new Timestamp(System.currentTimeMillis()));
        this.examResultService.handleSaveExamResult(examResult);
        model.addAttribute("examResult", examResult);
        return "client/doExam/result";
    }

    @GetMapping("/admin/thongke/student/{userId}")
    public String getExamResultByUserPage (Model model, @PathVariable("userId") long userId) {
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
        return "admin/thongke/detail";
    }

    @GetMapping("/admin/thongke/alluser/examresult")
    public String getThongKePage (Model model) {
        List<ExamResultDTO> examResultDTOS = new ArrayList<>();
        int numberOfExam = this.examService.getAllExam().size();

        Role use = roleRepository.findByName("USER");
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
        model.addAttribute("examResultDTOS", examResultDTOS);
        return "admin/thongke/show";
    }



    @GetMapping("/examresult/{userId}/{examId}")
    public String getExamResultAfterDoExamPage (Model model, @PathVariable("userId") long userId, @PathVariable("examId") String examId) {
        Exam exam = this.examService.getExamById(examId);
        User user = this.userService.findFirstById(userId);
        ExamResult examResult = this.examResultService.findByUserAndExam(user, exam);
        model.addAttribute("examResult", examResult);
        return "client/doExam/result";
    }

    @GetMapping("/answer/{userId}/{examId}")
    public String getAnswerPage(Model model, @PathVariable("examId") String examId, @PathVariable("userId") long userId){
        Exam exam = this.examService.getExamById(examId);
        User user = this.userService.findFirstById(userId);
        ExamResult examResult = this.examResultService.findByUserAndExam(user, exam);
        List<Question> questions = this.questionService.findQuestionByExam(exam);
        QuestionListWrapper questionListWrapper = new QuestionListWrapper();
        questionListWrapper.setQuestions(questions);
        model.addAttribute("examResult", examResult);
        model.addAttribute("questions", questions);
        model.addAttribute("questionListWrapper", questionListWrapper);
        return "client/doExam/answer";
    }


}
