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
    private boolean isPublic;

    public ModifyCanvasVersionDTO(){
    }

    public ModifyCanvasVersionDTO(String strokesData, String figuresData, String nameCanvas, boolean isPublic) {
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

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
}
