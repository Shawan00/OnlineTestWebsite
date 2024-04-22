package com.exam.ptitexam.service;

import com.exam.ptitexam.domain.Exam;
import com.exam.ptitexam.domain.Question;
import com.exam.ptitexam.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> findAllQuestion() {
        return this.questionRepository.findAll();
    }


    public List<Question> findQuestionByExam(Exam exam) {
        return this.questionRepository.findByExam(exam);
    }


    public Question findQuestionById(long id) {
        return this.questionRepository.findById(id);
    }


    public Void deleteQuestionByExam(Exam exam) {
        return this.questionRepository.deleteByExam(exam);
    }

    public void saveQuestion(List<Question> questions) {
        this.questionRepository.saveAll(questions);
    }

    public void saveAQuestion(Question question) {
        this.questionRepository.save(question);
    }

//    public Question saveOrUpdate(Question question) {
//        if (question.getId() != null) {
//            Question existingQuestion = this.questionRepository.findById(question.getId());
//            if(existingQuestion != null) {
//                existingQuestion.setQuestionContent(question.getQuestionContent());
//                existingQuestion.setOptionA(question.getOptionA());
//                existingQuestion.setOptionB(question.getOptionB());
//                existingQuestion.setOptionC(question.getOptionC());
//                existingQuestion.setOptionD(question.getOptionD());
//                existingQuestion.setExam(question.getExam());
//                return questionRepository.save(existingQuestion);
//            }
//        }
//        return questionRepository.save(question);
//    }

}
