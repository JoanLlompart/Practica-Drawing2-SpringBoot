package com.esliceu.PracticaDrawing2SpringBoot.DTO;
import org.springframework.stereotype.Component;
@Component
public class CanvasPermissionDTO {
    private int idCanvas;
    private String permissionType;
    private String user_email;
    private String owner_email;
    public CanvasPermissionDTO (){
    }
    public CanvasPermissionDTO(int idCanvas, String permissionType, String user_email, String owner_email) {
        this.idCanvas = idCanvas;
        this.permissionType = permissionType;
        this.user_email = user_email;
        this.owner_email = owner_email;
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
    public String getOwner_email() {
        return owner_email;
    }
    public void setOwner_email(String owner_email) {
        this.owner_email = owner_email;
    }
    @Override
    public String toString() {
        return "CanvasPermissionDTO{" +
                "idCanvas=" + idCanvas +
                ", permissionType='" + permissionType + '\'' +
                ", user_email='" + user_email + '\'' +
                ", owner_email='" + owner_email + '\'' +
                '}';
    }
}
