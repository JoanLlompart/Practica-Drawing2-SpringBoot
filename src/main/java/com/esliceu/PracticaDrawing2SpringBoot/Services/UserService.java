package com.esliceu.PracticaDrawing2SpringBoot.Services;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.User;
import com.esliceu.PracticaDrawing2SpringBoot.Repository.UserRepo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private String email;
    private String password;
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

    public boolean validLogin(String email, String password) {
        User user = userRepo.initSession(email,password);
        if (user == null) {
            System.out.println("Nuuull");
            System.out.println(user);
            return false;
        }
       /* password = encriptarPassword(password);
        if (user.getEmail().equals(email) && email.equals(user.getEmail())) return true;
        return false;
        */
        return true;
       // return userRepo.isPasswordOfUser(user, password);
    }
    public String getNameOfUser(String email) {
        User u = userRepo.findByEmail(email);
        return u.getName();
    }

    public UserRepo getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
