package com.esliceu.PracticaDrawing2SpringBoot.Repository;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.Canvas;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Permission;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.User;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Version;
import com.esliceu.PracticaDrawing2SpringBoot.Exceptions.TakeCanvasException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class CanvasRepoSQL implements CanvasRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Canvas> findByUser(User u) {
        try {
            String sql = "SELECT * FROM Canvas WHERE user_email = ?";
            List<Canvas> listCanvas = jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<>(Canvas.class), u.getEmail());
            System.out.println("llista canvas findByUser" + listCanvas);
            return listCanvas;
        } catch (TakeCanvasException e) {
            System.err.println("Error en el showCanvasAll");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Object[]> showAllCanvas(String sessionEmail) {
        String selectAllCanvasQuery = "SELECT c.idObjectes, c.nameCanvas, c.dataCreacio, c.user_email, latestVersion.figuresJSON, latestVersion.strokesJSON, latestVersion.dateLastModified " +
                "FROM Canvas c " +
                "INNER JOIN ( " +
                "    SELECT idDraw, MAX(dateLastModified) AS MaxDate " +
                "    FROM Version " +
                "    GROUP BY idDraw " +
                ") latestVersionDate ON c.idObjectes = latestVersionDate.idDraw " +
                "INNER JOIN Version latestVersion ON latestVersionDate.idDraw = latestVersion.idDraw AND latestVersionDate.MaxDate = latestVersion.dateLastModified " +
                "WHERE ((c.trash = false AND c.public = true) OR (c.user_email = ? AND c.public = false)) " +
                "AND c.trash = false " +
                "ORDER BY c.idObjectes;";
        try {
            return jdbcTemplate.query(selectAllCanvasQuery, new Object[]{sessionEmail}, (resultSet, i) -> {
                int canvasId = resultSet.getInt("idObjectes");
                String nameCanvas = resultSet.getString("nameCanvas");
                //Instant dataCreacio = resultSet.getTimestamp("dataCreacio").toInstant();
                String userEmail = resultSet.getString("user_email");
                String figuresJSON = resultSet.getString("figuresJSON");
                String strokesJSON = resultSet.getString("strokesJSON");
                //Instant dateLastModified = resultSet.getTimestamp("dateLastModified").toInstant();
                Instant dataCreacio = null;
                Timestamp dataCreacioTimestamp = resultSet.getTimestamp("dataCreacio");
                if (dataCreacioTimestamp != null) {
                    dataCreacio = dataCreacioTimestamp.toInstant();
                }

                Instant dateLastModified = null;
                Timestamp dateLastModifiedTimestamp = resultSet.getTimestamp("dateLastModified");
                if (dateLastModifiedTimestamp != null) {
                    dateLastModified = dateLastModifiedTimestamp.toInstant();
                }
                // Crear objetos Canvas y Version y almacenarlos en un array de objetos
                Canvas canvas = new Canvas();
                canvas.setIdObjectes(canvasId);
                canvas.setNameCanvas(nameCanvas);
                canvas.setDataCreacio(dataCreacio);
                canvas.setUser_email(userEmail);

                Version version = new Version();
                version.setFigures(figuresJSON);
                version.setStrokes(strokesJSON);
                version.setDateLastModified(dateLastModified);
                //Permission p = new Permission();

                // Toran un array de objectes amb el Canvas i la Version asociada
                return new Object[]{canvas, version};
            });
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Object[]> showCanvasUserHavePermission(String sessionEmail) {
        String selectAllCanvasQuery = "SELECT c.idObjectes, c.nameCanvas, c.dataCreacio, c.user_email as Propietari," +
                " latestVersion.figuresJSON, latestVersion.strokesJSON, latestVersion.dateLastModified," +
                "pe.permissionType FROM Canvas c " +
                "INNER JOIN ( SELECT idDraw, MAX(dateLastModified) AS MaxDate FROM Version GROUP BY idDraw ) " +
                "latestVersionDate ON c.idObjectes = latestVersionDate.idDraw " +
                "INNER JOIN Version latestVersion ON latestVersionDate.idDraw = latestVersion.idDraw " +
                "AND latestVersionDate.MaxDate = latestVersion.dateLastModified " +
                "LEFT JOIN Permission pe ON pe.idCanvas = c.idObjectes and latestVersion.idDraw = pe.idCanvas " +
                "WHERE (pe.user_email= ?) ORDER BY c.idObjectes;";
        try {
            return jdbcTemplate.query(selectAllCanvasQuery, new Object[]{sessionEmail}, (resultSet, i) -> {
                int canvasId = resultSet.getInt("idObjectes");
                String nameCanvas = resultSet.getString("nameCanvas");
                //Instant dataCreacio = resultSet.getTimestamp("dataCreacio").toInstant();
                String userEmail = resultSet.getString("Propietari");
                String figuresJSON = resultSet.getString("figuresJSON");
                String strokesJSON = resultSet.getString("strokesJSON");
                String permission = resultSet.getString("permissionType");
                //Instant dateLastModified = resultSet.getTimestamp("dateLastModified").toInstant();
                Instant dataCreacio = null;
                Timestamp dataCreacioTimestamp = resultSet.getTimestamp("dataCreacio");
                if (dataCreacioTimestamp != null) {
                    dataCreacio = dataCreacioTimestamp.toInstant();
                }

                Instant dateLastModified = null;
                Timestamp dateLastModifiedTimestamp = resultSet.getTimestamp("dateLastModified");
                if (dateLastModifiedTimestamp != null) {
                    dateLastModified = dateLastModifiedTimestamp.toInstant();
                }
                // Crear objetos Canvas y Version y almacenarlos en un array de objetos
                Canvas canvas = new Canvas();
                canvas.setIdObjectes(canvasId);
                canvas.setNameCanvas(nameCanvas);
                canvas.setDataCreacio(dataCreacio);
                canvas.setUser_email(userEmail);

                Version version = new Version();
                version.setFigures(figuresJSON);
                version.setStrokes(strokesJSON);
                version.setDateLastModified(dateLastModified);
                Permission p = new Permission();
                p.setPermissionType(permission);
                p.setIdCanvas(canvasId);
                p.setUser_email(userEmail);
                // Toran un array de objectes amb el Canvas i la Version asociada
                return new Object[]{canvas, version,p};//modificat
                //return new Object[]{canvas, version,permission};
            });
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Exception a showCanvasUserHavePermission");
            return Collections.emptyList();
        }
    }

    @Override
    public boolean removeCanvas(int idCanvas, String emailSessio) {
        String deleteVersionQuery = "DELETE FROM Version WHERE idDraw = ? AND user_email = ?";
        String deleteCanvasQuery = "DELETE FROM Canvas WHERE idObjectes = ? AND user_email = ?";
        // try {
        int deletedVersions = jdbcTemplate.update(deleteVersionQuery, idCanvas, emailSessio);
        int deletedCanvas = jdbcTemplate.update(deleteCanvasQuery, idCanvas, emailSessio);
        // Si se eliminó el lienzo y al menos una versión asociada
        return deletedCanvas > 0 && deletedVersions > 0;
    }

    @Override
    public boolean goToTrash(int idCanvas, String emailSessio) {
        //si es manda a la paperera si es public deixara de ser ho i tambe se posara amb false.
        String sql = "UPDATE Canvas SET trash = true, public = false " +
                "WHERE idObjectes = ? AND user_email = ?";
        try {
            int columnesCambiades = jdbcTemplate.update(sql, idCanvas, emailSessio);
            // Retorna true si se han actualitzat els camps correctament.
            return columnesCambiades > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean sendOutOfTrash(int idCanvas, String emailSessio) {
        //Si esta a la papelera i antes era puclic que pasa???
        String sql = "UPDATE Canvas SET trash = false, public = false " +
                "WHERE idObjectes = ? AND user_email = ?";
        try {
            int columnesCambiades = jdbcTemplate.update(sql, idCanvas, emailSessio);
            // Retorna true si se han actualitzat els camps correctament.
            return columnesCambiades > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public void saveCanvas(Canvas canvas, String strokesJson, String figureJson) {
        //Nomes valid per guardar el canvas per primer pic, si no se actualitzara la data de creacio per ara.
        try {
            // Insertar un nuevo Canvas
            String insertCanvasQuery = "INSERT INTO Canvas (nameCanvas, dataCreacio, user_email, trash, public) VALUES (?, NOW(), ?, ?,?)";
            jdbcTemplate.update(insertCanvasQuery, canvas.getNameCanvas(), canvas.getUser_email(), canvas.isTrash(), canvas.isPublicDraw());
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
    public List<Object> getCanvasById(int id) {
        String selectSQL = "SELECT c.nameCanvas, c.dataCreacio, c.user_email, v.figuresJSON, v.strokesJSON, v.dateLastModified " +
                "FROM Canvas c " +
                "INNER JOIN Version v ON c.idObjectes = v.idDraw " +
                "WHERE c.idObjectes = ? " + // Obtener el lienzo por su ID
                "ORDER BY v.dateLastModified DESC " + // Ordenar por la fecha de modificación más reciente
                "LIMIT 1"; // Agafa nomes el registre de la darrera versio.
        return jdbcTemplate.queryForObject(selectSQL, new Object[]{id}, (resultSet, i) -> {
            Instant dataCreacio = null;
            Timestamp dataCreacioTimestamp = resultSet.getTimestamp("dataCreacio");
            if (dataCreacioTimestamp != null) {
                dataCreacio = dataCreacioTimestamp.toInstant();
            }
            Instant dateLastModified = null;
            Timestamp dateLastModifiedTimestamp = resultSet.getTimestamp("dateLastModified");
            if (dateLastModifiedTimestamp != null) {
                dateLastModified = dateLastModifiedTimestamp.toInstant();
            }
            Canvas canvas = new Canvas();
            canvas.setIdObjectes(id);
            canvas.setNameCanvas(resultSet.getString("nameCanvas"));
            //Instant dataCreacio = resultSet.getTimestamp("dataCreacio").toInstant();
            canvas.setDataCreacio(dataCreacio);
            canvas.setUser_email(resultSet.getString("user_email"));

            Version version = new Version();
            version.setFigures(resultSet.getString("figuresJSON"));
            version.setStrokes(resultSet.getString("strokesJSON"));
            //Instant dateLastModified = resultSet.getTimestamp("dateLastModified").toInstant();

            // Convertir Timestamp a Instant
            version.setDateLastModified(dateLastModified);
            List<Object> canvasAndVersion = new ArrayList<>();
            canvasAndVersion.add(canvas);
            canvasAndVersion.add(version);
            return canvasAndVersion;
        });
    }

    @Override
    public List<Object[]> showMyTrash(String emailSessio) {

        String selectMyTrashQuery = "SELECT c.idObjectes, c.nameCanvas, c.dataCreacio, c.user_email, latestVersion.figuresJSON, latestVersion.strokesJSON, latestVersion.dateLastModified " +
                "FROM Canvas c " +
                "INNER JOIN ( " +
                "    SELECT idDraw, MAX(dateLastModified) AS MaxDate " +
                "    FROM Version " +
                "    GROUP BY idDraw " +
                ") latestVersionDate ON c.idObjectes = latestVersionDate.idDraw " +
                "INNER JOIN Version latestVersion ON latestVersionDate.idDraw = latestVersion.idDraw AND latestVersionDate.MaxDate = latestVersion.dateLastModified " +
                "WHERE c.user_email = ? AND c.trash = true " +
                "ORDER BY c.idObjectes;";
        try {
            return jdbcTemplate.query(selectMyTrashQuery, new Object[]{emailSessio}, (resultSet, i) -> {
                int canvasId = resultSet.getInt("idObjectes");
                String nameCanvas = resultSet.getString("nameCanvas");
                String userEmail = resultSet.getString("user_email");
                String figuresJSON = resultSet.getString("figuresJSON");
                String strokesJSON = resultSet.getString("strokesJSON");

                Instant dataCreacio = null;
                Timestamp dataCreacioTimestamp = resultSet.getTimestamp("dataCreacio");
                if (dataCreacioTimestamp != null) {
                    dataCreacio = dataCreacioTimestamp.toInstant();
                }

                Instant dateLastModified = null;
                Timestamp dateLastModifiedTimestamp = resultSet.getTimestamp("dateLastModified");
                if (dateLastModifiedTimestamp != null) {
                    dateLastModified = dateLastModifiedTimestamp.toInstant();
                }

                // Crear intancies Canvas i Version i les guarda en un array de object
                Canvas canvas = new Canvas();
                canvas.setIdObjectes(canvasId);
                canvas.setNameCanvas(nameCanvas);
                canvas.setDataCreacio(dataCreacio);
                canvas.setUser_email(userEmail);

                Version version = new Version();
                version.setFigures(figuresJSON);
                version.setStrokes(strokesJSON);
                version.setDateLastModified(dateLastModified);

                // Toran un array de objectes amb el Canvas i la Version asociada
                return new Object[]{canvas, version};
            });
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public Canvas compareStatusCanvas(int idCanvas) {
        String sql = "SELECT * FROM Canvas WHERE idObjectes=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{idCanvas}, (rs, rowNum) -> {
            Canvas canvas = new Canvas();
            canvas.setPublicDraw(rs.getBoolean("public"));
            canvas.setTrash(rs.getBoolean("trash"));
            canvas.setNameCanvas(rs.getString("nameCanvas"));
            Timestamp timestamp = rs.getTimestamp("dataCreacio");
            canvas.setDataCreacio(Instant.ofEpochMilli(timestamp.getTime()));
            System.out.println("Instant time : " + canvas.getDataCreacio());
            canvas.setUser_email(rs.getString("user_email"));
            return canvas;
        });
    }

    @Override
    public boolean visibilityCanvas(int idObjectes) {
        String sql = "SELECT public FROM `Canvas` WHERE idObjectes=?";
        return jdbcTemplate.queryForObject(sql, Boolean.class, idObjectes);
    }
}
