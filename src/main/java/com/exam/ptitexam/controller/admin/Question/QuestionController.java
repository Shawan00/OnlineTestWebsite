package com.exam.ptitexam.controller.admin.Question;

import com.exam.ptitexam.domain.Exam;
import com.exam.ptitexam.domain.Question;
import com.exam.ptitexam.repository.ExamRepository;
import com.exam.ptitexam.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ExamRepository examRepository;

    @GetMapping("/admin/exam/update/question")
    public String getQuestionPage (Model model){
        List<Question> questions = questionRepository.findAll();
        model.addAttribute("questions", questions);
        model.addAttribute("newQuestion", new Question());
        return "admin/question/show";
    }

    @GetMapping("/admin/exam/question/update_question/{id}/{examId}")
    public String getUpdateQuestionPage (Model model, @PathVariable("id") long id, @PathVariable("examId") String examId) {
        Question foundQuestion = questionRepository.findById(id);
        model.addAttribute("newQuestion", foundQuestion);
        model.addAttribute("examId", examId);
        model.addAttribute("id", id);
        return "admin/question/update";
    }

    @GetMapping("/admin/exam/update/question/{examId}")
    public String getQuestionByExamIdPage (Model model, @PathVariable("examId") String examId) {
        Exam foundExam = examRepository.findFirstById(examId);
        List<Question> questions = questionRepository.findByExam(foundExam);
        model.addAttribute("questions", questions);
        model.addAttribute("examId", examId);
        model.addAttribute("newQuestion", new Question());
        return "admin/question/show";
    }





    @GetMapping("/admin/exam/question/create_question/{examId}")
    public String getCreateQuestionPage (Model model, @PathVariable("examId") String examId) {
        model.addAttribute("examId", examId);
        model.addAttribute("newQuestion", new Question());
        return "admin/question/create";
    }



//    @PostMapping("admin/exam/question/create_question")
//    public String postCreateQuestion (Model model, @ModelAttribute("newQuestion") Question question) {
//        questionRepository.save(question);
//        return "redirect:/admin/question";
//    }

    @PostMapping("/admin/exam/question/create_question/{examId}")
    public String createQuestion (Model model, @RequestBody List<Question> questions, @PathVariable("examId") String examId) {
        model.addAttribute("examId", examId);
        Exam foundExam = examRepository.findFirstById(examId);
        for (Question q : questions) {
            q.setExam(foundExam);
        }
        questionRepository.saveAll(questions);
        return "redirect:/admin/exam/update/question/" + examId;
    }

    @PostMapping("/admin/exam/question/update_question")
    public String putUpdateQuestion (Model model, @ModelAttribute("newQuestion") Question question) {
        Question foundQuestion = questionRepository.findById(question.getId());
        System.out.println("Cau hoi moi: " + question.getQuestionContent());
        System.out.println(foundQuestion);
        if (foundQuestion != null) {
            foundQuestion.setQuestionContent(question.getQuestionContent());
            foundQuestion.setOptionA(question.getOptionA());
            foundQuestion.setOptionB(question.getOptionB());
            foundQuestion.setOptionC(question.getOptionC());
            foundQuestion.setOptionD(question.getOptionD());
            foundQuestion.setCorrectOptionIndex(question.getCorrectOptionIndex());
            questionRepository.save(foundQuestion);
        }
        String examId = question.getExam().getId();
        return "redirect:/admin/exam/update/question/" + examId;

    }

    @GetMapping("/admin/exam/question/delete_question/{id}/{examId}")
    public String getDeleteQuestionPage (Model model, @PathVariable("id") long id, @PathVariable("examId") String examId) {
        model.addAttribute("id", id);
        model.addAttribute("examId", examId);
        model.addAttribute("newQuestion", new Question());
        return "admin/question/delete";
    }

    @PostMapping("/admin/exam/question/delete_question")
    public String deleteQuestion (Model model, @ModelAttribute("newQuestion") Question question){
        questionRepository.deleteById(question.getId());
        return "redirect:/admin/exam/update/question/" + question.getExam().getId();
    }
}
