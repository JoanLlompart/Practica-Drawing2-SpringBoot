package com.esliceu.PracticaDrawing2SpringBoot.Controllers;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.Canvas;
import com.esliceu.PracticaDrawing2SpringBoot.Services.CanvasServices;
import com.esliceu.PracticaDrawing2SpringBoot.Services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AllCanvasController {
    @Autowired
    CanvasServices canvasServices;
    @Autowired
    UserService userService;


    @GetMapping("/allCanvas")
    public String showAllCanvas(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        String name = userService.getNameOfUser(email);
        //session.setAttribute("name", name);

        List<Canvas> listCanvas = canvasServices.showAllCanvas();

        model.addAttribute("allCanvas", listCanvas);
        model.addAttribute("email", email);
        model.addAttribute("name", name);

        return "allCanvas";
    }
    @PostMapping("/allCanvas")
    public String deleteCanvas(@RequestParam("id") int id, HttpSession session) {
        String email = (String) session.getAttribute("email");
        userService.setEmail(email);
        if (!canvasServices.deleteCanvasById(id, email)) {
        }
        return "redirect:/allCanvas";
    }
}