package com.esliceu.PracticaDrawing2SpringBoot.Repository;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Canvas;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Figure;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Strokes;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.User;
import com.esliceu.PracticaDrawing2SpringBoot.Services.CanvasServices;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql = "SELECT * FROM Canvas";
      /*  List<Canvas> canvasList = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Canvas.class));
        return canvasList;

       */

        ResultSetExtractor<String> jsonFigures = new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                // Guardar el valor de la primera fila
                if (rs.next()) {
                    rs.getString("figuresJSON");
                }

                return null;
            }
        };

        String jsonF = jdbcTemplate.query(sql, jsonFigures);


     /*   String sql = "SELECT * FROM Canvas WHERE idObjectes = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{canvasId}, (resultSet, rowNum) -> {
            Canvas canvas = new Canvas();
            canvas.setIdObjectes(resultSet.getInt("idObjectes"));
            canvas.setNameCanvas(resultSet.getString("nameCanvas"));
            // ... otras asignaciones de campos Canvas

            String figureJson = resultSet.getString("figuresJSON");
            String strokesJson = resultSet.getString("strokesJSON");

            // Convertir figureJson y strokesJson a listas de Figure y Strokes
            Gson gson = new Gson();
            Type figureListType = new TypeToken<List<Figure>>() {
            }.getType();
            Type strokesListType = new TypeToken<List<Strokes>>() {
            }.getType();

            List<Figure> figureList = gson.fromJson(figureJson, figureListType);
            List<Strokes> strokesList = gson.fromJson(strokesJson, strokesListType);

            // Asignar las listas a la instancia de Canvas
            canvas.setFigures(figureList);
            canvas.setStrokes(strokesList);

            return canvas;
        });

      */
        return null;
    }
    @Override
    public boolean removeCanvas(int idCanvas, String emailSessio) {
       //  String query = "DELETE FROM Canvas WHERE idObjectes = ? AND user_id = (SELECT id FROM user WHERE email = ?)";
        //int rowsAffected = jdbcTemplate.update(query, idCanvas, emailSessio);
        //si se elimina algun registre retornara true, si no False perque no se ha eliminat res.
       // return rowsAffected > 0;
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
