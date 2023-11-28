package com.esliceu.PracticaDrawing2SpringBoot.Entities;
import java.util.Date;
import java.util.List;

public class Canvas {
    private String nameCanvas;
    private int idObjectes;
    private User user;
    private Date dataCreacio;
    List<Figure> figures;
    List<Strokes> strokes;
    private int numberObject;

    public int getNumberObject() {
        return numberObject;
    }

    public void setNumberObject(int numberObject) {
        this.numberObject = numberObject;
    }
//private String enllacDibuix;

    //private Date dataDarreraMod;
    public Canvas(String nameCanvas, int idObjectes, User user, Date dataCreacio, List<Figure> figures, List<Strokes> strokes) {
        this.nameCanvas = nameCanvas;
        this.idObjectes = idObjectes;
        this.user = user;
        this.dataCreacio = dataCreacio;
        this.figures = figures;
        this.strokes = strokes;
    }
    //contructor buit
    public Canvas() {
    }
    public String getNameCanvas() {
        return nameCanvas;
    }

    public void setNameCanvas(String nameCanvas) {
        this.nameCanvas = nameCanvas;
    }

    public int getIdObjectes() {
        return idObjectes;
    }

    public void setIdObjectes(int idObjectes) {
        this.idObjectes = idObjectes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDataCreacio() {
        return dataCreacio;
    }

    public void setDataCreacio(Date dataCreacio) {
        this.dataCreacio = dataCreacio;
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public void setFigures(List<Figure> figures) {
        this.figures = figures;
    }

    public List<Strokes> getStrokes() {
        return strokes;
    }

    public void setStrokes(List<Strokes> strokes) {
        this.strokes = strokes;
    }

    @Override
    public String toString() {
        return "Canvas{" +
                "nameCanvas='" + nameCanvas + '\'' +
                ", idObjectes=" + idObjectes +
                ", user=" + user +
                ", dataCreacio=" + dataCreacio +
                ", figures=" + figures +
                ", strokes=" + strokes +
                ", numberObject=" + numberObject +
                '}';
    }
}