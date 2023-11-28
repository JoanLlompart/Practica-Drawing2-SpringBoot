package com.esliceu.PracticaDrawing2SpringBoot.Controllers;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.Canvas;
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
public class ViewController {
    @Autowired
    CanvasServices canvasServices;
    @Autowired
    UserService userService;
    @GetMapping("/viewCanvas")
    public String viewCanvas(@RequestParam("id") int idObjectes,
                             @RequestParam("nameCanvas") String nameCanvas,
                             HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        Canvas canvas = canvasServices.getCanvas(idObjectes);
        Gson gson = new Gson();
        String jsonFigure = gson.toJson(canvas.getFigures());
        String jsonStrokes = gson.toJson(canvas.getStrokes());

        model.addAttribute("llistaFigureJson", jsonFigure);
        model.addAttribute("llistaStroke", jsonStrokes);
        model.addAttribute("nameCanvas", nameCanvas);
        return "viewCanvas";
    }

    public String postViewCanvas(HttpSession session) {
        String email = (String) session.getAttribute("email");
        userService.setEmail(email);

        return "viewCanvas";
    }

}
