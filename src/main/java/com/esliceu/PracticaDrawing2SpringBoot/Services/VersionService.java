package com.esliceu.PracticaDrawing2SpringBoot.Services;

import com.esliceu.PracticaDrawing2SpringBoot.DTO.CanvasVersionDTO;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Version;
import com.esliceu.PracticaDrawing2SpringBoot.Repository.VersionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionService {
    @Autowired
    CanvasVersionDTO canvasVersionDTO;
    @Autowired
    VersionRepo versionRepo;
    public boolean newVersionCanvas(CanvasVersionDTO canvasVersionDTO) {
        try {
            //Els valors de canvasVersionDTO els asignam a la instancia de clase amb this.
            this.canvasVersionDTO = canvasVersionDTO;
            Version version = new Version();
            System.out.println("Service");
            System.out.println(canvasVersionDTO.toString());
            //Ara cream asignam els atributs a la clase version.
            version.setIdDraw(canvasVersionDTO.getIdObjectes());
            System.out.println(version.getIdDraw() +"IdDraw");
            version.setStrokes(canvasVersionDTO.getStrokes());
            version.setFigures(canvasVersionDTO.getFigures());
            version.setDateLastModified(canvasVersionDTO.getDateLastModified());//Treure data actual
            version.setNumberObject(canvasVersionDTO.getNumberObject());
            version.setUser_email(canvasVersionDTO.getUser_email());
            String nameCanvas = canvasVersionDTO.getNameCanvas();
            //Pasam tots els parametres de Version i el nameCanvas a la funcio de newVersionOfCanvas
            return versionRepo.newVersionOfCanvas(nameCanvas, version);
        }catch (Exception e) {
            System.out.println(e.getCause()+ e.getLocalizedMessage());
            return false;
        }
    }
    public List<Version> getAllVersion(int idObjectes) {
        return versionRepo.getVersionsByIdDraw(idObjectes);
    }

}
