package com.jongyeon.employee.controller;

import com.jongyeon.employee.domain.Admin;
import com.jongyeon.employee.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin")
    public ResponseEntity<?> create(@RequestBody Admin resource){
        adminService.create(resource);
        return null;
    }

}
