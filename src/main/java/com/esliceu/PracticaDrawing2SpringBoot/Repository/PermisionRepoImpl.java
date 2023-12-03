package com.esliceu.PracticaDrawing2SpringBoot.Repository;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.Permission;
import com.esliceu.PracticaDrawing2SpringBoot.Exceptions.InvalidPermissionTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class PermisionRepoImpl implements PermissionRepo{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public boolean getPermission(Permission permission, String owner_email) {
        int idCanvas = permission.getIdCanvas();
        String user_email = permission.getUser_email();
        System.out.println(user_email);
        String permissionType = permission.getPermissionType();
            // Verificar si existe un registro en la tabla Canvas con idObjectes y user_email correspondientes
            String canvasCheckQuery = "SELECT COUNT(*) FROM Canvas WHERE idObjectes = ? AND user_email = ?";
            int countPropCanvas = jdbcTemplate.queryForObject(canvasCheckQuery, Integer.class, idCanvas, owner_email);
            if (countPropCanvas<0) return false;


            String sql = "INSERT INTO Permission (idCanvas, user_email, permissionType) " +
                    "VALUES (?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE permissionType = ?";
            int rowsAffected = jdbcTemplate.update(sql, idCanvas, user_email, permissionType,permissionType);
            return rowsAffected > 0; // Devuelve true si se insertó satisfactoriamente

          /*  if (count > 0) {
                // Si existe un registro en la tabla Canvas que coincide con los parámetros proporcionados,
                // realizar la inserción en la tabla Permission
                System.out.println("Actualitzat");
                String updatePermissionQuery = "UPDATE Permission SET permissionType = ? WHERE idCanvas = ? AND user_email = ?";
                int rowsAffected = jdbcTemplate.update(updatePermissionQuery, permissionType, idCanvas, user_email);
                return rowsAffected > 0;
            } else {
                System.out.println("Nou insert");
                // Si no existe un registro en la tabla Permission, realizar la inserción
                String insertPermissionQuery = "INSERT INTO Permission (idCanvas, user_email, permissionType) VALUES (?, ?, ?)";
                int rowsAffected = jdbcTemplate.update(insertPermissionQuery, idCanvas, user_email, permissionType);
                return rowsAffected > 0; // Devuelve true si se insertó satisfactoriamente
            }

           */
    }
}
