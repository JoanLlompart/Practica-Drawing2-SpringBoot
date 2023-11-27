package com.esliceu.PracticaDrawing2SpringBoot.Entities;
import com.google.gson.annotations.SerializedName;
public class Punt {
    //per guardar les cordenades de els punts a ma alçada
    @SerializedName("y")
    private int positionY;
    @SerializedName("x")
    private int positionX;

    public Punt(int positionY, int positionX) {
        this.positionY = positionY;
        this.positionX = positionX;
    }
    @Override
    public String toString() {
        return "Punt{" +
                "positionY=" + positionY +
                ", positionX=" + positionX +
                '}';
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }
}
