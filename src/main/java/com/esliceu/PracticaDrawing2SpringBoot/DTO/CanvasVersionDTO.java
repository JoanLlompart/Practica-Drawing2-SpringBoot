package com.esliceu.PracticaDrawing2SpringBoot.DTO;
import org.springframework.stereotype.Component;
import javax.persistence.Column;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;

@Component
public class CanvasVersionDTO {
    private int idObjectes;
    private String nameCanvas;
    private String user_email;
    private Instant dataCreacio;
    private int numberObject;
    @Column(name = "figuresJSON")
    private String figures;
    @Column(name = "strokesJSON")
    private String strokes;
    private boolean trash;
    private Instant dateLastModified;
    private int version;
    private boolean isPublic;
    private String permissionType;
    public CanvasVersionDTO() {

    }
    public CanvasVersionDTO(int idObjectes, String nameCanvas, String user_email, Instant dataCreacio,
                            int numberObject, String figures, String strokes, boolean trash, Instant dateLastModified,
                            int version, boolean isPublic) {
        this.idObjectes = idObjectes;
        this.nameCanvas = nameCanvas;
        this.user_email = user_email;
        this.dataCreacio = dataCreacio;
        this.numberObject = numberObject;
        this.figures = figures;
        this.strokes = strokes;
        this.trash = trash;
        this.dateLastModified = dateLastModified;
        this.version = version;
        this.isPublic = isPublic;
    }

    @Override
    public String toString() {
        return "CanvasVersionDTO{" +
                "idObjectes=" + idObjectes +
                ", nameCanvas='" + nameCanvas + '\'' +
                ", user_email='" + user_email + '\'' +
                ", dataCreacio=" + dataCreacio +
                ", numberObject=" + numberObject +
                ", figures='" + figures + '\'' +
                ", strokes='" + strokes + '\'' +
                ", trash=" + trash +
                ", dateLastModified=" + dateLastModified +
                ", version=" + version +
                ", isPublic=" + isPublic +
                ", permissionType='" + permissionType + '\'' +
                '}';
    }

    public Timestamp viewDateLastModified() {
        return Timestamp.from(dateLastModified);
    }
    public Timestamp viewDataCreacio() {
        return Timestamp.from(dataCreacio);
    }

    public String getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
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

    public void setDataCreacio(Instant dataCreacio) {
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

    public Instant getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Instant dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
