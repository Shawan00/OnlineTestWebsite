package com.exam.ptitexam.service;


import java.util.List;

import com.exam.ptitexam.domain.ExamResult;
import org.springframework.stereotype.Service;

import com.exam.ptitexam.domain.Role;
import com.exam.ptitexam.domain.User;
import com.exam.ptitexam.domain.dto.RegisterDTO;
import com.exam.ptitexam.repository.RoleRepository;
import com.exam.ptitexam.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository UserRepository;
    private final RoleRepository roleRepository;
    private final ExamResultService examResultService;

    public UserService(UserRepository UserRepository,
                       RoleRepository roleRepository,
                       ExamResultService examResultService){
        this.UserRepository = UserRepository;
        this.roleRepository = roleRepository;
        this.examResultService = examResultService;
    }

    public List<User> getAllUser() {
        return UserRepository.findAll();
    }

    public User handleSaveUser(User user) {
        return this.UserRepository.save(user);
    }

    public User getUserById(Long id) {
        return this.UserRepository.findFirstById(id);
    }

    public void deleteUserById(Long id) {
        User user = this.UserRepository.findFirstById(id);
        List<ExamResult> examResults = this.examResultService.findByUser(user);
        for(ExamResult examResult : examResults){
            this.examResultService.deleteExamResultById(examResult.getId());
        }
        this.UserRepository.deleteById(id);
    }

    public Role getRoleByName(String name){
        return this.roleRepository.findByName(name);
    }

    public User registerDTOtoUser(RegisterDTO registerDTO){
        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        user.setFullName(registerDTO.getFullName());
        return user;
    
    }

    public boolean checkEmailExist(String email){
        return this.UserRepository.existsByEmail(email);
    }

    public User getUserByEmail(String email){
        return this.UserRepository.findByEmail(email);
    }

    public User findByUsername(String fullName){
        return this.UserRepository.findByFullName(fullName);
    }

    public User findFirstById(Long id){
        return this.UserRepository.findFirstById(id);
    }

    public List<User> findByRole(Role role) {
        return this.UserRepository.findByRole(role);
    }
}
