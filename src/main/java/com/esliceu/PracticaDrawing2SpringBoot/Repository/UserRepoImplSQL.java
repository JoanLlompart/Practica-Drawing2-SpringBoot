package com.esliceu.PracticaDrawing2SpringBoot.Repository;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.SQLWarningException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepoImplSQL implements UserRepo  {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public boolean save(User user)  {
            String sql = "INSERT INTO user (name,email,password) VALUES (?,?,?)";
            jdbcTemplate.update(sql,user.getName(),user.getEmail(),user.getPassword());
            return true;
    }
    @Override
    public User findByEmail(String email) {
        try {
            String sql = "SELECT * FROM user WHERE email = ?";
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);
            System.out.println(user.toString());
            return user;
        } catch (EmptyResultDataAccessException e) {
            System.err.println("No se ha encontrado el usuario");
            return null;
        }
    }

    @Override
    public List<User> findAllUsersExceptUserSession(String emailSession) {
        try {
            String sql = "SELECT * FROM user WHERE email <> ?";
            return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(User.class), emailSession);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("No se ha encontrado el usuario");
            System.out.println(e.getLocalizedMessage()+ e.getCause());
            return null;
        }
    }



/*
    public boolean isPasswordOfUser(User user, String pasword) {
        try {
            String sql = "SELECT password FROM user WHERE email = ?";
            User userDB = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);
            System.out.println(user.toString());
            return true;
        } catch (EmptyResultDataAccessException e) {
            System.err.println("No se ha encontrado el usuario");
            return false;
        }
    }

 */

    @Override
    public User initSession(String email, String password) {
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        try {
            System.out.println("PAS DE INIT :" +password);
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email,password);
            System.out.println(user.toString());
            return user;
        } catch (Exception e) {
            // Controla la excepción de manera apropiada (log, relanzar, etc.)
            e.printStackTrace();
        }
        return null;
    }
}
