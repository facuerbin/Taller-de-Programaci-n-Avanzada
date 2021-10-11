package com.tpa.videogames.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class Controlador {
    @GetMapping(value = "/")
    public RedirectView index(Model model) {
        String saludo = "Hola Thymeleaf";
        model.addAttribute("saludo", saludo);
        return new RedirectView("inicio");
        // return "index";
    }
}
