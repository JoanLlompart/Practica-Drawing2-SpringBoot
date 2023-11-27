package com.esliceu.PracticaDrawing2SpringBoot.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping
    public String redirectToLogin() {
        return "redirect:/login";
    }
}
