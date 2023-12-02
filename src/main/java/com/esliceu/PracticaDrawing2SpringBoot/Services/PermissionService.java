package com.esliceu.PracticaDrawing2SpringBoot.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
   private String user_email;
   private String permissionType;
   private int idCanvas;

   public PermissionService(){}

    public PermissionService(String user_email, String permissionType, int idCanvas) {
        this.user_email = user_email;
        this.permissionType = permissionType;
        this.idCanvas = idCanvas;
    }

    public int getIdCanvas() {
        return idCanvas;
    }

    public void setIdCanvas(int idCanvas) {
        this.idCanvas = idCanvas;
    }

    public String getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public void getPermission() {
    }
}
