package com.esliceu.PracticaDrawing2SpringBoot.Services;


import com.esliceu.PracticaDrawing2SpringBoot.DTO.CanvasPermissionDTO;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Permission;
import com.esliceu.PracticaDrawing2SpringBoot.Exceptions.InvalidPermissionTypeException;
import com.esliceu.PracticaDrawing2SpringBoot.Repository.PermissionRepo;
import com.esliceu.PracticaDrawing2SpringBoot.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
  //@Autowired
    //CanvasPermissionDTO canvasPermissionDTO;
  @Autowired
    PermissionRepo permissionRepo;
  @Autowired
    UserRepo userRepo;

    public boolean givePermission(CanvasPermissionDTO canvasPermissionDTO) {
        Permission p = new Permission();
        System.out.println("Pc" + canvasPermissionDTO.getUser_email());
        p.setUser_email(canvasPermissionDTO.getUser_email());
        p.setIdCanvas(canvasPermissionDTO.getIdCanvas());
        //p.setPermissionType(canvasPermissionDTO.getPermissionType());
        //Pasam a mayuscula la lletra que especifica el tipus de permis que es donara.
        String typePer = canvasPermissionDTO.getPermissionType().toUpperCase();
        //Comprobacio que la dada que es manda a la BD es valida.
        System.out.println("type" +typePer);
        if (typePer.equals("R") || typePer.equals("W")) {
            //Si es correcte el asignam a la Entitat Permission.
            p.setPermissionType(typePer);
        } else {
            //Si no coincideix amb els permisos enviats i validats per l'aplicacio, es considera no valid
            // per evitar la manipulacio per el client
            System.out.println("Entra ");
            return false;
        }
        String owner_email = canvasPermissionDTO.getOwner_email();
        System.out.println(p.getUser_email() + "permiservice");
        return permissionRepo.getPermission(p,owner_email);

        //return new InvalidPermissionTypeException();
    }
}
