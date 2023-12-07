package com.esliceu.PracticaDrawing2SpringBoot.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.io.Serial;

@Component
public class ModifyCanvasVersionDTO {
    private String strokesData;
    private String figuresData;
    @JsonProperty("nomDibuix")
    private String nameCanvas;
    private String isPublic;

    public ModifyCanvasVersionDTO(){
    }
    public ModifyCanvasVersionDTO(String strokesData, String figuresData, String nameCanvas, String isPublic) {
        this.strokesData = strokesData;
        this.figuresData = figuresData;
        this.nameCanvas = nameCanvas;
        this.isPublic = isPublic;
    }

    @Override
    public String toString() {
        return "ModifyCanvasVersionDTO{" +
                "strokesData='" + strokesData + '\'' +
                ", figuresData='" + figuresData + '\'' +
                ", nameCanvas='" + nameCanvas + '\'' +
                ", isPublic='" + isPublic + '\'' +
                '}';
    }

    public String getStrokesData() {

        return strokesData;
    }

    public void setStrokesData(String strokesData) {
        this.strokesData = strokesData;
    }

    public String getFiguresData() {
        return figuresData;
    }

    public void setFiguresData(String figuresData) {
        this.figuresData = figuresData;
    }

    public String getNameCanvas() {
        return nameCanvas;
    }

    public void setNameCanvas(String nameCanvas) {
        this.nameCanvas = nameCanvas;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }
}
