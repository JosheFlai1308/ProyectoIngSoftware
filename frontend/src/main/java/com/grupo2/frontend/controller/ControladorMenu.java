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
    public String mostrarMenuInicio(Model model,@RequestParam(name="search", required=false)String search, HttpServletRequest request) {
    	List<ProveedorDto> proveedores = null;
		try {
			proveedores = servicioProveedor.findAllREST(search);
		}
		catch (Exception e) {
			model.addAttribute("errorMessage",e.getMessage());
			e.printStackTrace();
			return "rest/proveedor/index";
		}
		if (proveedores!=null){
			model.addAttribute("proveedores", proveedores);
			model.addAttribute("search",search);
			model.addAttribute("Message", "Se han cargado todas las proveedores");
		}
		else
		{
			model.addAttribute("errorMessage", "Ocurrio un error es listar/REST");
		}
        return "f(x)/menu_principal"; // Esto es un ejemplo, la vista real debe estar configurada
    }

    @GetMapping("/top10")
    public String mostrarTop10() {
        // Aquí podrías devolver el nombre de la vista o la ruta del menú de inicio
        return "f(x)/top10"; // Esto es un ejemplo, la vista real debe estar configurada
    }
}

