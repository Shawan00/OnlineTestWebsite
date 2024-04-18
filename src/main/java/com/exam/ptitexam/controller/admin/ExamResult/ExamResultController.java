package com.exam.ptitexam.controller.admin.ExamResult;

import com.exam.ptitexam.domain.*;
import com.exam.ptitexam.domain.dto.ExamResultDTO;
import com.exam.ptitexam.repository.RoleRepository;
import com.exam.ptitexam.service.ExamResultService;
import com.exam.ptitexam.service.ExamService;
import com.exam.ptitexam.service.QuestionService;
import com.exam.ptitexam.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
public class ExamResultController {

    private ExamResultService examResultService;
    private UserService userService;
    private ExamService examService;
    private QuestionService questionService;
    @Autowired
    private RoleRepository roleRepository;
    private HttpServletRequest request;

    public ExamResultController(ExamResultService examResultService,
                                UserService userService,
                                ExamService examService,
                                QuestionService questionService,
                                HttpServletRequest request) {
        this.examResultService = examResultService;
        this.userService = userService;
        this.examService = examService;
        this.questionService = questionService;
        this.request = request;
    }


    @GetMapping("/doexam/{examId}")
    public String getDoExamPage (Model model, @PathVariable("examId") String examId) {
        Exam foundExam = this.examService.getExamById(examId);
        List<Question> questions = this.questionService.findQuestionByExam(foundExam);
        QuestionListWrapper questionListWrapper = new QuestionListWrapper();
        questionListWrapper.setQuestions(questions);
        model.addAttribute("questionListWrapper", questionListWrapper);
        model.addAttribute("examId", examId);
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
        }
        double sroce = (double) correctAnswer / questions.size() * 10;
        examResult.setExam(exam);
        examResult.setUser(user);
        examResult.setNumberOfCorrectQuestion(correctAnswer);
        examResult.setScore(sroce);
        this.examResultService.handleSaveExamResult(examResult);
        examResult.setScore(Math.round(sroce * 100.0) / 100.0);
        model.addAttribute("examResult", examResult);
        return "client/doExam/result";
    }

    @GetMapping("/admin/thongke/examresult/{userId}")
    public String getExamResultByUserPage (Model model, @PathVariable("userId") long userId) {
        User foundUser = this.userService.findFirstById(userId);
        List<ExamResult> results = this.examResultService.findByUser(foundUser);
        model.addAttribute("examResultByUser", results);
        return "admin/thongke/byuser";
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
            examResultDTOS.add(new ExamResultDTO(ten, soLanThamGia, tiLeHoanThanh, diem));
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


}
