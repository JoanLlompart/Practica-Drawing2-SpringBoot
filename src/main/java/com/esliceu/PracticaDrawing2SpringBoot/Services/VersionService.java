package com.esliceu.PracticaDrawing2SpringBoot.Services;

import com.esliceu.PracticaDrawing2SpringBoot.DTO.CanvasVersionDTO;
import com.esliceu.PracticaDrawing2SpringBoot.Repository.VersionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VersionService {
    @Autowired
    CanvasVersionDTO canvasVersionDTO;
    @Autowired
    VersionRepo versionRepo;
    public boolean newVersionCanvas(CanvasVersionDTO canvasVersionDTO) {
        //Els valors de canvasVersionDTO els asignam a la instancia de clase amb this.
        this.canvasVersionDTO =canvasVersionDTO;

        return false;
    }

}
