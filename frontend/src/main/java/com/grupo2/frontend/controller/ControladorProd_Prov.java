package com.grupo2.frontend.controller;

import com.grupo2.frontend.dto.Prod_ProvDto;
import com.grupo2.frontend.dto.ProductoDto;
import com.grupo2.frontend.dto.ProveedorDto;
import com.grupo2.frontend.service.ICrudServiceProd_Prov;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

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

@Controller
@RequestMapping("prod_prov")

public class ControladorProd_Prov {
    

    @Autowired
	public ICrudServiceProd_Prov servicio;

    @Autowired
    public ControladorProducto controller_producto;

    @Autowired
    public ControladorProveedor controller_proveedor;
    

	//http://localhost:8081/prod_prov/listar/REST
    @GetMapping("listar/REST")
    public String listarREST(Model model,@RequestParam(name="search", required=false)String search, HttpServletRequest request) {
    	List<Prod_ProvDto> prod_provs = null;
		try {
			prod_provs = servicio.findAllREST(search);
		}
		catch (Exception e) {
			model.addAttribute("errorMessage",e.getMessage());
			e.printStackTrace();
			return "rest/prod_prov/index";
		}
		if (prod_provs!=null){
			model.addAttribute("prod_provs", prod_provs);
            model.addAttribute("productos", controller_producto.getAll(model));
            model.addAttribute("proveedores", controller_proveedor.getAll(model));
			model.addAttribute("search",search);
			model.addAttribute("Message", "Se han cargado todas las prod_provs");
		}
		else
		{
			model.addAttribute("errorMessage", "Ocurrio un error es listar/REST");
		}
		return "rest/prod_prov/index";
	}
 
    //http://localhost:8081/prod_prov/nuevo/REST
    @GetMapping("listar/nuevo/REST")
    public String agregarREST(Model model) {
        model.addAttribute("prod_prov", new Prod_ProvDto());
        model.addAttribute("productos", controller_producto.getAll(model));
        model.addAttribute("proveedores", controller_proveedor.getAll(model));
        return "rest/prod_prov/form";
    }

    //http://localhost:8081/prod_prov/editar/REST/id
    @GetMapping("editar/REST/{id}")
    public String editarREST(@PathVariable int id, Model model) {
        Prod_ProvDto prod_prov =null;
        try {
	        Optional<Prod_ProvDto> dto = servicio.findByIdREST(id); 
	        prod_prov = dto.get();
        }catch (Exception e)
		{
			model.addAttribute("errorMessage", e.getMessage());
			e.printStackTrace();
			return "rest/prod_prov/index";
		}
		if (prod_prov ==null)
		{
			model.addAttribute("errorMessage", "Ocurrio un error en editar/REST/{id}");
		}
		else
		{
			model.addAttribute("prod_prov",prod_prov);
            model.addAttribute("productos", controller_producto.getAll(model));
            model.addAttribute("proveedores", controller_proveedor.getAll(model));
		}
		return "rest/prod_prov/form";
    }

    //http://localhost:8081/prod_prov/grabar
    @PostMapping("grabar/REST")
    public String saveREST(@Valid Prod_ProvDto p, Model model) {
    	Prod_ProvDto prod_prov = null;
		model.addAttribute("productos", controller_producto.getAll(model));
        model.addAttribute("proveedores", controller_proveedor.getAll(model));
		ProductoDto producto = controller_producto.found(p.getId_producto());
		ProveedorDto proveedor = controller_proveedor.found(p.getId_proveedor());
		System.out.println(producto);
		System.out.println(proveedor);
    	if (p.getId()==0) {
    		try {
				p.setProducto(producto);
				p.setProveedor(proveedor);
    			prod_prov = servicio.saveREST(p);
			}
			catch (Exception e) {
				model.addAttribute("errorMessage", e.getMessage());
				e.printStackTrace();
				return "rest/prod_prov/index";
			}
			if (prod_prov==null)
			{
				model.addAttribute("errorMessage", "Ocurrio un error en grabar/REST SAVE");
				return "rest/prod_prov/form";
			}	
    	}else
		{
			try {
				p.setProducto(producto);
				p.setProveedor(proveedor);
				prod_prov = servicio.editarREST(p);
			}
			catch (Exception e)
			{
				model.addAttribute("errorMessage", e.getMessage());
				e.printStackTrace();
				return "rest/prod_prov/index";
			}
			if (prod_prov==null)
			{
				model.addAttribute("errorMessage", "Ocurrio un error en grabar/REST EDITAR");
			}	
		}
		return "redirect:/prod_prov/listar/REST";
    }

    //http://localhost:8081/prod_prov/eliminar/id
    @GetMapping("eliminar/REST/{id}")
    public String deleteREST(@PathVariable int id, Model model) {
    	Prod_ProvDto prod_prov;
    	try {
    		prod_prov = servicio.deleteREST(id);
    		if (prod_prov == null) {
    			model.addAttribute("errorMessage", "Ocurrio un error en eliminar/REST/{id}");
    		}else return "redirect:/prod_prov/listar/REST";
    	}catch (Exception e){
			model.addAttribute("errorMessage",e.getMessage());
			e.printStackTrace();
			return "rest/prod_prov/index";
		}
        return "redirect:/prod_prov/listar/REST";
    }
    
}
