package com.dashboard.dati.controller;

import jakarta.servlet.http.HttpServletRequest; // o javax.servlet.http.HttpServletRequest se usi versione precedente
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

    @GetMapping({ "/" })
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("requestURI", request.getRequestURI());
        return "home";
    }
}
