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

import com.grupo2.frontend.dto.EncargadoDto;
import com.grupo2.frontend.dto.ProductoDto;
import com.grupo2.frontend.service.ICrudServiceEncargado;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("encargado")
public class ControladorEncargado {

    @Autowired
    public ICrudServiceEncargado servicio;

    @GetMapping("listar/REST")
    public String listarREST(Model model, @RequestParam(name="search", required=false) String search, HttpServletRequest request) {
        List<EncargadoDto> encargados = null;
        try {
            encargados = servicio.findAllREST(search);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            e.printStackTrace();
            return "rest/encargado/index";
        }
        if (encargados != null) {
            model.addAttribute("encargados", encargados);
            model.addAttribute("search", search);
            model.addAttribute("Message", "Se han cargado todas los encargados");
        } else {
            model.addAttribute("errorMessage", "Ocurrió un error en listar/REST");
        }
        return "rest/encargado/index";
    }

    @GetMapping("listar/nuevo/REST")
    public String agregarREST(Model model) {
        model.addAttribute("encargado", new EncargadoDto());
        return "rest/encargado/form";
    }


    @GetMapping("editar/REST/{id}")
    public String editarREST(@PathVariable int id, Model model) {
        EncargadoDto encargado = null;
        try {
            Optional<EncargadoDto> dto = servicio.findByIdREST(id);
            encargado = dto.orElse(null);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            e.printStackTrace();
            return "rest/encargado/index";
        }
        if (encargado == null) {
            model.addAttribute("errorMessage", "Ocurrió un error en editar/REST/{id}");
        } else {
            model.addAttribute("encargado", encargado);
        }
        return "rest/encargado/form";
    }

    @PostMapping("grabar/REST")
    public String saveREST(@Valid EncargadoDto p, Model model) {
    	EncargadoDto encargado = null;
    	if (p.getId()==0) {
    		try {
    			encargado = servicio.saveREST(p);
			}
			catch (Exception e) {
				model.addAttribute("errorMessage", e.getMessage());
				e.printStackTrace();
				return "rest/encargado/index";
			}
			if (encargado==null)
			{
				model.addAttribute("errorMessage", "Ocurrio un error en grabar/REST SAVE");
				return "rest/encargado/form";
			}	
    	}else
		{
			try {
				encargado = servicio.editarREST(p);
			}
			catch (Exception e)
			{
				model.addAttribute("errorMessage", e.getMessage());
				e.printStackTrace();
				return "rest/encargado/index";
			}
			if (encargado==null)
			{
				model.addAttribute("errorMessage", "Ocurrio un error en grabar/REST EDITAR");
			}	
		}
		return "redirect:/encargado/listar/REST";
    }

    @GetMapping("eliminar/REST/{id}")
    public String deleteREST(@PathVariable int id, Model model) {
        EncargadoDto encargado;
        try {
            encargado = servicio.deleteREST(id);
            if (encargado == null) {
                model.addAttribute("errorMessage", "Ocurrió un error en eliminar/REST/{id}");
            } else {
                return "redirect:/encargado/listar/REST";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            e.printStackTrace();
            return "rest/encargado/index";
        }
        return "redirect:/encargado/listar/REST";
    }
    
    
}
