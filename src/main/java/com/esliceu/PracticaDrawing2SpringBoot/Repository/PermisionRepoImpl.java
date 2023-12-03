package com.esliceu.PracticaDrawing2SpringBoot.Repository;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.Permission;
import com.esliceu.PracticaDrawing2SpringBoot.Exceptions.InvalidPermissionTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class PermisionRepoImpl implements PermissionRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean getPermission(Permission permission, String owner_email) {
        int idCanvas = permission.getIdCanvas();
        String user_email = permission.getUser_email();
        System.out.println(user_email);
        String permissionType = permission.getPermissionType();
        //Verifica si existeix un registre a la taula Canvas amb el idObjectes i user_email(de el propietari)
        String canvasCheckQuery = "SELECT COUNT(*) FROM Canvas WHERE idObjectes = ? AND user_email = ?";
        int countPropCanvas = jdbcTemplate.queryForObject(canvasCheckQuery, Integer.class, idCanvas, owner_email);
        if (countPropCanvas < 0) return false;
        // Verifica si existeix un registre a la taula Permission amb el idCanvas i user_email iguals si es
        // aixi fara un UPDATE de el registre, no creara un nou.
        String permissionCheckQuery = "SELECT COUNT(*) FROM Permission WHERE idCanvas = ? AND user_email = ?";
        int count = jdbcTemplate.queryForObject(permissionCheckQuery, Integer.class, idCanvas, user_email);

        if (count > 0) {
            // Si existeix un registre a la taula Permission que coincideix idCanvas amb user_email(el email de
            // l'usuari amb qui es comparteix) actualitzara el registre.
            System.out.println("Actualitzat");
            String updatePermissionQuery = "UPDATE Permission SET permissionType = ? WHERE idCanvas = ? AND user_email = ?";
            int rowsAffected = jdbcTemplate.update(updatePermissionQuery, permissionType, idCanvas, user_email);
            return rowsAffected > 0;
        } else {
            System.out.println("Nou insert");
            // Si no existeix un registre en la taula Permission, se crea un registre nou
            String insertPermissionQuery = "INSERT INTO Permission (idCanvas, user_email, permissionType) VALUES (?, ?, ?)";
            int rowsAffected = jdbcTemplate.update(insertPermissionQuery, idCanvas, user_email, permissionType);
            return rowsAffected > 0; // Devuelve true si se insertó satisfactoriamente
        }
    }
}
