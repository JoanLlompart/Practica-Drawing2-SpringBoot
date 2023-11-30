package com.esliceu.PracticaDrawing2SpringBoot.Controllers;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.Canvas;
import com.esliceu.PracticaDrawing2SpringBoot.Exceptions.NotYourCanvasException;
import com.esliceu.PracticaDrawing2SpringBoot.Services.CanvasServices;
import com.esliceu.PracticaDrawing2SpringBoot.Services.UserService;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModifyController {
    @Autowired
    CanvasServices canvasServices;
    @Autowired
    UserService userService;
    @GetMapping("/modify")
    public String getModifyCanvas(@RequestParam("id") int idObjectes,
                                  @RequestParam("nameCanvas") String nameCanvas,
                                  HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        try {
            Canvas canvas = canvasServices.getCanvasToModify(idObjectes, email);
            String nameUser = (String) session.getAttribute("name");
            System.out.println("print figures" + canvas.getFigures());

            System.out.println("print strokes" + canvas.getStrokes());
            model.addAttribute("llistaFigureJson", canvas.getFigures());
            model.addAttribute("llistaStroke", canvas.getStrokes());
            model.addAttribute("nameCanvas", nameCanvas);
            model.addAttribute("name",nameUser);
            return "modify";
        } catch (NotYourCanvasException e) {
            System.out.println("Mensaje del throw " + e.getMessage());
            String message = e.getMessage();
            model.addAttribute("errorMessage", message);
            return "errorNotYourCanvas";
        }
    }
}
