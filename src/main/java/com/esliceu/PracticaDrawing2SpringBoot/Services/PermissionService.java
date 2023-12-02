package com.esliceu.PracticaDrawing2SpringBoot.Services;


import com.esliceu.PracticaDrawing2SpringBoot.DTO.CanvasPermissionDTO;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Permission;
import com.esliceu.PracticaDrawing2SpringBoot.Repository.PermissionRepo;
import com.esliceu.PracticaDrawing2SpringBoot.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
  @Autowired
    CanvasPermissionDTO canvasPermissionDTO;
  @Autowired
    PermissionRepo permissionRepo;
  @Autowired
    UserRepo userRepo;

    public PermissionService(CanvasPermissionDTO canvasPermissionDTO) {
        this.canvasPermissionDTO = canvasPermissionDTO;
    }


    public boolean givePermission(CanvasPermissionDTO canvasPermissionDTO) {
        Permission p = new Permission();
        p.setUser_email(canvasPermissionDTO.getUser_email());
        p.setIdCanvas(canvasPermissionDTO.getIdCanvas());
        p.setPermissionType(canvasPermissionDTO.getPermissionType());
        return permissionRepo.getPermission(p);
    }
}
