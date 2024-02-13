package com.esliceu.PracticaDrawing2SpringBoot.Controllers;
import com.esliceu.PracticaDrawing2SpringBoot.Services.LoginOAuthServices;
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

import java.util.Enumeration;

@Controller
//@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    LoginOAuthServices loginOAuthServices;
    @GetMapping("/login")
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
        userService.setEmail(email);
        userService.setPassword(userService.encriptarPassword(password));
        String nameUser=userService.getNameOfUser(email);
        if (loginAttempts == null || loginAttempts < 3) {
            if (userService.validLogin(userService.getEmail(), userService.getPassword())) {
                session.setAttribute("email", userService.getEmail());
                session.setAttribute("loginAttempts", 0);
                session.setAttribute("usuariLogueat", true);
                session.setAttribute("name",nameUser);
                //Redireccio a canvasDraw
                return "redirect:/canvasDraw";
            } else {
                if (loginAttempts == null) {
                    loginAttempts = 1;
                } else {
                    loginAttempts++;
                }
                session.setAttribute("loginAttempts", loginAttempts);
                model.addAttribute("missatgeError", "No se ha pogut fer el login");
                return "login";
            }
        } else if (loginAttempts >= 3 && (System.currentTimeMillis() - lastFailedLoginTime) < 60000) {
            loginAttempts = 1;
            session.setAttribute("loginAttempts", loginAttempts);
            userInPause = false;
        } else {
            userInPause = true;
        }
        if (userInPause) {
            model.addAttribute("missatgeErrorAttempts", "Has pasat el nombre maxim de intents. Per tornar a provar has de esperar 1 minut.");
            return "login";
        } else {
            session.setAttribute("lastFailedLoginTime", System.currentTimeMillis());
            return "login";
        }
    }

    @GetMapping("/logindiscord")
    public String logindiscord() throws Exception {
        return "redirect:" + loginOAuthServices.getDiscordRedirection();
    }

    @GetMapping("/discord/callback")
    public String discordCallback(@RequestParam String code, HttpSession session) throws Exception{
        String email = loginOAuthServices.getDiscordUserEmail(code);
        //String username = loginOAuthServices.getDiscordUserName(code);
        System.out.println("email" + email);
        session.setAttribute("email",email);
        if (email.isEmpty()) return "redirect:/error";
        userService.setEmail(email);
        userService.setPassword("");
        boolean validLogWithDiscord=userService.logWithOauth2();
        if (validLogWithDiscord) {
            System.out.println("Valid login discord");
            session.setAttribute("email", userService.getEmail());
            session.setAttribute("loginAttempts", 0);
            session.setAttribute("usuariLogueat", true);
            session.setAttribute("name", "");
            printSession(session);
            return "redirect:/canvasDraw";
        } else {
            return "login";
        }
    }

    private void printSession(HttpSession session) {
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            Object attributeValue = session.getAttribute(attributeName);
            // Realiza alguna acción con el nombre y el valor del atributo
            System.out.println("(Ojo que en hi ha)Nombre del atributo: " + attributeName);
            System.out.println("(Ojo que en hi ha)Valor del atributo: " + attributeValue);
        }
    }


}
