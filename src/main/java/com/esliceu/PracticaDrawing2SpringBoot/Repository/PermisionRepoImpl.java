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
            int count = jdbcTemplate.queryForObject(canvasCheckQuery, Integer.class, idCanvas, owner_email);
            if (count > 0) {
                // Si existe un registro en la tabla Canvas que coincide con los parámetros proporcionados,
                // realizar la inserción en la tabla Permission
                String insertPermissionQuery = "INSERT INTO Permission (idCanvas, user_email, permissionType) VALUES (?, ?, ?)";
                int rowsAffected = jdbcTemplate.update(insertPermissionQuery, idCanvas, user_email, permissionType);

                return rowsAffected > 0; // Devuelve true si se insertó satisfactoriamente
            }
            return false;
    }
}
