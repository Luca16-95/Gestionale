package com.dashboard.dati.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dashboard.dati.model.User;
import com.dashboard.dati.model.enums.Role;
import com.dashboard.dati.registerDto.UserDTO;
import com.dashboard.dati.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminDashboard() {
        return "admin-dashboard";
    }

    @GetMapping("/register-user")
    @PreAuthorize("hasRole('ADMIN')")
    public String showUserRegistrationForm() {
        return "register-user";
    }

    @PostMapping("/register-user")
    @PreAuthorize("hasRole('ADMIN')")
    public String registerNewUser(@ModelAttribute UserDTO userDTO, Model model) {
        Optional<Role> role = parseRole(userDTO.getRole());
        if (role.isEmpty()) {
            model.addAttribute("error", "Ruolo non valido");
            return "register-user"; // torna al form con messaggio d'errore
        }

        boolean created = userService.registerUser(userDTO.getUsername(), userDTO.getPassword(), role.get());
        if (!created) {
            model.addAttribute("error", "Username già esistente");
            return "register-user";
        }

        return "redirect:/admin/users";
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users-list";
    }

    private Optional<Role> parseRole(String roleString) {
        try {
            return Optional.of(Role.valueOf(roleString.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

}
