package com.esliceu.PracticaDrawing2SpringBoot.Controllers;

import com.esliceu.PracticaDrawing2SpringBoot.DTO.CanvasVersionDTO;
import com.esliceu.PracticaDrawing2SpringBoot.DTO.ModifyCanvasVersionDTO;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Canvas;
import com.esliceu.PracticaDrawing2SpringBoot.Exceptions.NotYourCanvasException;
import com.esliceu.PracticaDrawing2SpringBoot.Services.CanvasServices;
import com.esliceu.PracticaDrawing2SpringBoot.Services.UserService;
import com.esliceu.PracticaDrawing2SpringBoot.Services.VersionService;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModifyController {
    @Autowired
    CanvasServices canvasServices;
    @Autowired
    VersionService versionService;//proba
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
           canvasVersionDTO = canvasServices.getCanvasToModify(idObjectes, email);
            String nameUser = (String) session.getAttribute("name");
            //System.out.println("print figures" + canvasVersionDTO.getFigures());
            //System.out.println("print strokes" + canvasVersionDTO.getStrokes());
            model.addAttribute("llistaFigureJson", canvasVersionDTO.getFigures());
            model.addAttribute("llistaStroke", canvasVersionDTO.getStrokes());
            model.addAttribute("nameCanvas", nameCanvas);
            model.addAttribute("name",nameUser);
            model.addAttribute("idObjectes",idObjectes);
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
    /*
    @PostMapping("/modify")
    public String saveCanvas(@RequestParam("strokesData") String strokJson,
                             @RequestParam("figuresData") String figureJson,
                             @RequestParam("nomDibuix") String nameCanvas,
                             HttpServletRequest req,
                             HttpSession session) {
        String email = (String) session.getAttribute("email");
        userService.setEmail(email);
       // String strokJson = req.getParameter("strokesData");
       // System.out.println("Dibuix amb json " + strokJson);
        //String figureJson = req.getParameter("figuresData");
        //System.out.println("Figuras amb json " + figureJson);
        System.out.println("Id de el canvas" + canvasVersionDTO.getIdObjectes());
        if (strokJson.equals("[]") && figureJson.equals("[]")) {
            System.err.println("Error no hi ha contingut a aquest canvas");
            throw new RuntimeException();
        }
       // String nameCanvas = req.getParameter("nomDibuix");
        //si el user no ha posat un nom se asigna automaticament.
        System.out.println("Nom" +nameCanvas);
        if (nameCanvas == null || nameCanvas.isEmpty()) {
            nameCanvas = canvasServices.generarNom(nameCanvas);
        }

        String isPub = req.getParameter("isPublic");
        System.out.println("NOM DE EL DIBUIX :" + nameCanvas);
        //Actualitzam els valors de la nova versio i el nous JSON
        canvasVersionDTO.setStrokes(strokJson);
        canvasVersionDTO.setFigures(figureJson);
        System.out.println(canvasVersionDTO.toString());
        if(versionService.newVersionCanvas(canvasVersionDTO,isPub)) {
            System.out.println("Se ha actualitzat correctament");
        } else {
            System.out.println("No se ha pogut crear la nova versio");
        }
        return "canvasDraw";
    }

     */



    @PostMapping("/modify")
    public String saveCanvas(@RequestBody ModifyCanvasVersionDTO modifyCanvasVersionDTO,
                             HttpSession session,Model model) {
        String email = (String) session.getAttribute("email");
        userService.setEmail(email);
       // System.out.println("Id de el canvas" + canvasVersionDTO.getIdObjectes());
        String strokJson = modifyCanvasVersionDTO.getStrokesData();
        String figureJson = modifyCanvasVersionDTO.getFiguresData();
        System.out.println("---------ModifyController--------");
        String nameCanvas = modifyCanvasVersionDTO.getNameCanvas();
        System.out.println("NOM DE EL DIBUIX :" + nameCanvas);

        canvasVersionDTO.setPublic(modifyCanvasVersionDTO.getIsPublic());
        System.out.println("Torna puclic com a : " + modifyCanvasVersionDTO.getIsPublic());
        if (strokJson.equals("[]") && figureJson.equals("[]")) {
            System.err.println("Error no hi ha contingut a aquest canvas");
            throw new RuntimeException();
        }
        // String nameCanvas = req.getParameter("nomDibuix");
        //si el user no ha posat un nom se asigna automaticament.
        System.out.println("Nom" + nameCanvas);
        if (nameCanvas == null || nameCanvas.isEmpty()) {
            nameCanvas = canvasServices.generarNom(nameCanvas);
        }

        //String isPub = req.getParameter("isPublic");
        //Actualitzam els valors de la nova versio i el nous JSON
        canvasVersionDTO.setStrokes(strokJson);
        canvasVersionDTO.setFigures(figureJson);
        canvasVersionDTO.setNameCanvas(nameCanvas);

        if(versionService.newVersionCanvas(canvasVersionDTO)) {
            System.out.println("Se ha actualitzat correctament");
           // model.addAttribute("message-ok",true);
           // return "Se ha creat una nova versio de el canvas";
        } else {
            System.out.println("No se ha pogut crear la nova versio");
            //model.addAttribute("message-error",true);
            //return "Datos no recibidos correctamente";
        }
        return "modify";
    }
}
