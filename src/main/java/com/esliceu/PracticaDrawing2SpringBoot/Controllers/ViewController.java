package com.esliceu.PracticaDrawing2SpringBoot.Controllers;

import com.esliceu.PracticaDrawing2SpringBoot.DTO.CanvasPermissionDTO;
import com.esliceu.PracticaDrawing2SpringBoot.DTO.CanvasVersionDTO;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Canvas;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Permission;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.User;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Version;
import com.esliceu.PracticaDrawing2SpringBoot.Services.CanvasServices;
import com.esliceu.PracticaDrawing2SpringBoot.Services.PermissionService;
import com.esliceu.PracticaDrawing2SpringBoot.Services.UserService;
import com.esliceu.PracticaDrawing2SpringBoot.Services.VersionService;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class ViewController {
    @Autowired
    CanvasServices canvasServices;
    @Autowired
    UserService userService;
    @Autowired
    VersionService versionService;
    @Autowired
    PermissionService permissionService;
    @Autowired
    CanvasPermissionDTO canvasPermissionDTO;
    @Autowired
    CanvasVersionDTO canvasVersionDTO;
    @GetMapping("/viewCanvas")
    public String viewCanvas(@RequestParam("id") int idObjectes,
                             @RequestParam("nameCanvas") String nameCanvas,
                             HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        //Canvas canvas = canvasServices.getCanvas(idObjectes,email);
        CanvasVersionDTO canvasVersionDTO= canvasServices.getVersion(idObjectes,email);
        //List<User> usersList=userService.allUsersExceptUserSession(email);
        List<User> usersList=userService.allUsersExceptUserSession(email,idObjectes);
       // System.out.println("ID DE EL VIEWDTO" + canvasVersionDTO.getIdObjectes());
        canvasPermissionDTO.setIdCanvas(idObjectes);
        //todo: falta poder elegir la versio.
        List<Version> versionList = versionService.getAllVersion(idObjectes);

        System.out.println("------Version List--------");
        for (Version v:versionList) {
            System.out.println("IdDraw : " + v.getIdDraw() + ",    idVersion "+ v.getIdVersion() + " , data : " + v.viewDateLastModified());
        }
        model.addAttribute("versions",versionList);
        model.addAttribute("allUsers", usersList);
        String nameUser=(String) session.getAttribute("name");
        model.addAttribute("name",nameUser);
        model.addAttribute("llistaFigureJson", canvasVersionDTO.getFigures());
        model.addAttribute("llistaStroke", canvasVersionDTO.getStrokes());
        model.addAttribute("nameCanvas", nameCanvas);
        model.addAttribute(nameUser);
        return "viewCanvas";
    }

    @PostMapping("/viewCanvas")
    public String postViewCanvas(@RequestBody Permission permission,
                                 HttpSession session) {
        String email = (String) session.getAttribute("email");
        canvasPermissionDTO.setPermissionType(permission.getPermissionType());
        System.out.println(permission.getUser_email()+ "pem?");
        canvasPermissionDTO.setOwner_email(email);
        canvasPermissionDTO.setUser_email(permission.getUser_email());
        userService.setEmail(email);
        boolean permisOk=permissionService.givePermission(canvasPermissionDTO);

        System.out.println("ID canvas services :" + canvasPermissionDTO.getIdCanvas());
        if (permisOk) {
            //Mensatge de exit
        } else {
            //exception tornara.
        }
        return "viewCanvas";
    }
    @PostMapping("/viewCanvas/copy")
    public String copyCanvasPost(@RequestBody Version version,HttpSession session) {
        String email = (String) session.getAttribute("email");

        System.out.println("version copy " + version.getIdVersion());
        System.out.println(version.toString());
        version.setIdDraw(canvasVersionDTO.getIdObjectes());
        //canvasVersionDTO.setVersion(version.getIdVersion());
        System.out.println("Copy----");
        System.out.println(canvasVersionDTO.toString());
        version=versionService.getVersionById(version,email);
        System.out.println("ver2 " + version.toString());
        //boolean copyOk =versionService.makeCopyCanvas(version,email);

        CanvasVersionDTO canVerDTOCopy = new CanvasVersionDTO();

        canVerDTOCopy.setFigures(version.getFigures());
        canVerDTOCopy.setStrokes(version.getStrokes());
        canVerDTOCopy.setNameCanvas(canvasVersionDTO.getNameCanvas());
        canVerDTOCopy.setTrash(false);
        canVerDTOCopy.setPublic(false);
        canVerDTOCopy.setIdObjectes(version.getIdDraw());
        canVerDTOCopy.setVersion(version.getIdVersion());
        canVerDTOCopy.setUser_email(email);
        canVerDTOCopy.setDateLastModified(version.getDateLastModified());
        canVerDTOCopy.setDataCreacio(canvasVersionDTO.getDataCreacio());
        canVerDTOCopy.setNumberObject(canvasVersionDTO.getNumberObject());
        boolean copyOk = false;
        if (versionService.verifyCanCopyVersion(version,email)) {
            copyOk = canvasServices.createCanvasCopy(canVerDTOCopy);
        }
        if (copyOk) {
            System.out.println("Se ha guardat correctament");
        }

        return "viewCanvas";
    }
}
