package com.esliceu.PracticaDrawing2SpringBoot.Services;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.User;
import com.esliceu.PracticaDrawing2SpringBoot.Repository.UserRepo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
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

        return true;
    }
    public String getNameOfUser(String email) {
        User u = userRepo.findByEmail(email);
        System.out.println("getName of user");
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

    public List<User> allUsersExceptUserSession(String email, int idObjectes) {
        return userRepo.findAllUsersExceptUserSession(email,idObjectes);
    }

    public boolean logWithOauth2() {
        System.out.println("email en logService" + email);
        System.out.println("pass: " + password);
        User user=userRepo.findByEmail(email);
        if (user == null) {
            return registrarUsuariOauth2("",email,"");
        } else{
            //fer login amb discord
            return true;
        }

    }

    private boolean registrarUsuariOauth2(String name, String email, String password) {
        if (!email.isEmpty() && password.isEmpty()) {
            User user = new User(name,email,password);
            return userRepo.save(user);
        }
        return false;
    }
}
