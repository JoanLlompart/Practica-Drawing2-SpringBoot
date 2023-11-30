package com.esliceu.PracticaDrawing2SpringBoot.Entities;

import javax.persistence.Column;
import java.util.Date;


public class Version {
    private int idVersion;
    private int idDraw;
    private int numberObject;
    @Column(name = "figuresJSON")
    private String figures;
    @Column(name = "strokesJSON")
    private String strokes;
    private Date dateLastModified;
    private String user_email;

    public Version() {

    }
    public Version(int idVersion, int idDraw, int numberObject, String figures, String strokes, Date dateLastModified, String user_email) {
        this.idVersion = idVersion;
        this.idDraw = idDraw;
        this.numberObject = numberObject;
        this.figures = figures;
        this.strokes = strokes;
        this.dateLastModified = dateLastModified;
        this.user_email = user_email;
    }

    @Override
    public String toString() {
        return "Version{" +
                "idVersion=" + idVersion +
                ", idDraw=" + idDraw +
                ", numberObject=" + numberObject +
                ", figures='" + figures + '\'' +
                ", strokes='" + strokes + '\'' +
                ", dateLastModified=" + dateLastModified +
                ", user_email='" + user_email + '\'' +
                '}';
    }

    public int getIdVersion() {
        return idVersion;
    }

    public void setIdVersion(int idVersion) {
        this.idVersion = idVersion;
    }

    public int getIdDraw() {
        return idDraw;
    }

    public void setIdDraw(int idDraw) {
        this.idDraw = idDraw;
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

    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
