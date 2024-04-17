package com.exam.ptitexam.controller.admin.Exam;

import com.exam.ptitexam.domain.Exam;
import com.exam.ptitexam.domain.Question;
import com.exam.ptitexam.domain.User;
import com.exam.ptitexam.repository.ExamRepository;
import com.exam.ptitexam.repository.QuestionRepository;
import com.exam.ptitexam.service.ExamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/exam")
public class RestExamController {
    private final ExamService examService;


    public RestExamController(ExamService examService) {
        this.examService = examService;

    }

    @GetMapping("/list")
    public List<Exam> getExamList() {
        return examService.getAllExam();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createExam(@RequestBody Exam newExam) {
        Exam createdExam = this.examService.handleSaveExam(newExam);
        if (createdExam != null) {
            // user creation successful, return user details
            return ResponseEntity.ok(createdExam);
        } else {
            // user creation failed, return an error message
            return ResponseEntity.badRequest().body("User creation failed.");


        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateExam(@PathVariable("id") String id, @RequestBody Exam updateExam) {
        Exam exam = this.examService.getExamById(id);
        if(exam != null) {
            exam.setName(updateExam.getName());
            exam.setStatus(updateExam.getStatus());
            exam.setDescription(updateExam.getDescription());
            exam.setType(updateExam.getType());
            Exam updated = this.examService.handleSaveExam(exam);
            return ResponseEntity.ok(updated);
        }else {
            return ResponseEntity.badRequest().body("Exam not found.");
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExam(@PathVariable("id") String id) {
        Exam exam = this.examService.getExamById(id);
        if(exam != null) {
            this.examService.deleteExamById(id);
            return ResponseEntity.ok("Exam deleted successfully.");
        }else {
            return ResponseEntity.badRequest().body("Exam not found.");
        }
    }


}
