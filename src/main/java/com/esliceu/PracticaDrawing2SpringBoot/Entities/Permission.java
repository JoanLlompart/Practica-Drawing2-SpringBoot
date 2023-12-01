package com.esliceu.PracticaDrawing2SpringBoot.Entities;

public class Permission {
    private int idCanvas;
    private String user_email;
    private String permissionType;
    public Permission() {
    }
    public Permission(int idCanvas, String user_email, String permissionType) {
        this.idCanvas = idCanvas;
        this.user_email = user_email;
        this.permissionType = permissionType;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "idCanvas=" + idCanvas +
                ", user_email='" + user_email + '\'' +
                ", permissionType='" + permissionType + '\'' +
                '}';
    }

    public int getIdCanvas() {
        return idCanvas;
    }

    public void setIdCanvas(int idCanvas) {
        this.idCanvas = idCanvas;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }
}
