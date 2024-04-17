package com.exam.ptitexam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.exam.ptitexam.domain.User;;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
    boolean existsByEmail(String email);

    User findFirstById(Long id);

    User findByEmail(String email);

    User findByFullName(String fullName);
}
