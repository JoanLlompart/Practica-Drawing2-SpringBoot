package com.esliceu.PracticaDrawing2SpringBoot.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    public String processRegistration(@RequestParam String name,
                                      @RequestParam String email,
                                      @RequestParam String password,
                                      Model model) {
        boolean passwordValida = password.length() >= 5;
       /* if (passwordValida) {
        UserService userService = new UserService();
        boolean registrat = userService.registrarUsuari(name, email, password);

        if (registrat) {
            model.addAttribute("missatgeRegistre", "Registre correcte. Benvingut!");
            return "redirect:/";
        } else {
            model.addAttribute("missatgeError", "Error, comprueba si el usuario ya existe");
            return "register";
        }
    } else {
        model.addAttribute("missatgeError", "La contraseña es demasiado corta, debe tener más de 5 caracteres");
        return "register";
    }

         */
            return "register";
        }
    }
