package com.grupo2.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.backend.dto.ProductoDto;
import com.grupo2.backend.service.ICrudServiceProducto;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.Valid;

@RestController
@RequestMapping("producto")
public class ControladorProducto {
	@Autowired
	private ICrudServiceProducto servicio;

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@PostMapping("/REST")
	public ResponseEntity<ProductoDto> agregarProducto(@Valid @NonNull @RequestBody ProductoDto dto) {
		return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@GetMapping("/REST")
	public ResponseEntity<List<ProductoDto>> getAllProductos(
			@RequestParam(name = "search", required = false) String search) {
		List<ProductoDto> productos = servicio.findAll(search);
		if (productos.isEmpty()) {
			return new ResponseEntity<List<ProductoDto>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(productos, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@GetMapping("/REST/{id}")
	public ResponseEntity<ProductoDto> getProductoById(@PathVariable("id") int id) {
		Optional<ProductoDto> oDto = servicio.findById(id);
		if (oDto.isPresent()) {
			ProductoDto dto = oDto.get();
			return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
		} else {
			return new ResponseEntity<ProductoDto>(HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@PutMapping(("/REST"))
	public ResponseEntity<ProductoDto> updateProductoById(@Valid @NonNull @RequestBody ProductoDto dto) {
		Optional<ProductoDto> oDto = servicio.findById(dto.getId());
		if (oDto.isPresent()) {
			return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
		}
		return new ResponseEntity<ProductoDto>(HttpStatus.NO_CONTENT);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@DeleteMapping("/REST/{id}")
	public ResponseEntity<ProductoDto> deleteProductoById(@PathVariable("id") int id) {
		Optional<ProductoDto> oDto = servicio.findById(id);
		if (oDto.isPresent()) {
			ProductoDto dto = oDto.get();
			servicio.delete(oDto.get());
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else
			return new ResponseEntity<ProductoDto>(HttpStatus.NO_CONTENT);
	}
}
