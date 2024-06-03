package com.grupo2.frontend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grupo2.frontend.dto.ProductoDto;
import com.grupo2.frontend.service.ICrudServiceProducto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

public class ControladorProducto {

    @Autowired
	public ICrudServiceProducto servicio;

	//http://localhost:8081/producto/listar/REST
    @GetMapping("listar/REST")
    public String listarREST(Model model,@RequestParam(name="key", required=false)String key, HttpServletRequest request) {
    	List<ProductoDto> productos = null;
		try {
			productos = servicio.findAllREST(key);
		}
		catch (Exception e) {
			model.addAttribute("errorMessage",e.getMessage());
			e.printStackTrace();
			return "rest/producto/index";
		}
		if (productos!=null){
			model.addAttribute("productos", productos);
			model.addAttribute("key",key);
			model.addAttribute("Message", "Se han cargado todas las productos");
		}
		else
		{
			model.addAttribute("errorMessage", "Ocurrio un error es listar/REST");
		}
		return "rest/producto/index";
	}
 
    //http://localhost:8081/producto/nuevo/REST
    @GetMapping("listar/nuevo/REST")
    public String agregarREST(Model model) {
        model.addAttribute("producto", new ProductoDto());
        return "rest/producto/form";
    }

    //http://localhost:8081/producto/editar/REST/id
    @GetMapping("editar/REST/{id}")
    public String editarREST(@PathVariable int id, Model model) {
        ProductoDto producto =null;
        try {
	        Optional<ProductoDto> dto = servicio.findByIdREST(id); 
	        producto = dto.get();
        }catch (Exception e)
		{
			model.addAttribute("errorMessage", e.getMessage());
			e.printStackTrace();
			return "rest/producto/index";
		}
		if (producto ==null)
		{
			model.addAttribute("errorMessage", "Ocurrio un error en editar/REST/{id}");
		}
		else
		{
			model.addAttribute("producto",producto);
		}
		return "rest/producto/form";
    }

    //http://localhost:8081/comprobante/grabar
    @PostMapping("grabar/REST")
    public String saveREST(@Valid ProductoDto v, Model model) {
    	ProductoDto producto = null;
    	if (v.getId()==0) {
    		try {
    			producto = servicio.saveREST(v);
			}
			catch (Exception e) {
				model.addAttribute("errorMessage", e.getMessage());
				e.printStackTrace();
				return "rest/producto/index";
			}
			if (producto==null)
			{
				model.addAttribute("errorMessage", "Ocurrio un error en grabar/REST SAVE");
				return "rest/producto/form";
			}	
    	}else
		{
			try {
				producto = servicio.editarREST(v);
			}
			catch (Exception e)
			{
				model.addAttribute("errorMessage", e.getMessage());
				e.printStackTrace();
				return "rest/producto/index";
			}
			if (producto==null)
			{
				model.addAttribute("errorMessage", "Ocurrio un error en grabar/REST EDITAR");
			}	
		}
		return "redirect:/producto/listar/REST";
    }

    //http://localhost:8081/comprobante/eliminar/id
    @GetMapping("eliminar/REST/{id}")
    public String deleteREST(@PathVariable int id, Model model) {
    	ProductoDto producto;
    	try {
    		producto = servicio.deleteREST(id);
    		if (producto == null) {
    			model.addAttribute("errorMessage", "Ocurrio un error en eliminar/REST/{id}");
    		}else return "redirect:/producto/listar/REST";
    	}catch (Exception e){
			model.addAttribute("errorMessage",e.getMessage());
			e.printStackTrace();
			return "rest/producto/index";
		}
        return "redirect:/producto/listar/REST";
    }
    
}
