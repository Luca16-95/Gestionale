package com.dashboard.dati.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dashboard.dati.registerDto.RegisterAdminDto;
import com.dashboard.dati.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> registerAdmin(@ModelAttribute RegisterAdminDto dto) {
        boolean success = userService.registerAdmin(dto.getUsername(), dto.getPassword());
        if (success) {
            return ResponseEntity.ok("Admin registrato con successo");
        } else {
            return ResponseEntity.badRequest().body("Username già esistente");
        }
    }

}
