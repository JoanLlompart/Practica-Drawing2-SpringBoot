package com.esliceu.PracticaDrawing2SpringBoot.Repository;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.Canvas;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Version;
import com.esliceu.PracticaDrawing2SpringBoot.Exceptions.NotYourCanvasException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RecursiveTask;

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
        System.out.println("userEmail" + version.getUser_email());
        System.out.println("idCanvas" + version.getIdDraw());

       /* String sql = "SELECT COUNT(*) " +
                "FROM Permission AS p " +
                "WHERE " +
                "    (p.user_email = ? AND p.idCanvas = ? AND p.permissionType = 'W') " +
                "    OR " +
                "    ( " +
                "        p.user_email = ? " +
                "        AND p.idCanvas = ? " +
                "        AND EXISTS ( " +
                "            SELECT 1 " +
                "            FROM Canvas AS c " +
                "            WHERE c.idObjectes = ? AND c.user_email = ? " +
                "        ) " +
                "    )";
        int permisCount = jdbcTemplate.queryForObject(sql, Integer.class, version.getUser_email(),
                version.getIdDraw(),version.getUser_email(),version.getIdDraw(),version.getIdDraw(),version.getUser_email());

        */

        String sqlPermission = "SELECT COUNT(*) FROM Permission WHERE permissionType ='W' AND idCanvas=? AND user_email= ?";
        int permisCount = jdbcTemplate.queryForObject(sqlPermission,Integer.class,version.getIdDraw(),version.getUser_email());
        System.out.println("permisCount primera " + permisCount);

        String slqOwner = "SELECT COUNT(*) FROM Canvas WHERE idObjectes =? AND user_email= ? AND trash =false";
        permisCount = jdbcTemplate.queryForObject(slqOwner,Integer.class,version.getIdDraw(),version.getUser_email());

        System.out.println("permisCount Fi" + permisCount);
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
        String sql = "SELECT COUNT(*) " +
                "FROM Permission AS p " +
                "WHERE " +
                "    (p.user_email = ? AND p.idCanvas = ? AND p.permissionType = 'R') " +
                "    OR " +
                "    ( " +
                "        p.user_email = ? " +
                "        AND p.idCanvas = ? " +
                "        AND EXISTS ( " +
                "            SELECT 1 " +
                "            FROM Canvas AS c " +
                "            WHERE c.idObjectes = ? AND c.user_email = ? " +
                "        ) " +
                "    )";
        int permisCount = jdbcTemplate.queryForObject(sql, Integer.class, version.getUser_email(),
                version.getIdDraw(),version.getUser_email(),version.getIdDraw(),version.getIdDraw(),version.getUser_email());
        if (permisCount > 0) {
            //El usuari o be es el propietario o te permis per editar el dibuix.
            return true;
        } else {
            //No te permisos per veure el dibuix
            return false;
        }
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
         /*   } else {
                throw new NotYourCanvasException("No tens permis per modificar el canvas");
            }
          */
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la nova versio", e);
        }
        return true;
    }

}
