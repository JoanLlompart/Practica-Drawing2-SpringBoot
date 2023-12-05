package com.esliceu.PracticaDrawing2SpringBoot.Controllers;
import com.esliceu.PracticaDrawing2SpringBoot.Services.CanvasServices;
import com.esliceu.PracticaDrawing2SpringBoot.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Enumeration;
@Controller
public class CanvasController {
    @Autowired
    CanvasServices canvasServices;
    @Autowired
    UserService userService;
    @GetMapping("/canvasDraw")
    public String getCanvasPage(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        String name = userService.getNameOfUser(email);
        //session.setAttribute("name", name);
        String nomSesion = (String)session.getAttribute("name");
        model.addAttribute("name", nomSesion);
        //printSession(session);
        return "canvasDraw"; // nombre de la vista Thymeleaf
    }
    @PostMapping("/canvasDraw")
    public String saveCanvas(HttpServletRequest req, HttpSession session) {
        String email = (String) session.getAttribute("email");
        userService.setEmail(email);
        userService.setEmail(email);
        String strokJson = req.getParameter("strokesData");
        System.out.println("Dibuix amb json " + strokJson);
        String figureJson = req.getParameter("figuresData");
        System.out.println("Figuras amb json " + figureJson);
        if (strokJson.equals("[]") && figureJson.equals("[]")) {
            System.err.println("Error no hi ha contingut a aquest canvas");
            return "CanvasDraw";
            //throw new RuntimeException();
        }
        String nameCanvas = req.getParameter("nomDibuix");
        //si el user no ha posat un nom se asigna automaticament.
        if (nameCanvas == null || nameCanvas.isEmpty()) {
            nameCanvas = canvasServices.generarNom(nameCanvas);
        }
        System.out.println("NOM DE EL DIBUIX :" + nameCanvas);
        String esPub=req.getParameter("isPublic");
        System.out.println("Valkor de si es public o no -> " + esPub);
        canvasServices.newCanvas(strokJson, figureJson, email, nameCanvas,esPub);
        return "canvasDraw";
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
