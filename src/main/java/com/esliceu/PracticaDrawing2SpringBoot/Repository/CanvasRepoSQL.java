package com.esliceu.PracticaDrawing2SpringBoot.Repository;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.Canvas;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CanvasRepoSQL implements CanvasRepo{
    @Autowired
    JdbcTemplate jdbcTemplate;
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
        String sql = "INSERT INTO Canvas (nameCanvas, user_id, dataCreacio, numberObject, figuresJSON, strokesJSON) VALUES (?, ?, NOW(), ?, ?, ?)";
        jdbcTemplate.update(sql,
                canvas.getNameCanvas(),
                canvas.getUser().getId(),
                //canvas.getDataCreacio(),
                canvas.getNumberObject(),
                figureJson,
                strokesJson
        );
    }
    @Override
    public Canvas getCanvasById(int id) {
        return null;
    }
}
