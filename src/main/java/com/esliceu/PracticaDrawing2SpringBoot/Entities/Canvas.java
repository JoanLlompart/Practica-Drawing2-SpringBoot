package com.esliceu.PracticaDrawing2SpringBoot.Entities;

import javax.persistence.Column;
import java.util.Date;

public class Canvas {
    private String nameCanvas;
    private int idObjectes;
    private String user_email;
    private Date dataCreacio;
    private int numberObject;
    @Column(name = "figuresJSON")
    String figures;
    @Column(name = "strokesJSON")
    String strokes;
    boolean trash;

    private Date dateLastModified;

    public Canvas(String nameCanvas, int idObjectes, String user_email, Date dataCreacio, String figures, String strokes, int numberObject) {
        this.nameCanvas = nameCanvas;
        this.idObjectes = idObjectes;
        this.user_email = user_email;
        this.dataCreacio = dataCreacio;
        this.figures = figures;
        this.strokes = strokes;
        this.numberObject = numberObject;
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

    public int getNumberObject() {
        return numberObject;
    }

    public void setNumberObject(int numberObject) {
        this.numberObject = numberObject;
    }

    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }
    //proba de contructor amb les llistes amb String
    //Cream un constructor diferent que rebra en lloc de llistes de objectes Figure i Strokes rebra un json i ho convertira en el contructor a
   /* public Canvas(String nameCanvas, int idObjectes, User user, Date dataCreacio, String figures, String strokes) {
        this.nameCanvas = nameCanvas;
        this.idObjectes = idObjectes;
        this.user = user;
        this.dataCreacio = dataCreacio;
        System.out.println("Ha entrat");
        Gson gson = new Gson();
            //Parsetjam el JSON a la clase que pertany
            this.figures = gson.fromJson(figures, new TypeToken<List<Figure>>() {
            }.getType());
            Type listType = new TypeToken<List<Strokes>>() {
            }.getType();
            this.strokes = gson.fromJson(strokes, listType);
        //this.figures = figures;
        //this.strokes = strokes;
    }

    */

    @Override
    public String toString() {
        return "Canvas{" +
                "nameCanvas='" + nameCanvas + '\'' +
                ", idObjectes=" + idObjectes +
                ", user_email='" + user_email + '\'' +
                ", dataCreacio=" + dataCreacio +
                ", figures='" + figures + '\'' +
                ", strokes='" + strokes + '\'' +
                ", numberObject=" + numberObject +
                ", dateLastModified=" + dateLastModified +
                '}';
    }
}