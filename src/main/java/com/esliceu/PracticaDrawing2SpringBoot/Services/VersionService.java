package com.esliceu.PracticaDrawing2SpringBoot.Services;

import com.esliceu.PracticaDrawing2SpringBoot.DTO.CanvasVersionDTO;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Canvas;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Version;
import com.esliceu.PracticaDrawing2SpringBoot.Repository.CanvasRepo;
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
    @Autowired
    CanvasServices canvasServices;

    @Autowired
    CanvasRepo canvasRepo;
    public boolean newVersionCanvas(CanvasVersionDTO canvasVersionDTO,String sessionEmail) {
        try {
            //Els valors de canvasVersionDTO els asignam a la instancia de clase amb this.
            this.canvasVersionDTO = canvasVersionDTO;

            System.out.println("public? " +canvasVersionDTO.isPublic());
            Version version = new Version();
            //Ara cream asignam els atributs a la clase version.
            version.setIdDraw(canvasVersionDTO.getIdObjectes());
            System.out.println(version.getIdDraw() +"IdDraw");
            version.setStrokes(canvasVersionDTO.getStrokes());
            version.setFigures(canvasVersionDTO.getFigures());
            version.setDateLastModified(canvasVersionDTO.getDateLastModified());//Treure data actual

            version.setNumberObject(canvasVersionDTO.getNumberObject());
            version.setUser_email(canvasVersionDTO.getUser_email());
            String nameCanvas = canvasVersionDTO.getNameCanvas();
            boolean isPublic = canvasVersionDTO.isPublic();
            //Pasam tots els parametres de Version i el nameCanvas a la funcio de newVersionOfCanvas
            //Comproba si te permissos de escritura
            if (versionRepo.verifyUserCanWrite(version,sessionEmail)) {
                //Comproba que el els JSON han cambiat si no, no guarda els canvis.
                if (compareVersionChange(version,nameCanvas,isPublic)) {
                    System.out.println("Els Json han cambiat");
                    return versionRepo.newVersionOfCanvas(nameCanvas, version,isPublic);
                } else if (compareCanvasAtributtesChange(nameCanvas,isPublic,version.getIdDraw())) {
                    System.out.println("Els atributs han cambiat");
                    //Comproba que el nameCanvas o el public ha cambiat per actualitzar les dades de el canvas.
                    return versionRepo.changeNameAndVisibility(nameCanvas,isPublic,version.getIdDraw());
                } else {
                    System.out.println("No se ha modificado el Canvas");
                    return false;
                }
            } else {
                System.out.println("Fallo en Version Services");
                return false;
            }
        }catch (Exception e) {
            System.out.println(e.getCause()+ e.getLocalizedMessage());
            throw new RuntimeException();
        }
    }

    private boolean compareCanvasAtributtesChange(String nameCanvas, boolean isPublic, int idDraw) {
        Canvas c =canvasRepo.compareStatusCanvas(idDraw);
        //Asigna el valor actual de la base de dades per comprobar que ha cambiat.
        String nameCanvasOld = c.getNameCanvas();
        boolean publicOld = c.isPublicDraw();
        System.out.println("ANTES name " +nameCanvasOld);
        System.out.println("Nou name " + nameCanvas);

        //Comproba si ha cambiat la visibilitat
        boolean publicChange = publicOld == isPublic;
        System.out.println("Public change? " + publicChange);

        //Comproba el nameCanvas actual de la base de dades per mirar si ha cambiat
        boolean nameCanvChange = nameCanvas.equals(nameCanvasOld);
        System.out.println("bool cambi de nom ? " + nameCanvChange);
        return (!publicChange || !nameCanvChange);
    }


    public List<Version> getAllVersion(int idObjectes) {
        return versionRepo.getVersionsByIdDraw(idObjectes);
    }
    public boolean compareVersionChange(Version version,String nameCanvas, boolean isPublic) {
        //Agafar la darrera Versio de el dibuix amb aquest id i la comparam els json.
        int idCanvas =version.getIdDraw();
        Version vOld=versionRepo.getLastVersionByCanvasId(idCanvas);

        String strokOld = vOld.getStrokes();
        String figuresOld = vOld.getFigures();
        String strokesNew = version.getStrokes();
        String figuresNew = version.getFigures();
        System.out.println("FIGURES NEW " + figuresNew);
        System.out.println("figures Old " + figuresOld);
        boolean  strokesJson= strokesNew.equals(strokOld);
        boolean figuresJson = figuresNew.equals(figuresOld);
        System.out.println("boolean figures  " + figuresJson);
        System.out.println("boolean strokes : " + strokesJson);

        //Tenir en compte si el boolean de Public ha cambiat i tambe comparar els dos json a la vegada if s1.equals(s2) and ...
        return (!figuresJson || !strokesJson);
    }

    public Version getVersionById(Version version, String email) {
        //todo hem de comprobar que te permisos de escritura
        //torna la versio de el idVersion que especificam
        version=versionRepo.getVersionByIdVersion(version.getIdVersion());
        System.out.println("------version--");
        System.out.println(version.toString());
        //si te permissos per fer la copia retorna una version i si no null.
        return (verifyCanCopyVersion(version,email)) ? version : null;
    }

    public boolean verifyCanCopyVersion(Version version, String email) {
        //comproba si el dibuix es public, o el usuari que
        // fa la copia te permissos o es el propietari.
        if (versionRepo.verifyUserCanRead(version,email)) {
            //te permisos de lectura, escritura o es el propietari.
            return true;
        }
        //comproba si el dibuix es public
        boolean publicDraw =canvasServices.isCanvasPublic(version.getIdDraw());
        return publicDraw;
    }
}
