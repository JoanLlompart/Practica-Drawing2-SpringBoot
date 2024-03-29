package com.esliceu.PracticaDrawing2SpringBoot.Repository;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Canvas;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.User;
import java.util.List;

public interface CanvasRepo {
    List<Canvas> findByUser(User u);
    List<Object[]> showAllCanvas(String email);
    boolean removeCanvas(int idCanvas,String emailSessio);
    boolean goToTrash(int idCanvas,String emailSessio);
    boolean sendOutOfTrash(int idCanvas,String emailSessio);

    void saveCanvas(Canvas canvas,String strokesJson, String figureJson);
    List<Object>  getCanvasById(int id);
    List<Object[]> showMyTrash(String emailSessio);

    Canvas compareStatusCanvas(int idCanvas);

    boolean visibilityCanvas(int idObjectes);
    List<Object[]> showCanvasUserHavePermission(String email);
}
