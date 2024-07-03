package com.grupo2.frontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grupo2.frontend.dto.ProveedorDto;
import com.grupo2.frontend.service.ICrudServiceProveedor;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("menu")
public class ControladorMenu {

    @Autowired
    public ICrudServiceProveedor servicioProveedor;

    // Endpoint para la URL de menú de inicio
    @GetMapping("/inicio")
    public String mostrarMenuInicio(){
        return "f(x)/menu_principal"; // Esto es un ejemplo, la vista real debe estar configurada
    }

    @GetMapping("/top10")
    public String mostrarTop10(Model model,@RequestParam(name="search", required=false)String search, HttpServletRequest request) {
        List<ProveedorDto> proveedores = null;
        try {
            proveedores = servicioProveedor.findAllREST(search);
            // Ordenar proveedores por calificación y limitar a los primeros 10
            proveedores.sort((a, b) -> Double.compare(b.getCalificacion(), a.getCalificacion()));
            proveedores = proveedores.subList(0, Math.min(proveedores.size(), 10));
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            e.printStackTrace();
            return "rest/proveedor/index";
        }
        if (proveedores != null) {
            model.addAttribute("proveedores", proveedores);
            model.addAttribute("search", search);
            model.addAttribute("Message", "Se han cargado todas las proveedores");
        } else {
            model.addAttribute("errorMessage", "Ocurrio un error es listar/REST");
        }
        return "f(x)/top10"; // Esto es un ejemplo, la vista real debe estar configurada
    }

    @GetMapping("/secreto")
    public String mostrarMenuSecreto(){
        return "f(x)/menu_secreto"; // Esto es un ejemplo, la vista real debe estar configurada
    }
}

