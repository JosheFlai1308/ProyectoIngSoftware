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

import com.grupo2.backend.dto.LineadetalleCDTO;
import com.grupo2.backend.service.ICrudServiceLineadetalleC;
import io.micrometer.common.lang.NonNull;
import jakarta.validation.Valid;

@RestController
@RequestMapping("lineadetalleC")
public class ControladorLineadetalleC {

    @Autowired
	private ICrudServiceLineadetalleC servicio;

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@PostMapping("/REST")
	public ResponseEntity<LineadetalleCDTO> agregarLineadetalleC(@Valid @NonNull @RequestBody LineadetalleCDTO dto) {
		return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@GetMapping("/REST")
	public ResponseEntity<List<LineadetalleCDTO>> getAllLineadetallesC(
			@RequestParam(name = "search", required = false) String search) {
		List<LineadetalleCDTO> lineadetallesC = servicio.findAll(search);
		if (lineadetallesC.isEmpty()) {
			return new ResponseEntity<List<LineadetalleCDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lineadetallesC, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@GetMapping("/REST/{id}")
	public ResponseEntity<LineadetalleCDTO> getLineadetalleCById(@PathVariable("id") int id) {
		Optional<LineadetalleCDTO> oDto = servicio.findById(id);
		if (oDto.isPresent()) {
			LineadetalleCDTO dto = oDto.get();
			return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
		} else {
			return new ResponseEntity<LineadetalleCDTO>(HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@PutMapping("/REST")
	public ResponseEntity<LineadetalleCDTO> updateLineadetalleCById(@Valid @NonNull @RequestBody LineadetalleCDTO dto) {
		Optional<LineadetalleCDTO> oDto = servicio.findById(dto.getId_linea_detallec());
		if (oDto.isPresent()) {
			return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
		}
		return new ResponseEntity<LineadetalleCDTO>(HttpStatus.NO_CONTENT);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@DeleteMapping("/REST/{id}")
	public ResponseEntity<LineadetalleCDTO> deleteLineadetalleCById(@PathVariable("id") int id) {
		Optional<LineadetalleCDTO> oDto = servicio.findById(id);
		if (oDto.isPresent()) {
			LineadetalleCDTO dto = oDto.get();
			servicio.delete(oDto.get());
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<LineadetalleCDTO>(HttpStatus.NO_CONTENT);
		}
	}
}
