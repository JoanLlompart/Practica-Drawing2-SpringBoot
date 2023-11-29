package com.esliceu.PracticaDrawing2SpringBoot.Entities;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

public class Canvas {
    private String nameCanvas;
    private int idObjectes;
    private String user_email;
    private Date dataCreacio;
    String figures;
    String strokes;
   // List<Figure> figures;
    //List<Strokes> strokes;
    private int numberObject;
    private Date dateLastModified;


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
}