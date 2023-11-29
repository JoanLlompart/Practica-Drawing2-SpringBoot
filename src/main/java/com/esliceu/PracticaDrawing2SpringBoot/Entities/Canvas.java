package com.esliceu.PracticaDrawing2SpringBoot.Entities;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
    //proba de contructor amb les llistes amb String
    //Cream un constructor diferent que rebra en lloc de llistes de objectes Figure i Strokes rebra un json i ho convertira en el contructor a
    public Canvas(String nameCanvas, int idObjectes, User user, Date dataCreacio, String figures, String strokes) {
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

    public String viewStructure() {
        return "Canvas{" +
                "nameCanvas='" + nameCanvas + '\'' +
                ", idObjectes=" + idObjectes +
                ", user_id=" + user.getId() +
                ", dataCreacio=" + dataCreacio +
                ", figures=" + figures +
                ", strokes=" + strokes +
                ", numberObject=" + numberObject +
                '}';
    }
}