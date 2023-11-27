package com.esliceu.PracticaDrawing2SpringBoot.Repository;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.Canvas;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CanvasRepoImpl implements CanvasRepo {
    @Override
    public List<Canvas> findByUser(User u) {
        return null;
    }

    @Override
    public List<Canvas> showAllCanvas() {
        return null;
    }

    @Override
    public boolean removeCanvas(int idCanvas, String emailSessio) {
        return false;
    }

    @Override
    public void saveCanvas(Canvas canvas, String strokesJson, String figureJson) {

    }

    @Override
    public Canvas getCanvasById(int id) {
        return null;
    }
}
