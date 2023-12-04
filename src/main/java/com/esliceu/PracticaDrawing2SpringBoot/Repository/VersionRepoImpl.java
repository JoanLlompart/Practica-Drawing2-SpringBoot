package com.esliceu.PracticaDrawing2SpringBoot.Repository;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class VersionRepoImpl implements VersionRepo{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public boolean newVersionOfCanvas(String nameCanvas, Version version) {
        try {
            String canvasQuery = "SELECT idObjectes FROM Canvas WHERE idObjectes = ? AND user_email = ?";
            int idCanvas = jdbcTemplate.queryForObject(canvasQuery, Integer.class, version.getIdDraw(), version.getUser_email());
            if (idCanvas > 0) { // Si se encuentra una coincidencia en Canvas
                String insertQuery = "INSERT INTO Version (idDraw, figuresJSON, strokesJSON, dateLastModified, user_email, numberObject) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
                // Ejecutar la consulta de inserción
                int rowsAffected = jdbcTemplate.update(insertQuery, version.getIdDraw(), version.getFigures(), version.getStrokes(),
                        version.getDateLastModified(), version.getUser_email(), version.getNumberObject());
                return rowsAffected > 0;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la nova versio", e);
        }
    }
}
