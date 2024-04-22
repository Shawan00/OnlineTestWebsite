package com.exam.ptitexam.repository;

import com.exam.ptitexam.domain.Exam;
import com.exam.ptitexam.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAll();
    List<Question> findByExam(Exam exam);

    Question findById(long id);
    Void deleteByExam(Exam exam);
}
