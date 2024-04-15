package com.exam.ptitexam.controller.admin.ExamResult;

import com.exam.ptitexam.domain.Exam;
import com.exam.ptitexam.domain.ExamResult;
import com.exam.ptitexam.domain.User;
import com.exam.ptitexam.repository.ExamRepository;
import com.exam.ptitexam.repository.ExamResultRepository;
import com.exam.ptitexam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ExamResultController {

    @Autowired
    private ExamResultRepository examResultRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExamRepository examRepository;


    @GetMapping("/admin/thongke/examresult/{userId}")
    public String getExamResultByUserPage (Model model, @PathVariable("userId") long userId) {
        User foundUser = userRepository.findFirstById(userId);
        List<ExamResult> results = examResultRepository.findByUser(foundUser);
        model.addAttribute("examResultByUser", results);
        return "admin/thongke/byuser";
    }

    @GetMapping("/admin/thongke/alluser/examresult")
    public String getThongKePage (Model model) {
        List<User> allUser = userRepository.findAll();
        HashMap<User, List<ExamResult>> allUserAndResult = new HashMap<>();
        for (User user : allUser) {
            List<ExamResult> examResults = examResultRepository.findByUser(user);
            allUserAndResult.put(user, examResults);
        }
        model.addAttribute(allUserAndResult);
        return "admin/thongke/show";
    }

    @PostMapping("/doexam/examresult/{userId}/{examId}")
    public String postExamResutl(@RequestBody ExamResult examResult, @PathVariable("userId") long userId, @PathVariable("examId") String examId) {
        Exam exam = examRepository.findFirstById(examId);
        User user = userRepository.findFirstById(userId);
        examResult.setExam(exam);
        examResult.setUser(user);
        examResultRepository.save(examResult);
        return "redirect:/examresult/" + userId + "/" + examId;
    }

    @GetMapping("/examresult/{userId}/{examId}")
    public String getExamResultAfterDoExamPage (Model model, @PathVariable("userId") long userId, @PathVariable("examId") String examId) {
        Exam exam = examRepository.findFirstById(examId);
        User user = userRepository.findFirstById(userId);
        ExamResult examResult = examResultRepository.findByUserAndExam(user, exam);
        model.addAttribute("examResult", examResult);
        return "client/doExam/result";
    }
}
