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

import com.grupo2.backend.dto.Linea_detalle_pDto;
import com.grupo2.backend.service.ICrudServiceLinea_detalle_p;
import io.micrometer.common.lang.NonNull;
import jakarta.validation.Valid;

@RestController
@RequestMapping("linea_detalle_p")
public class ControladorLinea_detalle_p {

    @Autowired
	private ICrudServiceLinea_detalle_p servicio;

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@PostMapping("/REST")
	public ResponseEntity<Linea_detalle_pDto> agregarLinea_detalle_p(@Valid @NonNull @RequestBody Linea_detalle_pDto dto) {
		return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@GetMapping("/REST")
	public ResponseEntity<List<Linea_detalle_pDto>> getAllLinea_detalle_ps(
			@RequestParam(name = "search", required = false) String search) {
		List<Linea_detalle_pDto> linea_detalle_ps = servicio.findAll(search);
		if (linea_detalle_ps.isEmpty()) {
			return new ResponseEntity<List<Linea_detalle_pDto>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(linea_detalle_ps, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@GetMapping("/REST/{id}")
	public ResponseEntity<Linea_detalle_pDto> getLinea_detalle_pById(@PathVariable("id") int id) {
		Optional<Linea_detalle_pDto> oDto = servicio.findById(id);
		if (oDto.isPresent()) {
			Linea_detalle_pDto dto = oDto.get();
			return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
		} else {
			return new ResponseEntity<Linea_detalle_pDto>(HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@PutMapping("/REST")
	public ResponseEntity<Linea_detalle_pDto> updateLinea_detalle_pById(@Valid @NonNull @RequestBody Linea_detalle_pDto dto) {
		Optional<Linea_detalle_pDto> oDto = servicio.findById(dto.getId_linea_detalle());
		if (oDto.isPresent()) {
			return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
		}
		return new ResponseEntity<Linea_detalle_pDto>(HttpStatus.NO_CONTENT);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@DeleteMapping("/REST/{id}")
	public ResponseEntity<Linea_detalle_pDto> deleteLinea_detalle_pById(@PathVariable("id") int id) {
		Optional<Linea_detalle_pDto> oDto = servicio.findById(id);
		if (oDto.isPresent()) {
			Linea_detalle_pDto dto = oDto.get();
			servicio.delete(oDto.get());
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<Linea_detalle_pDto>(HttpStatus.NO_CONTENT);
		}
	}
}
