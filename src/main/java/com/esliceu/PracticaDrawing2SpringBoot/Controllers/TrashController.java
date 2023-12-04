package com.esliceu.PracticaDrawing2SpringBoot.Controllers;

import com.esliceu.PracticaDrawing2SpringBoot.DTO.CanvasVersionDTO;
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
public class TrashController {
    @Autowired
    CanvasServices canvasServices;
    @Autowired
    UserService userService;
    @GetMapping("/trash")
    public String showTrash(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        String name = userService.getNameOfUser(email);
        List<CanvasVersionDTO> listCanvas = canvasServices.showMyTrash(email);
     /*   for (CanvasVersionDTO c : listCanvas) {
            System.out.println(c.toString());
        }
      */
        model.addAttribute("trash", listCanvas);
        model.addAttribute("email", email);
        model.addAttribute("name", name);
        return "trash";
    }

    @PostMapping("/trash/delete")
    public String deleteCanvas(@RequestParam("id") int id, HttpSession session,Model model) {
        String email = (String) session.getAttribute("email");
        userService.setEmail(email);
        if (!canvasServices.deleteCanvasById(id, email)) {
            //TODO
            model.addAttribute("message","Error: No se ha eliminar el Canvas!");
        }else {
            model.addAttribute("message","Canvas eliminado con exito!");
        }
        return "redirect:/trash";
    }
    @PostMapping("/trash/exitTrash")
    public String recoverCanvas(@RequestParam("id") int id, HttpSession session,Model model) {
        String email = (String) session.getAttribute("email");
        userService.setEmail(email);
        if (!canvasServices.sendOutToTrash(id, email)) {
            model.addAttribute("message","Error: No se ha podido sacar el Canvas de la papelera!");
        } else {
            model.addAttribute("message","Canvas recuperado de la papelera");
        }
        return "redirect:/allCanvas";
    }
}
