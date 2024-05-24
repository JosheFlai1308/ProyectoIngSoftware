package com.grupo2.backend.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grupo2.backend.dto.ProductoDto;
import com.grupo2.backend.service.ICrudServiceProducto;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.Valid;

@Controller
@RequestMapping("producto")
public class ControladorProducto {
    @Autowired
	private ICrudServiceProducto servicio;

	
	@PostMapping("/REST")
	public ProductoDto agregarProducto(@Valid @NonNull @RequestBody ProductoDto dto) {
		return servicio.save(dto);
	}

	
	@GetMapping("/REST/{id}")
	public ProductoDto getProductoById(@PathVariable("id") int id) {
		Optional<ProductoDto> oDto = servicio.findById(id);
		if (oDto.isPresent()) {
			ProductoDto dto = oDto.get();
			return dto;
		} else {
			return null;
		}
	}

	
	@PutMapping(("/REST"))
	public ProductoDto updateProducto(@Valid @NonNull @RequestBody ProductoDto dto) {
		Optional<ProductoDto> oDto = servicio.findById(dto.getId());
		if (oDto.isPresent() == true) {
			return servicio.save(dto);
		} else
			return null;
	}

	
	@DeleteMapping("/REST/{id}")
	public boolean deleteProductoById(@PathVariable("id") int id) {
		Optional<ProductoDto> oDto = servicio.findById(id);
		if (oDto.isPresent() == true) {
			servicio.delete(oDto.get());
			return true;
		} else {
			return false;
		}
	}
	
	@GetMapping("/REST")
	public List<ProductoDto> getAllProductos(@RequestParam(name = "search", required = false) String search) {
	    return servicio.findAll(search);
	}
}
