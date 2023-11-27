package com.esliceu.PracticaDrawing2SpringBoot.Entities;

import java.util.Date;
import java.util.List;

public class Figure extends Canvas {
    private String type;
    private String color;
    private boolean isFilled;
    private int size;
    private int centerX,centerY;

    public Figure(String nameCanvas, int idObjectes, User user, Date dataCreacio, List<Figure> figures, List<Strokes>
            strokes, String type, String color, boolean isFilled, int size, int centerX, int centerY) {

        super(nameCanvas, idObjectes, user, dataCreacio, figures, strokes);
        this.type = type;
        this.color = color;
        this.isFilled = isFilled;
        this.size = size;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public Figure(String type, String color, boolean isFilled, int size, int centerX, int centerY) {
        this.type = type;
        this.color = color;
        this.isFilled = isFilled;
        this.size = size;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }
}
