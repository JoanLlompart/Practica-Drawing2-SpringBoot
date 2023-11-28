package com.esliceu.PracticaDrawing2SpringBoot.Repository;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        return null;
    }

    @Override
    public boolean isPasswordOfUser(User user, String pasword) {
        return false;
    }

    @Override
    public User initSession(String email, String password) {
        return null;
    }
}
