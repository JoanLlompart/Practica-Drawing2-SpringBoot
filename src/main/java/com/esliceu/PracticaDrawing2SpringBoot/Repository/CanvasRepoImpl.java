package com.esliceu.PracticaDrawing2SpringBoot.Repository;
/*
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Canvas;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Figure;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Strokes;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//@Repository

public class CanvasRepoImpl implements CanvasRepo {
    //contador per cada vegada que se instancia un nou canvas.
    int contador = 1;
  //  @Autowired
    static List<Figure> figureList = new ArrayList<>();
    //@Autowired
    static List<Strokes> strokesList = new ArrayList<>();
    //@Autowired
    static List<Canvas> canvasList = new ArrayList<>();

    @Override
    public List<Canvas> findByUser(User u) {
        List<Canvas> miLista = new ArrayList<>();
        for (Canvas c : canvasList) {
            if (c.getUser().equals(u)) {
                miLista.add(c);
            }
        }
        System.out.println(miLista);
        return miLista;
    }

    @Override
    public List<Canvas> showAllCanvas() {
        return canvasList;
    }

    @Override
    public boolean removeCanvas(int idCanvas, String emailSessio) {
        for (Canvas cRemove : canvasList) {
            if (cRemove.getIdObjectes() == idCanvas) {
                System.out.println("remove:" + cRemove.getIdObjectes());
                String canvasEmail = cRemove.getUser().getEmail();
                if (canvasEmail.equals(emailSessio)) {
                    canvasList.remove(cRemove);
                    return true;
                }
            }
        }
        System.out.println("No esta aturitzat per borrar");
        return false;
    }

    @Override
    public void saveCanvas(Canvas canvas, String strokesJson, String figureJson) {
        try {
            // Cream una instancia de Gson per poder utilitzar la llibreria.
            Gson gson = new Gson();
            //Parsetjam el JSON a la clase que pertany
            figureList = gson.fromJson(figureJson, new TypeToken<List<Figure>>() {
            }.getType());
            //strokesList = gson.fromJson(strokesJson, new TypeToken<List<Strokes>>() {}.getType());
            //System.out.println(strokesList);
            Type listType = new TypeToken<List<Strokes>>() {
            }.getType();
            List<Strokes> strokesList = gson.fromJson(strokesJson, listType);
            //System.out.println(figureList);
            // Agafam la fecha i hora actual
            LocalDateTime actualDate = LocalDateTime.now();
            // Convertim de LocalDateTime a Date
            Date date = Date.from(actualDate.atZone(ZoneId.systemDefault()).toInstant());
           // System.out.println("Fecha y hora actual (Date): " + date);
            canvas.setDataCreacio(date);
            canvas.setFigures(figureList);
            canvas.setStrokes(strokesList);
            canvas.setIdObjectes(contador);
            contador++;
            //System.out.println("canvas a DAOImpl " + canvas.toString());
            //System.out.println("Ha arribat a canvas dao");
            System.out.println("Estructure : "+canvas.viewStructure());
        } catch (Exception e) {
            System.err.println("No se ha pogut fer la conversio a JSON correctamente . " + e.getMessage());
        }
        try {
            System.out.println(canvas.toString());
            canvasList.add(canvas);
          //  System.out.println("Llista " + canvasList);
          //  System.out.println("---------------------");
            for (Canvas c : canvasList) {
           //     System.out.println(c.getIdObjectes());
            }
        } catch (Exception e) {
            System.err.println("Error en la llista de canvas, algo ha fallat." + e.getCause());
        }
    }
    @Override
    public Canvas getCanvasById(int id) {
        try {
            for (Canvas canvas : canvasList) {
                if (canvas.getIdObjectes() == id) {
                 //   System.out.println("ID de el canvas:" + canvas.getIdObjectes());
                    return canvas;
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage() + e.getCause() + e.getLocalizedMessage());
        }
        return null;
    }
}

 */