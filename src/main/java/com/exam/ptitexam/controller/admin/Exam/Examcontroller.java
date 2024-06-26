package com.exam.ptitexam.controller.admin.Exam;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.exam.ptitexam.domain.Exam;
import com.exam.ptitexam.service.ExamService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class Examcontroller {
    private final ExamService examService;

    public Examcontroller(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping("/admin/exam")
    public String getExamPage(Model model) {
        List<Exam> exams = this.examService.getAllExam();
        model.addAttribute("exams", exams);
        model.addAttribute("newExam", new Exam());
        return "admin/exam/show";
    }

    @GetMapping("/admin/exam/create")
    public String getCreateExam(Model model){
        model.addAttribute("newExam", new Exam());
        return "admin/exam/create";
    }

    @PostMapping("/admin/exam/create")
    public String postCreateExam(Model model, @ModelAttribute("newExam") Exam exam, 
                                @RequestParam("questionFile") MultipartFile file){
        
        this.examService.handleSaveExam(exam);
        this.examService.saveQuestionsToDB(file, exam);
        
        return "redirect:/admin/exam";
    }

    @GetMapping("/admin/exam/update/{id}")
    public String getUpdateExamPage(Model model, @PathVariable("id") String id) {
        Exam exam = this.examService.getExamById(id);
        model.addAttribute("newExam", exam);
        return "admin/exam/update";
    }
    
    @PostMapping("/admin/exam/update")
    public String postUpdateexam(Model model, @ModelAttribute("newExam") Exam exam){
        Exam currentExam = this.examService.getExamById(exam.getId());
        if(currentExam != null){
            currentExam.setName(exam.getName());
            currentExam.setType(exam.getType());
            currentExam.setDescription(exam.getDescription());
            currentExam.setStatus(exam.getStatus());
            currentExam.setNumberOfMinutes(exam.getNumberOfMinutes());
            currentExam.setStartTime(exam.getStartTime());
            currentExam.setEndTime(exam.getEndTime());
            this.examService.handleSaveExam(currentExam);
        }
        return "redirect:/admin/exam";
    }

    @GetMapping("/admin/exam/delete/{id}")
    public String deleteExamPage(Model model, @PathVariable("id") String id){
        model.addAttribute("newExam", this.examService.getExamById(id));
        model.addAttribute("id", id);
        return "admin/exam/delete";
    }

    @PostMapping("/admin/exam/delete")
    public String postDeletEexam(Model model, @ModelAttribute("newExam") Exam exam){

        this.examService.deleteExamById(exam.getId());
        
        return "redirect:/admin/exam";
    }   
    
}
