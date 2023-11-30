package com.esliceu.PracticaDrawing2SpringBoot.Repository;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Canvas;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.User;
import com.esliceu.PracticaDrawing2SpringBoot.Exceptions.TakeCanvasException;
import com.esliceu.PracticaDrawing2SpringBoot.Services.CanvasServices;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
        try {
            String sql = "SELECT * FROM Canvas WHERE user_email = ?";
            List<Canvas> listCanvas= jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<>(Canvas.class),u.getEmail());
            System.out.println("llista canvas findByUser" + listCanvas);
            return listCanvas;
        } catch(TakeCanvasException e) {
            System.err.println("Error en el showCanvasAll");
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<Canvas> showAllCanvas() {
        try {
            String sql = "SELECT * FROM Canvas";
            List<Canvas> canvasList = jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<>(Canvas.class));
            return canvasList;
        } catch(TakeCanvasException e) {
            System.err.println("Error en el showCanvasAll");
            e.printStackTrace();
        }

     /*   try {
            String sql = "SELECT * FROM Canvas";
            List<Canvas> canvasList = jdbcTemplate.query(sql);
        } catch (SQLException e) {
            e.getSQLState();
        }
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
      */
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
    /*
    @Override
    public void saveCanvas(Canvas canvas, String strokesJson, String figureJson) {
        String sql = "INSERT INTO Canvas (nameCanvas, user_email, dataCreacio, numberObject, figuresJSON, strokesJSON) VALUES (?, ?, NOW(), ?, ?, ?)";
        jdbcTemplate.update(sql,
                canvas.getNameCanvas(),
                canvas.getUser_email(),
                //canvas.getDataCreacio(),
                canvas.getNumberObject(),
                figureJson,
                strokesJson
        );
    }

     */

    @Transactional
    public void saveCanvas(Canvas canvas, String strokesJson, String figureJson) {
        // Suponiendo que ya tienes acceso al usuario y su ID para la relación
        // y el canvas tiene los datos necesarios

        try {
            // Insertar un nuevo Canvas
            String insertCanvasQuery = "INSERT INTO Canvas (nameCanvas, dataCreacio, user_email, trash) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(insertCanvasQuery, canvas.getNameCanvas(), canvas.getDataCreacio(), canvas.getUser_email(), canvas.isTrash());

            // Obtener el ID del Canvas insertado
            Integer canvasId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

            // Insertar un nuevo registre en la taula de Version
            String insertVersionQuery = "INSERT INTO Version (idDraw, figuresJSON, strokesJSON, dateLastModified, user_email, numberObject) VALUES (?, ?, ?, NOW(), ?, ?)";
            jdbcTemplate.update(insertVersionQuery, canvasId, figureJson, strokesJson, canvas.getUser_email(), 1);
        } catch (Exception e) {
            // Manejar excepciones
            throw new RuntimeException("Error al guardar el Canvas", e);
        }
    }
    @Override
    public Canvas getCanvasById(int id) {
        try {
            String sql = "SELECT * FROM Canvas WHERE idObjectes = ?";
            Canvas canvas= jdbcTemplate.queryForObject(sql,
                    new BeanPropertyRowMapper<>(Canvas.class),id);
            System.out.println("Return canvas by id : " + canvas.toString());
            return canvas;
        } catch(TakeCanvasException e) {
            System.err.println("Error en el showCanvasAll");
            e.printStackTrace();
            return null;
        }

    }
}
