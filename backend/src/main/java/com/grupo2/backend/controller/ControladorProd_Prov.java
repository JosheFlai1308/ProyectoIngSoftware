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

import com.grupo2.backend.dto. Prod_ProvDto;
import com.grupo2.backend.service.ICrudServiceProd_Prov;
import io.micrometer.common.lang.NonNull;
import jakarta.validation.Valid;

@RestController
@RequestMapping("prod_prov")
public class ControladorProd_Prov {

    @Autowired
	private ICrudServiceProd_Prov servicio;

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@PostMapping("/REST")
	public ResponseEntity< Prod_ProvDto> agregarProd_Prov(@Valid @NonNull @RequestBody  Prod_ProvDto dto) {
		return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@GetMapping("/REST")
	public ResponseEntity<List< Prod_ProvDto>> getAllProd_Provs(
			@RequestParam(name = "search", required = false) String search) {
		List< Prod_ProvDto>  prod_provs = servicio.findAll(search);
		if ( prod_provs.isEmpty()) {
			return new ResponseEntity<List< Prod_ProvDto>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>( prod_provs, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@GetMapping("/REST/{id}")
	public ResponseEntity< Prod_ProvDto> getProd_ProvById(@PathVariable("id") int id) {
		Optional< Prod_ProvDto> oDto = servicio.findById(id);
		if (oDto.isPresent()) {
			 Prod_ProvDto dto = oDto.get();
			return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
		} else {
			return new ResponseEntity< Prod_ProvDto>(HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@PutMapping(("/REST"))
	public ResponseEntity< Prod_ProvDto> updateProd_ProvById(@Valid @NonNull @RequestBody  Prod_ProvDto dto) {
		Optional< Prod_ProvDto> oDto = servicio.findById(dto.getId());
		if (oDto.isPresent()) {
			return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
		}
		return new ResponseEntity< Prod_ProvDto>(HttpStatus.NO_CONTENT);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	@DeleteMapping("/REST/{id}")
	public ResponseEntity< Prod_ProvDto> deleteProd_ProvById(@PathVariable("id") int id) {
		Optional< Prod_ProvDto> oDto = servicio.findById(id);
		if (oDto.isPresent()) {
			 Prod_ProvDto dto = oDto.get();
			servicio.delete(oDto.get());
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else
			return new ResponseEntity<Prod_ProvDto>(HttpStatus.NO_CONTENT);
	}
}
