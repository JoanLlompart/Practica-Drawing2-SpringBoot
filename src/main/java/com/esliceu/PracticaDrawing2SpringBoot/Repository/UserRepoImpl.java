package com.esliceu.PracticaDrawing2SpringBoot.Repository;

import com.esliceu.PracticaDrawing2SpringBoot.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//@Repository
public class UserRepoImpl implements UserRepo {
    //@Autowired
    static List<User> usuaris = new ArrayList<>();
    public boolean save(User user) {
        String correu =  user.getEmail();
        if (usuaris.size() != 0) {
            user.setId(usuaris.size() + 1);
        } else {
            user.setId(1);
        }
        boolean repetit = comprobarSiExisteix(correu);
        if (!repetit) {
            //si es false pot afegir el usuari.
            usuaris.add(user);
            System.out.println("usuari afegit");
        } else {
            System.err.println("Email no valid ja pertany a un usuari");
        }
        for (User use1: usuaris) {
            System.out.println("ID :" +use1.getId() + ", " + use1.getName());
        }
        System.out.println("repetit" + repetit);
        if (repetit) {
            //Si esta repetit retorna false,no se ha pogut registrar
            return false;
        } else {
            //si el usuari ja esta creat tornam true, ja que no se ha pogut registrar
            return true;
        }
    }
    public User findByEmail(String email) {
        for (User user : usuaris) {
            if (user.getEmail().equals(email)) {
                //retornara totes les dades de user si el email es correcte
                return user;
            }
        }
        return null;
    }
    /*
    @Override
    public boolean isPasswordOfUser(User user, String pasword) {
        if (user.getPassword().equals(pasword)) {
            System.out.println("pasword coincideix amb el user :()UserDAOImpl");
            System.out.println("Password:" + pasword + " ,user = " + user.getPassword());
            return true;
        }
        return false;
    }
     */
    @Override
    public User initSession(String email, String password) {
        for(User user : usuaris) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                //Troba el usuari que te aquest email i aquesta password
                return user;
            }
        }
        return null;
    }
    private boolean comprobarSiExisteix(String correu) {
        for (User user1: usuaris) {
            if (user1.getEmail().equals(correu)) {
                //si el email esta repetit retornara true
                return true;
            }
        }
        //si cap usuari de la llista ha coincidit amb el correu retornara false.
        return false;
    }
}