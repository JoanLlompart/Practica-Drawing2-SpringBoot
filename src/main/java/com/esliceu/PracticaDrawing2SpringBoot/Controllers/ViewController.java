package com.esliceu.PracticaDrawing2SpringBoot.Controllers;

import com.esliceu.PracticaDrawing2SpringBoot.DTO.CanvasPermissionDTO;
import com.esliceu.PracticaDrawing2SpringBoot.DTO.CanvasVersionDTO;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Canvas;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.User;
import com.esliceu.PracticaDrawing2SpringBoot.Services.CanvasServices;
import com.esliceu.PracticaDrawing2SpringBoot.Services.PermissionService;
import com.esliceu.PracticaDrawing2SpringBoot.Services.UserService;
import com.esliceu.PracticaDrawing2SpringBoot.Services.VersionService;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ViewController {
    @Autowired
    CanvasServices canvasServices;
    @Autowired
    UserService userService;
    @Autowired
    PermissionService permissionService;
    @Autowired
    CanvasPermissionDTO canvasPermissionDTO;
    @GetMapping("/viewCanvas")
    public String viewCanvas(@RequestParam("id") int idObjectes,
                             @RequestParam("nameCanvas") String nameCanvas,
                             HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        //Canvas canvas = canvasServices.getCanvas(idObjectes,email);
        CanvasVersionDTO canvasVersionDTO= canvasServices.getVersion(idObjectes,email);
        List<User> usersList=userService.allUsersExceptUserSession(email);

        System.out.println("ID DE EL VIEWDTO" + canvasVersionDTO.getIdObjectes());

        model.addAttribute("allUsers", usersList);
        String nameUser=(String) session.getAttribute("name");
        model.addAttribute("llistaFigureJson", canvasVersionDTO.getFigures());
        model.addAttribute("llistaStroke", canvasVersionDTO.getStrokes());
        model.addAttribute("nameCanvas", nameCanvas);
        model.addAttribute(nameUser);
        return "viewCanvas";
    }
  /*  @PostMapping("/viewCanvas")
    public String postViewCanvas(HttpSession session) {
        String email = (String) session.getAttribute("email");
        userService. setEmail(email);
        return "viewCanvas";
    }
   */
    @PostMapping("/viewCanvas")
    public String postViewCanvas(@RequestParam("id") int id, // Recibiendo el parámetro "id" enviado desde la solicitud fetch
                                 @RequestParam("nameCanvas") String nameCanvas, // Recibiendo el parámetro "nameCanvas" enviado desde la solicitud fetch
                                 HttpSession session) {
        String email = (String) session.getAttribute("email");
        //email de el propietari
        canvasPermissionDTO.setOwner_email(email);

        userService.setEmail(email); // Por ejemplo, establecer el email en el servicio de usuario
        return "viewCanvas";
    }
    /*
    @PostMapping("/viewCanvas/write")
    public String writePermission(HttpSession session) {
        String email = (String) session.getAttribute("email");
        permissionService.setUser_email(email);
        permissionService.getPermission();
        return "viewCanvas";
    }
    @PostMapping("/viewCanvas/write")
    public String readPermission(HttpSession session) {
        //String email = (String) session.getAttribute("email");
        permissionService.setUser_email(email,);
        return "viewCanvas";
    }
     */


    @PostMapping("/viewCanvas/write")
    public String writePermission(@RequestParam("id") int id,
                                  @RequestParam("nameCanvas") String nameCanvas,
                                  HttpSession session) {
        String email = (String) session.getAttribute("email");
        permissionService.setUser_email(email);
        return "viewCanvas";
    }

    @PostMapping("/viewCanvas/read")
    public String readPermission(@RequestParam("id") int id,
                                 @RequestParam("user_email") String user_email,
                                 HttpSession session) {
        String email = (String) session.getAttribute("email");
        permissionService.setUser_email(email);
        permissionService.getPermission();
        return "viewCanvas";
    }





}
