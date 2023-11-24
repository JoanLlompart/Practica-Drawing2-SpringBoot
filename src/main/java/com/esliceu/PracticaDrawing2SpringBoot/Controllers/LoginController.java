package com.esliceu.PracticaDrawing2SpringBoot.Controllers;

import com.esliceu.PracticaDrawing2SpringBoot.Services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService userService;
    @GetMapping
    public String showLoginForm(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(
            HttpSession session,
            Model model,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpServletResponse resp
    ) {
        Integer loginAttempts = (Integer) session.getAttribute("loginAttempts");
        Long lastFailedLoginTime = (Long) session.getAttribute("lastFailedLoginTime");
        boolean userInPause = false;

        userService.registrarUsuari("Joan", "joan@gmail.com", "hola1");
        userService.registrarUsuari("Aina", "aina@gmail.com", "hola1");
        userService.setEmail(email);
        userService.setPassword(userService.encriptarPassword(password));

        if (loginAttempts == null || loginAttempts < 3) {
            if (userService.validLogin(email, password)) {
                session.setAttribute("email", userService.getEmail());
                session.setAttribute("loginAttempts", 0);
                session.setAttribute("usuariLogueat", true);
                return "redirect:/dibuixar"; // redirige a la URL '/dibuixar'
            } else {
                if (loginAttempts == null) {
                    loginAttempts = 1;
                } else {
                    loginAttempts++;
                }
                session.setAttribute("loginAttempts", loginAttempts);
                model.addAttribute("missatgeError", "No se ha pogut fer el login");
                return "login"; // nombre del archivo JSP asociado (login.jsp)
            }
        } else if (loginAttempts >= 3 && (System.currentTimeMillis() - lastFailedLoginTime) > 60000) {
            loginAttempts = 1;
            session.setAttribute("loginAttempts", loginAttempts);
            userInPause = false;
        } else {
            userInPause = true;
        }

        if (userInPause) {
            model.addAttribute("missatgeError", "Has pasat el nombre maxim de intents. Per tornar a provar has de esperar 1 minut.");
            return "login"; // nombre del archivo JSP asociado (login.jsp)
        } else {
            session.setAttribute("lastFailedLoginTime", System.currentTimeMillis());
            return "login"; // nombre del archivo JSP asociado (login.jsp)
        }
    }

}
