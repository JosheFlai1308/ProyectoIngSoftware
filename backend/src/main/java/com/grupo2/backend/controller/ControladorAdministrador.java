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

import com.grupo2.backend.dto.AdministradorDTO;
import com.grupo2.backend.service.ICrudServiceAdministrador;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.Valid;

@RestController
@RequestMapping("administrador")
public class ControladorAdministrador {
    @Autowired
    private ICrudServiceAdministrador servicio;

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PostMapping("/REST")
    public ResponseEntity<AdministradorDTO> agregarAdministrador(@Valid @NonNull @RequestBody AdministradorDTO dto) {
        return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping("/REST")
    public ResponseEntity<List<AdministradorDTO>> getAllAdministrador(
            @RequestParam(name = "search", required = false) String search) {
        List<AdministradorDTO> administradores = servicio.findAll(search);
        if (administradores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(administradores, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping("/REST/{id}")
    public ResponseEntity<AdministradorDTO> getAdministradorById(@PathVariable("id") int id) {
        Optional<AdministradorDTO> oDto = servicio.findById(id);
        if (oDto.isPresent()) {
            AdministradorDTO dto = oDto.get();
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PutMapping("/REST")
    public ResponseEntity<AdministradorDTO> updateAdministradorById(@Valid @NonNull @RequestBody AdministradorDTO dto) {
        Optional<AdministradorDTO> oDto = servicio.findById(dto.getId());
        if (oDto.isPresent()) {
            return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
        }
        return new ResponseEntity<AdministradorDTO>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @DeleteMapping("/REST/{id}")
    public ResponseEntity<AdministradorDTO> deleteAdministradorById(@PathVariable("id") int id) {
        Optional<AdministradorDTO> oDto = servicio.findById(id);
        if (oDto.isPresent()) {
            AdministradorDTO dto = oDto.get();
            servicio.delete(dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<AdministradorDTO>(HttpStatus.NO_CONTENT);
        }
    }
}
