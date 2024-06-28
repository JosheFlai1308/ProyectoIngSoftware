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

import com.grupo2.frontend.dto.LineadetalleCDTO;
import com.grupo2.frontend.service.ICrudServiceLineadetalleC;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("lineadetalleC")
public class ControladorLineadetalleC {

	@Autowired
	public ICrudServiceLineadetalleC servicio;

	@GetMapping("listar/REST")
	public String listarREST(Model model, @RequestParam(name = "search", required = false) String search,
			HttpServletRequest request) {
		List<LineadetalleCDTO> lineas_detalle = null;
		try {
			lineas_detalle = servicio.findAllREST(search);
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			e.printStackTrace();
			return "rest/lineadetalleC/index";
		}
		if (lineas_detalle != null) {
			model.addAttribute("lineas_detalle", lineas_detalle);
			model.addAttribute("search", search);
			model.addAttribute("Message", "Se han cargado todas las lineas de detalle");
		} else {
			model.addAttribute("errorMessage", "Ocurrio un error es listar/REST");
		}
		return "rest/lineadetalleC/index";
	}

	@GetMapping("listar/nuevo/REST")
	public String agregarREST(Model model) {
		model.addAttribute("linea_detalle", new LineadetalleCDTO());
		return "rest/lineadetalleC/form";
	}

	@GetMapping("editar/REST/{id}")
	public String editarREST(@PathVariable int id, Model model) {
		LineadetalleCDTO linea_detalle = null;
		try {
			Optional<LineadetalleCDTO> dto = servicio.findByIdREST(id);
			linea_detalle = dto.get();
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			e.printStackTrace();
			return "rest/lineadetalleC/index";
		}
		if (linea_detalle == null) {
			model.addAttribute("errorMessage", "Ocurrio un error en editar/REST/{id}");
		} else {
			model.addAttribute("linea_detalle", linea_detalle);
		}
		return "rest/lineadetalleC/form";
	}

	@PostMapping("grabar/REST")
	public String saveREST(@Valid LineadetalleCDTO p, Model model) {
		LineadetalleCDTO linea_detalle = null;
		if (p.getId() == 0) {
			try {
				linea_detalle = servicio.saveREST(p);
			} catch (Exception e) {
				model.addAttribute("errorMessage", e.getMessage());
				e.printStackTrace();
				return "rest/lineadetalleC/index";
			}
			if (linea_detalle == null) {
				model.addAttribute("errorMessage", "Ocurrio un error en grabar/REST SAVE");
				return "rest/lineadetalleC/form";
			}
		} else {
			try {
				linea_detalle = servicio.editarREST(p);
			} catch (Exception e) {
				model.addAttribute("errorMessage", e.getMessage());
				e.printStackTrace();
				return "rest/lineadetalleC/index";
			}
			if (linea_detalle == null) {
				model.addAttribute("errorMessage", "Ocurrio un error en grabar/REST EDITAR");
			}
		}
		return "redirect:/lineadetalleC/listar/REST";
	}

	@GetMapping("eliminar/REST/{id}")
	public String deleteREST(@PathVariable int id, Model model) {
		LineadetalleCDTO linea_detalle;
		try {
			linea_detalle = servicio.deleteREST(id);
			if (linea_detalle == null) {
				model.addAttribute("errorMessage", "Ocurrio un error en eliminar/REST/{id}");
			} else
				return "redirect:/lineadetalleC/listar/REST";
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			e.printStackTrace();
			return "rest/lineadetalleC/index";
		}
		return "redirect:/lineadetalleC/listar/REST";
	}
}
