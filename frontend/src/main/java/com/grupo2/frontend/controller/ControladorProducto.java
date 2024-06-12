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

import com.grupo2.frontend.dto.ProductoDto;
import com.grupo2.frontend.service.ICrudServiceProducto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller 
@RequestMapping("producto")
public class ControladorProducto {

    @Autowired
	public ICrudServiceProducto servicio;

	//http://localhost:8081/producto/listar/REST
    @GetMapping("listar/REST")
    public String listarREST(Model model,@RequestParam(name="search", required=false)String search, HttpServletRequest request) {
    	List<ProductoDto> productos = null;
		try {
			productos = servicio.findAllREST(search);
		}
		catch (Exception e) {
			model.addAttribute("errorMessage",e.getMessage());
			e.printStackTrace();
			return "rest/producto/index";
		}
		if (productos!=null){
			model.addAttribute("productos", productos);
			model.addAttribute("search",search);
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

    //http://localhost:8081/producto/grabar
    @PostMapping("grabar/REST")
    public String saveREST(@Valid ProductoDto p, Model model) {
    	ProductoDto producto = null;
    	if (p.getId()==0) {
    		try {
    			producto = servicio.saveREST(p);
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
				producto = servicio.editarREST(p);
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

    //http://localhost:8081/producto/eliminar/id
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
    


//REST
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////77
//FX

	public List<ProductoDto> getAll (Model model){
		try {
			List<ProductoDto> productos = servicio.findAllREST(null);
			model.addAttribute("null", productos);
			return productos;

		} catch(Exception e){
			model.addAttribute("errorMessage", e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public ProductoDto found(String idp){
		StringBuilder nros = new StringBuilder();
		for (char l:idp.toCharArray()){
			if (Character.isDigit(l)){
				nros.append(l);
			}
		}
		int id = Integer.parseInt(nros.toString());
		try {
			Optional<ProductoDto> dto = servicio.findByIdREST(id);
			return dto.get();
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

} 