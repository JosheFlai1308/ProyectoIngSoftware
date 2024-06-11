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

import com.grupo2.frontend.dto.ProveedorDto;
import com.grupo2.frontend.service.ICrudServiceProveedor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("proveedor")
public class ControladorProveedor {
    
    @Autowired
	public ICrudServiceProveedor servicio;

	//http://localhost:8081/proveedor/listar/REST
    @GetMapping("listar/REST")
    public String listarREST(Model model,@RequestParam(name="search", required=false)String search, HttpServletRequest request) {
    	List<ProveedorDto> proveedores = null;
		try {
			proveedores = servicio.findAllREST(search);
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
		return "rest/proveedor/index";
	}
 
    //http://localhost:8081/proveedor/nuevo/REST
    @GetMapping("listar/nuevo/REST")
    public String agregarREST(Model model) {
        model.addAttribute("proveedor", new ProveedorDto());
        return "rest/proveedor/form";
    }

    //http://localhost:8081/proveedor/editar/REST/id
    @GetMapping("editar/REST/{id}")
    public String editarREST(@PathVariable int id, Model model) {
        ProveedorDto proveedor =null;
        try {
	        Optional<ProveedorDto> dto = servicio.findByIdREST(id); 
	        proveedor = dto.get();
        }catch (Exception e)
		{
			model.addAttribute("errorMessage", e.getMessage());
			e.printStackTrace();
			return "rest/proveedor/index";
		}
		if (proveedor ==null)
		{
			model.addAttribute("errorMessage", "Ocurrio un error en editar/REST/{id}");
		}
		else
		{
			model.addAttribute("proveedor",proveedor);
		}
		return "rest/proveedor/form";
    }

    //http://localhost:8081/proveedor/grabar
    @PostMapping("grabar/REST")
    public String saveREST(@Valid ProveedorDto p, Model model) {
    	ProveedorDto proveedor = null;
    	if (p.getId()==0) {
    		try {
    			proveedor = servicio.saveREST(p);
			}
			catch (Exception e) {
				model.addAttribute("errorMessage", e.getMessage());
				e.printStackTrace();
				return "rest/proveedor/index";
			}
			if (proveedor==null)
			{
				model.addAttribute("errorMessage", "Ocurrio un error en grabar/REST SAVE");
				return "rest/proveedor/form";
			}	
    	}else
		{
			try {
				proveedor = servicio.editarREST(p);
			}
			catch (Exception e)
			{
				model.addAttribute("errorMessage", e.getMessage());
				e.printStackTrace();
				return "rest/proveedor/index";
			}
			if (proveedor==null)
			{
				model.addAttribute("errorMessage", "Ocurrio un error en grabar/REST EDITAR");
			}	
		}
		return "redirect:/proveedor/listar/REST";
    }

    //http://localhost:8081/proveedor/eliminar/id
    @GetMapping("eliminar/REST/{id}")
    public String deleteREST(@PathVariable int id, Model model) {
    	ProveedorDto proveedor;
    	try {
    		proveedor = servicio.deleteREST(id);
    		if (proveedor == null) {
    			model.addAttribute("errorMessage", "Ocurrio un error en eliminar/REST/{id}");
    		}else return "redirect:/proveedor/listar/REST";
    	}catch (Exception e){
			model.addAttribute("errorMessage",e.getMessage());
			e.printStackTrace();
			return "rest/proveedor/index";
		}
        return "redirect:/proveedor/listar/REST";
    }
}
