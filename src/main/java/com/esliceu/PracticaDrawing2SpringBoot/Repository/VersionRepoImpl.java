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
            // Consulta para verificar si existe una coincidencia en Canvas con el idDraw proporcionado y el user_email
            String canvasQuery = "SELECT idObjectes FROM Canvas WHERE idObjectes = ? AND user_email = ?";
            int idCanvas = jdbcTemplate.queryForObject(canvasQuery, Integer.class, version.getIdDraw(), version.getUser_email());
            if (idCanvas > 0) { // Si se encuentra una coincidencia en Canvas
                // Consulta para insertar en la tabla Version
                String insertQuery = "INSERT INTO Version (idDraw, figuresJSON, strokesJSON, dateLastModified, user_email, numberObject) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";

                // Ejecutar la consulta de inserción
                int rowsAffected = jdbcTemplate.update(insertQuery, version.getIdDraw(), version.getFigures(), version.getStrokes(),
                        version.getDateLastModified(), version.getUser_email(), version.getNumberObject());

                return rowsAffected > 0; // Devolver true si al menos una fila fue afectada (es decir, si se realizó la inserción)
            } else {
                return false; // No se encontró una coincidencia en Canvas
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la nova versio", e);
        }

    }
}
