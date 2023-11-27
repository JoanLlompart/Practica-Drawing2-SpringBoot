package com.esliceu.PracticaDrawing2SpringBoot.Services;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Canvas;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.User;
import com.esliceu.PracticaDrawing2SpringBoot.Repository.CanvasRepo;
import com.esliceu.PracticaDrawing2SpringBoot.Repository.CanvasRepoImpl;
import com.esliceu.PracticaDrawing2SpringBoot.Repository.UserRepo;
import com.esliceu.PracticaDrawing2SpringBoot.Repository.UserRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CanvasServices {
    @Autowired
    CanvasRepo canvasRepo;
    @Autowired
    UserRepo userRepo;

    public void newCanvas(String strokesJson, String figureJson, String email, String nameCanvas) {
        try {
            Canvas canvas = new Canvas();
            canvas.setNameCanvas(nameCanvas);
            User u = userRepo.findByEmail(email);
            canvas.setUser(u);
            System.out.println("Nom de el canvas"+canvas.getNameCanvas());

            //parsearObjectes(strokesJson,figureJson);
            canvasRepo.saveCanvas(canvas,strokesJson,figureJson);
        } catch (Exception e) {
            System.err.println("Error en el al fer el nou canvas a CanvasService" + e.getMessage());
        }
    }
    public List<Canvas> showMyCanvas(String email) {
        User u = userRepo.findByEmail(email);
        //torna la llista amb els canvas que pertanyen a el usuari
        return canvasRepo.findByUser(u);
    }
    //genera un nom si el usuari no ha asignat un nom a el canvas.
    public String generarNom(String nameCanvas) {
        //a la hora de guardar hem de anar alerta que no es repetesqui el numero si
        // es repeteix generar un altre nom nou,per el usuari.
        int numAleatori = (int) (Math.random() * 99999);
        return nameCanvas = "Image" + numAleatori;
    }
    public boolean deleteCanvasById(int idDelete,String email) {
        //borrara els dibuixos i tornara true si ha anat be.
        return canvasRepo.removeCanvas(idDelete,email);
    }

    public List<Canvas> showAllCanvas() {
        return canvasRepo.showAllCanvas();
    }
    public Canvas getCanvas(int id) {
        return canvasRepo.getCanvasById(id);
    }
}
