package com.exam.ptitexam.domain.dto;

import com.exam.ptitexam.domain.Role;

public class UpdateUser {
    private String fullName;
    private Role role;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UpdateUser(String fullName, Role role) {
        this.fullName = fullName;
        this.role = role;
    }
}
