package com.esliceu.PracticaDrawing2SpringBoot.Repository;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.User;
import org.springframework.stereotype.Repository;

public interface UserRepo {
    boolean save(User user);
    User findByEmail(String email);

    //boolean isPasswordOfUser(User user, String pasword);

    User initSession(String email,String password);
}
