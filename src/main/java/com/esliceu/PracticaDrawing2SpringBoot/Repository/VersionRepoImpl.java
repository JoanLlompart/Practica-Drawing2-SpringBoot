package com.esliceu.PracticaDrawing2SpringBoot.Repository;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Repository
public class VersionRepoImpl implements VersionRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

  /*  @Override
    public boolean newVersionOfCanvas(String nameCanvas, Version version) {
        try {
            String canvasQuery = "SELECT idObjectes FROM Canvas WHERE idObjectes = ? AND user_email = ?";
            int idCanvas = jdbcTemplate.queryForObject(canvasQuery, Integer.class, version.getIdDraw(), version.getUser_email());
            if (idCanvas > 0) { // Si se encuentra una coincidencia en Canvas
                String insertQuery = "INSERT INTO Version (idDraw, figuresJSON, strokesJSON, dateLastModified, user_email, numberObject) " +
                        "VALUES (?, ?, ?, NOW(), ?, ?)";
                // Ejecutar la consulta de inserción
                int rowsAffected = jdbcTemplate.update(insertQuery, version.getIdDraw(), version.getFigures(), version.getStrokes(),
                        version.getUser_email(), version.getNumberObject());
                return rowsAffected > 0;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la nova versio", e);
        }
    }

   */

    /*
    @Override
    public List<Version> getVersionsByIdDraw(int idDraw) {
        try {
            String query = "SELECT * FROM Version WHERE idDraw = ?";
            List<Version> versionList= jdbcTemplate.query(query,
                    new BeanPropertyRowMapper<>(Version.class),idDraw);
            System.out.println("Versions");


            Instant dateLastModified = null;
            Timestamp dateLastModifiedTimestamp = resultSet.getTimestamp("dateLastModified");
            if (dateLastModifiedTimestamp != null) {
                dateLastModified = dateLastModifiedTimestamp.toInstant();
            }

            return versionList;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las versiones de idDraw " + idDraw, e);
        }
}


     */
    @Override
    public List<Version> getVersionsByIdDraw(int idDraw) {
        try {
            String sql = "SELECT * FROM Version WHERE idDraw = ?";
            List<Version> versionList = jdbcTemplate.query(sql, (rs, rowNum) -> {
                Version version = new Version();
                version.setIdDraw(rs.getInt("idDraw"));
                version.setFigures(rs.getString("figuresJSON"));
                version.setStrokes(rs.getString("strokesJSON"));
                version.setNumberObject(rs.getInt("numberObject"));
                version.setUser_email(rs.getString("user_email"));

                // Convertir java.sql.Date a java.time.Instant
                Instant dateLastModified = null;
                Timestamp dateLastModifiedTimestamp = rs.getTimestamp("dateLastModified");
                if (dateLastModifiedTimestamp != null) {
                    dateLastModified = dateLastModifiedTimestamp.toInstant();
                }
                version.setDateLastModified(dateLastModified);
                version.setIdVersion(rs.getInt("idVersion"));
                return version;
            }, idDraw);
            return versionList;
        } catch (
                EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }
    @Override
    public boolean verifyUserCanWrite(Version version) {
        String sqlPermission = "SELECT COUNT(*) FROM Permission WHERE permissionType ='W' AND idCanvas=? AND user_email= ?";
        int permisCount = jdbcTemplate.queryForObject(sqlPermission, Integer.class, version.getIdDraw(), version.getUser_email());
        String slqOwner = "SELECT COUNT(*) FROM Canvas WHERE idObjectes =? AND user_email= ? AND trash =false";
        permisCount = jdbcTemplate.queryForObject(slqOwner, Integer.class, version.getIdDraw(), version.getUser_email());
        if (permisCount > 0) {
            //El usuari o be es el propietario o te permis per editar el dibuix.
            System.out.println("te permis");
            return true;
        } else {
            System.out.println("No te permis");
            //No te permisos per per realitzar modificacions
            return false;
        }
    }
    @Override
    public boolean verifyUserCanRead(Version version) {
        String sqlPermission = "SELECT COUNT(*) FROM Permission WHERE permissionType ='R' AND idCanvas=? AND user_email= ?";
        int permisCount = jdbcTemplate.queryForObject(sqlPermission, Integer.class, version.getIdDraw(), version.getUser_email());
        String slqOwner = "SELECT COUNT(*) FROM Canvas WHERE idObjectes =? AND user_email= ? AND trash =false";
        permisCount = jdbcTemplate.queryForObject(slqOwner, Integer.class, version.getIdDraw(), version.getUser_email());
        if (permisCount > 0) {
            //El usuari o be es el propietario o te permis per visualitzar el dibuix.
            System.out.println("te permis de Lectura");
            return true;
        } else {
            System.out.println("No te permis de Escritura");
            //No te permisos per per realitzar Visualitzar el canvas
            return false;
        }
    }

    @Override
    public Version getLastVersionByCanvasId(int idCanvas) {
        String sql = "SELECT * FROM Version WHERE idDraw = ? AND dateLastModified = (SELECT MAX(dateLastModified) FROM Version WHERE idDraw = ?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{idCanvas, idCanvas}, (rs, rowNum) -> {
            Version version = new Version();
            version.setIdVersion(rs.getInt("idVersion"));
            version.setIdDraw(rs.getInt("idDraw"));
            version.setFigures(rs.getString("figuresJSON"));
            version.setStrokes(rs.getString("strokesJSON"));

            Timestamp timestamp=rs.getTimestamp("dateLastModified");
            version.setDateLastModified(Instant.ofEpochMilli(timestamp.getTime()));
            System.out.println("Instant time : " + version.getDateLastModified());
            version.setNumberObject(rs.getInt("numberObject"));
            version.setUser_email(rs.getString("user_email"));
            return version;
        });
    }


    @Override
    public String getNameCanvasById(int idCanvas) {
        String sql = "SELECT nameCanvas FROM Canvas WHERE idObjectes = ?;";
        return jdbcTemplate.queryForObject(sql,String.class,idCanvas);
    }

    @Override
    public boolean changeNameAndVisibility(String nameCanvasNew, boolean isPublic, int idCanvas) {
        String sqlUpdate = "UPDATE Canvas " +
                "SET nameCanvas = ?, public = ? " +
                "WHERE idObjectes = ?;";
        System.out.println("name new " + nameCanvasNew);
        int count = jdbcTemplate.update(sqlUpdate,nameCanvasNew,isPublic,idCanvas);
        if (count >0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean newVersionOfCanvas(String nameCanvas, Version version, boolean isPublic) {
        /*
            String sqlPermissionCheck = "SELECT COUNT(*) " +
                    "FROM Permission AS p " +
                    "WHERE p.user_email = ? " +
                    "    AND p.idCanvas = ? " +
                    "    AND p.permissionType = 'W'";
            //String sqlPermissionCheck = "SELECT COUNT(*) FROM Permission WHERE idCanvas = ? AND user_email = ? ";

            //int permissionCount = jdbcTemplate.queryForObject(sqlPermissionCheck, Integer.class, version.getIdDraw(), version.getUser_email());
            int permissionCount = jdbcTemplate.queryForObject(sqlPermissionCheck, Integer.class, version.getUser_email(),
                    version.getIdDraw());
            if (permissionCount > 0) {
        */
        try {
            // El usuario tiene permisos, realizar la inserción de la versión
            String sqlInsertVersion = "INSERT INTO Version (idDraw, figuresJSON, strokesJSON, dateLastModified, user_email, numberObject) VALUES (?, ?, ?, NOW(), ?, ?)";
            jdbcTemplate.update(sqlInsertVersion, version.getIdDraw(), version.getFigures(), version.getStrokes(), version.getUser_email(), version.getNumberObject());
            // Actualizar el nombre del Canvas en la tabla Canvas
            String sqlUpdateCanvasName = "UPDATE Canvas SET nameCanvas = ? WHERE idObjectes = ?";
            jdbcTemplate.update(sqlUpdateCanvasName, nameCanvas, version.getIdDraw());
            return true;

        } catch (Exception e) {
            throw new RuntimeException("Error al crear la nova versio", e);
        }
    }
}
