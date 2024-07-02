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

import com.grupo2.frontend.dto.Linea_detalle_pDto;
import com.grupo2.frontend.dto.ProductoDto;
import com.grupo2.frontend.dto.PedidoDto;
import com.grupo2.frontend.service.ICrudServiceLinea_detalle_p;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("linea_detalle_p")

public class ControladorLinea_detalle_p {

	@Autowired
	public ICrudServiceLinea_detalle_p servicio;

	@Autowired
	public ControladorProducto controller_producto;

	@Autowired
	public ControladorPedido controller_pedido;

	// http://localhost:8081/linea_detalle_p/listar/REST
	@GetMapping("listar/REST")
	public String listarREST(Model model, @RequestParam(name = "search", required = false) String search,
			HttpServletRequest request) {
		List<Linea_detalle_pDto> lineas_detalle = null;
		try {
			lineas_detalle = servicio.findAllREST(search);
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			e.printStackTrace();
			return "rest/linea_detalle_p/index";
		}
		if (lineas_detalle != null) {
			model.addAttribute("lineas_detalle", lineas_detalle);
			model.addAttribute("productos", controller_producto.getAll(model));
			model.addAttribute("pedidos", controller_pedido.getAll(model));
			model.addAttribute("search", search);
			model.addAttribute("Message", "Se han cargado todas las lineas_detalle");
		} else {
			model.addAttribute("errorMessage", "Ocurrio un error es listar/REST");
		}
		return "rest/linea_detalle_p/index";
	}

	// http://localhost:8081/linea_detalle_p/nuevo/REST
	@GetMapping("listar/nuevo/REST")
	public String agregarREST(Model model) {
		model.addAttribute("linea_detalle_p", new Linea_detalle_pDto());
		model.addAttribute("productos", controller_producto.getAll(model));
		model.addAttribute("pedidos", controller_pedido.getAll(model));
		return "rest/linea_detalle_p/form";
	}

	// http://localhost:8081/linea_detalle_p/editar/REST/id
	@GetMapping("editar/REST/{id}")
	public String editarREST(@PathVariable int id, Model model) {
		Linea_detalle_pDto linea_detalle_p = null;
		try {
			Optional<Linea_detalle_pDto> dto = servicio.findByIdREST(id);
			linea_detalle_p = dto.get();
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			e.printStackTrace();
			return "rest/linea_detalle_p/index";
		}
		if (linea_detalle_p == null) {
			model.addAttribute("errorMessage", "Ocurrio un error en editar/REST/{id}");
		} else {
			model.addAttribute("linea_detalle_p", linea_detalle_p);
			model.addAttribute("productos", controller_producto.getAll(model));
			model.addAttribute("pedidos", controller_pedido.getAll(model));
		}
		return "rest/linea_detalle_p/form";
	}

	// http://localhost:8081/linea_detalle_p/grabar
	@PostMapping("grabar/REST")
	public String saveREST(@Valid Linea_detalle_pDto p, Model model) {
		Linea_detalle_pDto linea_detalle_p = null;
		model.addAttribute("productos", controller_producto.getAll(model));
		model.addAttribute("pedidos", controller_pedido.getAll(model));
		ProductoDto producto = controller_producto.found(p.getId_producto());
		PedidoDto pedido = controller_pedido.found(p.getId_pedido());
		if (p.getId() == 0) {
			try {
				p.setProducto(producto);
				p.setPedido(pedido);
				linea_detalle_p = servicio.saveREST(p);
			} catch (Exception e) {
				model.addAttribute("errorMessage", e.getMessage());
				e.printStackTrace();
				return "rest/linea_detalle_p/index";
			}
			if (linea_detalle_p == null) {
				model.addAttribute("errorMessage", "Ocurrio un error en grabar/REST SAVE");
				return "rest/linea_detalle_p/form";
			}
		} else {
			try {
				p.setProducto(producto);
				p.setPedido(pedido);
				linea_detalle_p = servicio.editarREST(p);
			} catch (Exception e) {
				model.addAttribute("errorMessage", e.getMessage());
				e.printStackTrace();
				return "rest/linea_detalle_p/index";
			}
			if (linea_detalle_p == null) {
				model.addAttribute("errorMessage", "Ocurrio un error en grabar/REST EDITAR");
			}
		}
		return "redirect:/linea_detalle_p/listar/REST";
	}

	// http://localhost:8081/linea_detalle_p/eliminar/id
	@GetMapping("eliminar/REST/{id}")
	public String deleteREST(@PathVariable int id, Model model) {
		Linea_detalle_pDto linea_detalle_p;
		try {
			linea_detalle_p = servicio.deleteREST(id);
			if (linea_detalle_p == null) {
				model.addAttribute("errorMessage", "Ocurrio un error en eliminar/REST/{id}");
			} else
				return "redirect:/linea_detalle_p/listar/REST";
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			e.printStackTrace();
			return "rest/linea_detalle_p/index";
		}
		return "redirect:/linea_detalle_p/listar/REST";
	}

}
