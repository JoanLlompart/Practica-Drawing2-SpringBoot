package com.esliceu.PracticaDrawing2SpringBoot.Controllers;

import com.esliceu.PracticaDrawing2SpringBoot.Services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class RegisterController {
    @Autowired
    UserService userService;
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }
    @PostMapping("/register")
    public String processRegistration(@RequestParam String name,
                                      @RequestParam String email,
                                      @RequestParam String password,
                                      Model model, HttpServletResponse resp) throws IOException {
        boolean passwordValida = password.length() >= 5;

        if (passwordValida) {
            boolean registrat = userService.registrarUsuari(name, email, password);
            if (registrat) {
                model.addAttribute("missatgeRegistre", "Registre correcte. Benvingut!");
                return "redirect:/login";
                //resp.sendRedirect("login");
            } else {
                model.addAttribute("missatgeError", "Error, comprueba si el usuario ya existe");
                return "register";
            }
        } else {
            model.addAttribute("missatgeError", "La contraseña es demasiado corta, debe tener más de 5 caracteres");
            return "register";
        }
        //return "register";
    }
}
