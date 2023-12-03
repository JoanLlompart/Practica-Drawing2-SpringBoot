package com.esliceu.PracticaDrawing2SpringBoot.Repository;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepo {
    boolean save(User user);
    User findByEmail(String email);

    //boolean isPasswordOfUser(User user, String pasword);

    //List<User> findAllUsersExceptUserSession(String emailSession);
    List<User> findAllUsersExceptUserSession(String emailSession,int idObjectes);
    User initSession(String email,String password);
}