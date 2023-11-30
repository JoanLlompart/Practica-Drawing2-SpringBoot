package com.esliceu.PracticaDrawing2SpringBoot.Entities;

import javax.persistence.Column;
import java.util.Date;

public class Canvas {
    private int idObjectes;
    private String nameCanvas;

    private String user_email;
    private Date dataCreacio;
    private int numberObject;
    @Column(name = "figuresJSON")
    String figures;
    @Column(name = "strokesJSON")
    String strokes;
    boolean trash;
    private Date dateLastModified;
    //private int version;

    public Canvas () {
    }
    public Canvas(int idObjectes, String nameCanvas, String user_email, Date dataCreacio, int numberObject, String figures, String strokes, boolean trash) {
        this.idObjectes = idObjectes;
        this.nameCanvas = nameCanvas;
        this.user_email = user_email;
        this.dataCreacio = dataCreacio;
        this.numberObject = numberObject;
        this.figures = figures;
        this.strokes = strokes;
        this.trash = trash;
    }

    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
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

    public Date getDataCreacio() {
        return dataCreacio;
    }

    public void setDataCreacio(Date dataCreacio) {
        this.dataCreacio = dataCreacio;
    }

    public int getNumberObject() {
        return numberObject;
    }

    public void setNumberObject(int numberObject) {
        this.numberObject = numberObject;
    }

    public String getFigures() {
        return figures;
    }

    public void setFigures(String figures) {
        this.figures = figures;
    }

    public String getStrokes() {
        return strokes;
    }

    public void setStrokes(String strokes) {
        this.strokes = strokes;
    }

    public boolean isTrash() {
        return trash;
    }

    public void setTrash(boolean trash) {
        this.trash = trash;
    }

    @Override
    public String toString() {
        return "Canvas{" +
                "idObjectes=" + idObjectes +
                ", nameCanvas='" + nameCanvas + '\'' +
                ", user_email='" + user_email + '\'' +
                ", dataCreacio=" + dataCreacio +
                ", numberObject=" + numberObject +
                ", figures='" + figures + '\'' +
                ", strokes='" + strokes + '\'' +
                ", trash=" + trash +
                ", dateLastModified=" + dateLastModified +
                '}';
    }
}