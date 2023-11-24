package com.esliceu.PracticaDrawing2SpringBoot.Filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionInterruptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("Dins el filtre.");
     /*   String user = (String) session.getAttribute("email");
        request.setAttribute("email",user);
        req.setAttribute("password",user);
        if (user == null) {
            // si el usuari no esta logeat se redirigeix a el login
            response.sendRedirect("/error");
            return;
        }

      */
        return true;
    }
}
