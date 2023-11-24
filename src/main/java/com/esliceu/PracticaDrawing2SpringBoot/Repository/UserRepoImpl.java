package com.esliceu.PracticaDrawing2SpringBoot.Repository;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepoImpl implements UserRepo{
    @Override
    public boolean save(User user) {
        return false;
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
