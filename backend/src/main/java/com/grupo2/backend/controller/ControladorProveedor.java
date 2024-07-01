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

import com.grupo2.backend.dto.ProveedorDto;
import com.grupo2.backend.service.ICrudServiceProveedor;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.Valid;

@RestController
@RequestMapping("proveedor")
public class ControladorProveedor {
    
    @Autowired
	private ICrudServiceProveedor servicio;

	@GetMapping("/{id_proveedor}/notas_conformidad")
    public List<Float> getNotasConformidad(@PathVariable int id_proveedor) {
        return servicio.getNotasConformidadPedido(id_proveedor);
    }

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@PostMapping("/REST")
	public ResponseEntity<ProveedorDto> agregarProveedor(@Valid @NonNull @RequestBody ProveedorDto dto) {
		return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@GetMapping("/REST")
	public ResponseEntity<List<ProveedorDto>> getAllProveedors(
			@RequestParam(name = "search", required = false) String search) {
		List<ProveedorDto> Proveedors = servicio.findAll(search);
		if (Proveedors.isEmpty()) {
			return new ResponseEntity<List<ProveedorDto>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(Proveedors, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@GetMapping("/REST/{id}")
	public ResponseEntity<ProveedorDto> getProveedorById(@PathVariable("id") int id) {
		Optional<ProveedorDto> oDto = servicio.findById(id);
		if (oDto.isPresent()) {
			ProveedorDto dto = oDto.get();
			return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
		} else {
			return new ResponseEntity<ProveedorDto>(HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@PutMapping(("/REST"))
	public ResponseEntity<ProveedorDto> updateProveedorById(@Valid @NonNull @RequestBody ProveedorDto dto) {
		Optional<ProveedorDto> oDto = servicio.findById(dto.getId());
		if (oDto.isPresent()) {
			return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
		}
		return new ResponseEntity<ProveedorDto>(HttpStatus.NO_CONTENT);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@DeleteMapping("/REST/{id}")
	public ResponseEntity<ProveedorDto> deleteProveedorById(@PathVariable("id") int id) {
		Optional<ProveedorDto> oDto = servicio.findById(id);
		if (oDto.isPresent()) {
			ProveedorDto dto = oDto.get();
			servicio.delete(oDto.get());
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else
			return new ResponseEntity<ProveedorDto>(HttpStatus.NO_CONTENT);
	}
}
