package com.esliceu.PracticaDrawing2SpringBoot.Repository;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepo {
    boolean save(User user);
    User findByEmail(String email);

    List<User> findAllUsersExceptUserSession(String emailSession,int idObjectes);
    User initSession(String email,String password);
}