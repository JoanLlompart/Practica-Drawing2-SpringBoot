package com.esliceu.PracticaDrawing2SpringBoot.Entities;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;
import java.util.List;

public class Strokes  extends Canvas{
    //utilitzam  @SerializedName per fer que la conversio de JSON funcioni amb diferents noms, es a dir perque
    // es puguin relacionar.
    @SerializedName("puntos")
    private List<Punt> punts;
    private String color;
    private int size;
    public Strokes(String nameCanvas, int idObjectes, User user, Date dataCreacio, List<Figure> figures, List<Strokes> strokes, List<Punt> punts, String color, int size) {
        super(nameCanvas, idObjectes, user, dataCreacio, figures, strokes);
        this.punts = punts;
        this.color = color;
        this.size = size;
    }
    public Strokes(List<Punt> punts, String color, int size) {
        this.punts = punts;
        this.color = color;
        this.size = size;
    }
    public Strokes() {
        super();
    }
    public List<Punt> getPunts() {
        return punts;
    }
    public void setPunts(List<Punt> punts) {
        this.punts = punts;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    @Override
    public String toString() {
        return "Strokes{" +
                "punts=" + punts +
                ", color='" + color + '\'' +
                ", size=" + size +
                '}';
    }
}
