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
public class AllCanvasController {
    @Autowired
    CanvasServices canvasServices;
    @Autowired
    UserService userService;
    @Autowired
    CanvasVersionDTO canvasVersionDTO;
    @GetMapping("/allCanvas")
    public String showAllCanvas(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        String name = userService.getNameOfUser(email);
        //session.setAttribute("name", name);
        System.out.println("antes de petar");
        List<CanvasVersionDTO> listCanvas = canvasServices.showAllCanvas(email);

        model.addAttribute("allCanvas", listCanvas);
        model.addAttribute("email", email);
        model.addAttribute("name", name);
        return "allCanvas";
    }

    @PostMapping("/trash")
    public String sendToTrash(@RequestParam("id") int id, HttpSession session,Model model) {
        String email = (String) session.getAttribute("email");
        userService.setEmail(email);
        if (!canvasServices.sendToTrash(id, email)) {
            //model.addAttribute("message","No se ha podido borrar");
        } else {
           // model.addAttribute("message","Canvas eliminado con exito!");
        }
        return "redirect:/allCanvas";
    }
}