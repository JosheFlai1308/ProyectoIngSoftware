package com.grupo2.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "rest/encargado/login";
    }

@PostMapping("/login")
public String login(@RequestParam("correo_electronico") String correoElectronico,
                    @RequestParam("password") String password,
                    HttpServletRequest request,
                    RedirectAttributes attributes) {
    
    // Simulación básica de validación de credenciales
    if ("usuario@gmail.com".equals(correoElectronico) && "password123".equals(password)) {
        // Aquí normalmente obtendrías la URL a la que deseas redirigir después del inicio de sesión
        String redirectUrl = "http://localhost:8081/guia_despacho/listar/REST";
        
        // Redirigir a la URL obtenida después del inicio de sesión
        return "redirect:" + redirectUrl;
    } else {
        // Redirigir de nuevo a la página de login con mensaje de error
        attributes.addFlashAttribute("error", "Correo electrónico o contraseña incorrectos");
        return "redirect:/login";
    }
}


}
