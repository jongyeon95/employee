package com.jongyeon.employee.service;

import com.jongyeon.employee.domain.Admin;
import com.jongyeon.employee.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AdminService {

    private AdminRepository adminRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    AdminService(AdminRepository adminRepository,PasswordEncoder passwordEncoder){
        this.adminRepository=adminRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public Admin create(Admin admin){
        Admin newAdmin=Admin.builder()
                        .name(admin.getName())
                        .email(admin.getEmail())
                        .createdTime(LocalDate.now())
                        .updatedTime(LocalDate.now())
                        .password(passwordEncoder.encode(admin.getPassword()))
                .build();
        return adminRepository.save(newAdmin);
    }

}
