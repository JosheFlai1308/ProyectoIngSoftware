package com.grupo2.frontend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grupo2.frontend.dto.AdministradorDTO;
import com.grupo2.frontend.service.ICrudServiceAdministrador;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("administrador")
public class ControladorAdministrador {

    @Autowired
    public ICrudServiceAdministrador servicio;

    @GetMapping("listar/REST")
    public String listarREST(Model model, @RequestParam(name="search", required=false) String search, HttpServletRequest request) {
        List<AdministradorDTO> administradores = null;
        try {
            administradores = servicio.findAllREST(search);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            e.printStackTrace();
            return "rest/administrador/index";
        }
        if (administradores != null) {
            model.addAttribute("administradores", administradores);
            model.addAttribute("search", search);
            model.addAttribute("Message", "Se han cargado todos los administradores");
        } else {
            model.addAttribute("errorMessage", "Ocurrió un error en listar/REST");
        }
        return "rest/administrador/index";
    }

    @GetMapping("listar/nuevo/REST")
    public String agregarREST(Model model) {
        model.addAttribute("administrador", new AdministradorDTO());
        return "rest/administrador/form";
    }


    @GetMapping("editar/REST/{id}")
    public String editarREST(@PathVariable int id, Model model) {
        AdministradorDTO administrador = null;
        try {
            Optional<AdministradorDTO> dto = servicio.findByIdREST(id);
            administrador = dto.orElse(null);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            e.printStackTrace();
            return "rest/administrador/index";
        }
        if (administrador == null) {
            model.addAttribute("errorMessage", "Ocurrió un error en editar/REST/{id}");
        } else {
            model.addAttribute("administrador", administrador);
        }
        return "rest/administrador/form";
    }

    @PostMapping("grabar/REST")
    public String saveREST(@Valid AdministradorDTO a, Model model) {
    	AdministradorDTO administrador = null;
    	if (a.getId()==0) {
    		try {
    			administrador = servicio.saveREST(a);
			}
			catch (Exception e) {
				model.addAttribute("errorMessage", e.getMessage());
				e.printStackTrace();
				return "rest/administrador/index";
			}
			if (administrador==null)
			{
				model.addAttribute("errorMessage", "Ocurrio un error en grabar/REST SAVE");
				return "rest/administrador/form";
			}	
    	}else
		{
			try {
				administrador = servicio.editarREST(a);
			}
			catch (Exception e)
			{
				model.addAttribute("errorMessage", e.getMessage());
				e.printStackTrace();
				return "rest/administrador/index";
			}
			if (administrador==null)
			{
				model.addAttribute("errorMessage", "Ocurrio un error en grabar/REST EDITAR");
			}	
		}
		return "redirect:/administrador/listar/REST";
    }

    @GetMapping("eliminar/REST/{id}")
    public String deleteREST(@PathVariable int id, Model model) {
        AdministradorDTO administrador;
        try {
            administrador = servicio.deleteREST(id);
            if (administrador == null) {
                model.addAttribute("errorMessage", "Ocurrió un error en eliminar/REST/{id}");
            } else {
                return "redirect:/administrador/listar/REST";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            e.printStackTrace();
            return "rest/administrador/index";
        }
        return "redirect:/administrador/listar/REST";
    }
    
    
}
