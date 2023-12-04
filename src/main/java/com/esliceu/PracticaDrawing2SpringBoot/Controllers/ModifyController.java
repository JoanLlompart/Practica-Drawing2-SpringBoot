package com.esliceu.PracticaDrawing2SpringBoot.Controllers;

import com.esliceu.PracticaDrawing2SpringBoot.DTO.CanvasVersionDTO;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Canvas;
import com.esliceu.PracticaDrawing2SpringBoot.Exceptions.NotYourCanvasException;
import com.esliceu.PracticaDrawing2SpringBoot.Services.CanvasServices;
import com.esliceu.PracticaDrawing2SpringBoot.Services.UserService;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModifyController {
    @Autowired
    CanvasServices canvasServices;
    @Autowired
    CanvasVersionDTO canvasVersionDTO;
    @Autowired
    UserService userService;
    @GetMapping("/modify")
    public String getModifyCanvas(@RequestParam("id") int idObjectes,
                                  @RequestParam("nameCanvas") String nameCanvas,
                                  HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        try {
          CanvasVersionDTO canvasVersionDTO = canvasServices.getCanvasToModify(idObjectes, email);
            String nameUser = (String) session.getAttribute("name");
            System.out.println("print figures" + canvasVersionDTO.getFigures());

            System.out.println("print strokes" + canvasVersionDTO.getStrokes());
            model.addAttribute("llistaFigureJson", canvasVersionDTO.getFigures());
            model.addAttribute("llistaStroke", canvasVersionDTO.getStrokes());
            model.addAttribute("nameCanvas", nameCanvas);
            model.addAttribute("name",nameUser);
            return "modify";
        } catch (NotYourCanvasException e) {
            System.out.println("Mensaje del throw " + e.getMessage());
            String message = e.getMessage();
            model.addAttribute("errorMessage", message);
            return "errorNotYourCanvas";
        }
       /* try {
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

        */
    }
    @PostMapping("/modify")
    public String saveCanvas(@RequestParam("strokesData") String strokJson,
                             @RequestParam("figuresData") String figureJson,
                             @RequestParam("nomDibuix") String nameCanvas,
                             @RequestParam("nomDibuix") String esPub,
                             HttpSession session) {
        String email = (String) session.getAttribute("email");
        userService.setEmail(email);
       // String strokJson = req.getParameter("strokesData");
        System.out.println("Dibuix amb json " + strokJson);
        //String figureJson = req.getParameter("figuresData");
        System.out.println("Figuras amb json " + figureJson);
        if (strokJson.equals("[]") && figureJson.equals("[]")) {
            System.err.println("Error no hi ha contingut a aquest canvas");
            throw new RuntimeException();
        }
       // String nameCanvas = req.getParameter("nomDibuix");
        //si el user no ha posat un nom se asigna automaticament.
        if (nameCanvas == null || nameCanvas.isEmpty()) {
            nameCanvas = canvasServices.generarNom(nameCanvas);
        }
        System.out.println("NOM DE EL DIBUIX :" + nameCanvas);
        System.out.println("Valkor de si es public o no -> " + esPub);
        canvasServices.newCanvas(strokJson, figureJson, email, nameCanvas,esPub);
        return "canvasDraw";
    }
}
