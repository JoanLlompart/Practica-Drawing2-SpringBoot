package com.esliceu.PracticaDrawing2SpringBoot.Services;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.User;
import com.esliceu.PracticaDrawing2SpringBoot.Repository.UserRepo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    public boolean registrarUsuari(String name, String email, String password) {
        String passEncrip=encriptarPassword(password);
        User user = new User(name,email,passEncrip);

        System.out.println("registreUsuari a UserService");
        return userRepo.save(user);
    }
    public String encriptarPassword(String password) {
        return DigestUtils.md5Hex(password);
    }
}
