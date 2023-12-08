package com.esliceu.PracticaDrawing2SpringBoot.Entities;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.time.Instant;

public class Canvas {
    private int idObjectes;
    private String nameCanvas;
    private String user_email;
    private Instant dataCreacio;
    boolean trash;
    @Column(name = "public")
    boolean publicDraw;

    public Canvas () {
    }

    public Canvas(int idObjectes, String nameCanvas, String user_email, Instant dataCreacio, boolean trash, boolean publicDraw) {
        this.idObjectes = idObjectes;
        this.nameCanvas = nameCanvas;
        this.user_email = user_email;
        this.dataCreacio = dataCreacio;
        this.trash = trash;
        this.publicDraw = publicDraw;
    }

    @Override
    public String toString() {
        return "Canvas{" +
                "idObjectes=" + idObjectes +
                ", nameCanvas='" + nameCanvas + '\'' +
                ", user_email='" + user_email + '\'' +
                ", dataCreacio=" + dataCreacio +
                ", trash=" + trash +
                ", publicDraw=" + publicDraw +
                '}';
    }

    public int getIdObjectes() {
        return idObjectes;
    }

    public void setIdObjectes(int idObjectes) {
        this.idObjectes = idObjectes;
    }

    public String getNameCanvas() {
        return nameCanvas;
    }

    public void setNameCanvas(String nameCanvas) {
        this.nameCanvas = nameCanvas;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public Instant getDataCreacio() {
        return dataCreacio;
    }
    public Timestamp viewDataCreacio() {
        return Timestamp.from(dataCreacio);
    }

    public void setDataCreacio(Instant dataCreacio) {
        this.dataCreacio = dataCreacio;
    }

    public boolean isTrash() {
        return trash;
    }

    public void setTrash(boolean trash) {
        this.trash = trash;
    }

    public boolean isPublicDraw() {
        return publicDraw;
    }

    public void setPublicDraw(boolean publicDraw) {
        this.publicDraw = publicDraw;
    }
}