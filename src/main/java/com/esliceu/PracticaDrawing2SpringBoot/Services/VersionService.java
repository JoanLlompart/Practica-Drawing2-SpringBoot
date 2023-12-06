package com.esliceu.PracticaDrawing2SpringBoot.Services;

import com.esliceu.PracticaDrawing2SpringBoot.DTO.CanvasVersionDTO;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Version;
import com.esliceu.PracticaDrawing2SpringBoot.Repository.VersionRepo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionService {
    @Autowired
    CanvasVersionDTO canvasVersionDTO;
    @Autowired
    VersionRepo versionRepo;
    public boolean newVersionCanvas(CanvasVersionDTO canvasVersionDTO,String isPub) {
        try {
            //Els valors de canvasVersionDTO els asignam a la instancia de clase amb this.
            this.canvasVersionDTO = canvasVersionDTO;
            if (isPub == null) {
                canvasVersionDTO.setPublic(false);
            }else if (isPub.equals("on")) {
                canvasVersionDTO.setPublic(true);
            }
            System.out.println("public? " +canvasVersionDTO.isPublic());
            Version version = new Version();
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
            boolean isPublic = canvasVersionDTO.isPublic();
            //Pasam tots els parametres de Version i el nameCanvas a la funcio de newVersionOfCanvas
            // return versionRepo.newVersionOfCanvas(nameCanvas, version);
            if (versionRepo.verifyUserCanWrite(version)) {
                if (compareVersionChange(version,nameCanvas,isPublic)) {
                    return versionRepo.newVersionOfCanvas(nameCanvas, version,isPublic);
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



    public List<Version> getAllVersion(int idObjectes) {
        //if (versionRepo.verifyUserCanRead())
        return versionRepo.getVersionsByIdDraw(idObjectes);
    }
    public boolean compareVersionChange(Version version,String nameCanvas, boolean isPublic) {
        //Agafar la darrera Versio de el dibuix amb aquest id i la comparam els json.
        int idCanvas =version.getIdDraw();

        String nameCanvasNew=nameCanvas;

        //Comproba el nameCanvas actual en la base de dades per mirar si ha cambiat
        String nameCanvasOld = versionRepo.getNameCanvasById(idCanvas);
        Version vOld=versionRepo.getLastVersionByCanvasId(idCanvas);

        System.out.println(vOld.toString());

        String strokOld = vOld.getStrokes();
        System.out.println("Strokes old" + strokOld);
        String figuresOld = vOld.getFigures();
        System.out.println("figures Old " + figuresOld);

        String strokesNew = version.getStrokes();
        System.out.println("Strokes nou "+strokesNew);
        String figuresNew = version.getFigures();
        System.out.println("FIGURES NEW " + figuresNew);

        //boolean strokesJson = compareJSONContent(vOld.getStrokes(),canvasVersionDTO.getStrokes());
        //System.out.println("Strokes han cambiat ?? " + strokesJson);

        //boolean figuresJson = compareJSONContent(vOld.getFigures(),canvasVersionDTO.getFigures());
        //System.out.println("Figures ha cambiat ? " + figuresJson);

        boolean  strokesJson= strokesNew.equals(strokOld);
        boolean figuresJson = figuresNew.equals(figuresOld);

        System.out.println("boolean figures" + figuresJson);
        if (!figuresJson || !strokesJson) { //falta el nom
            //Todo: falta compara la visibilitat
            System.out.println("Els Json han cambiat");
            //Si els Json han cambiat guardam la nova versio a la base de dades.
            return true;
        }
        //Tenir en compte si el boolean de Public ha cambiat i tambe comparar els dos json a la vegada if s1.equals(s2) and ...
        return false;
    }

    /*
    public boolean compareVersionChange(Version version,String nameCanvas, boolean isPublic) {
        //Agafar la darrera Versio de el dibuix amb aquest id i la comparam els json.
        int idCanvas = canvasVersionDTO.getIdObjectes();
        String nameCanvasNew=canvasVersionDTO.getNameCanvas();

        //Comproba el nameCanvas actual en la base de dades per mirar si ha cambiat
        String nameCanvasOld = versionRepo.getNameCanvasById(idCanvas);
        Version vOld=versionRepo.getLastVersionByCanvasId(idCanvas);

        System.out.println(vOld.toString());

        String strokOld = vOld.getStrokes();
        System.out.println("Strokes old" + strokOld);
        String figuresOld = vOld.getFigures();
        System.out.println("figures Old " + figuresOld);

        String strokesNew = canvasVersionDTO.getStrokes();
        System.out.println("Strokes nou "+strokesNew);
        String figuresNew = canvasVersionDTO.getFigures();
        System.out.println("FIGURES NEW " + figuresNew);

        //boolean strokesJson = compareJSONContent(vOld.getStrokes(),canvasVersionDTO.getStrokes());
        //System.out.println("Strokes han cambiat ?? " + strokesJson);

        //boolean figuresJson = compareJSONContent(vOld.getFigures(),canvasVersionDTO.getFigures());
        //System.out.println("Figures ha cambiat ? " + figuresJson);

        boolean  strokesJson= strokesNew.equals(strokOld);
        boolean figuresJson = figuresNew.equals(figuresOld);

        System.out.println("boolean figures" + figuresJson);
        if (!figuresJson || !strokesJson) { //falta el nom
            //Todo: falta compara la visibilitat
            System.out.println("Els Json han cambiat");
            //Si els Json han cambiat guardam la nova versio a la base de dades.
            return true;
        }
        //Tenir en compte si el boolean de Public ha cambiat i tambe comparar els dos json a la vegada if s1.equals(s2) and ...
        return false;
    }
     */



   /* public static boolean compareJSONContent(String json1, String json2) {
        try {
            System.out.println("Json1" + json1);
            System.out.println("Json2" + json2);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode tree1 = objectMapper.readTree(json1);
            JsonNode tree2 = objectMapper.readTree(json2);

            // Compara los contenidos de los JSON convertidos a objetos Java
            return tree1.toString().equals(tree2.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No ha comparat be els JSON");
            return false;
        }

    }

    */

}
