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

import com.grupo2.backend.dto.EncargadoDto;
import com.grupo2.backend.service.ICrudServiceEncargado;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.Valid;

@RestController
@RequestMapping("encargado")
public class ControladorEncargado {
    @Autowired
    private ICrudServiceEncargado servicio;

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PostMapping("/REST")
    public ResponseEntity<EncargadoDto> agregarEncargado(@Valid @NonNull @RequestBody EncargadoDto dto) {
        return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping("/REST")
    public ResponseEntity<List<EncargadoDto>> getAllEncargado(
            @RequestParam(name = "search", required = false) String search) {
        List<EncargadoDto> encargados = servicio.findAll(search);
        if (encargados.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(encargados, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping("/REST/{id}")
    public ResponseEntity<EncargadoDto> getEncargadoById(@PathVariable("id") int id) {
        Optional<EncargadoDto> oDto = servicio.findById(id);
        if (oDto.isPresent()) {
            EncargadoDto dto = oDto.get();
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PutMapping("/REST")
    public ResponseEntity<EncargadoDto> updateEncargadoById(@Valid @NonNull @RequestBody EncargadoDto dto) {
        Optional<EncargadoDto> oDto = servicio.findById(dto.getId());
        if (oDto.isPresent()) {
            return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @DeleteMapping("/REST/{id}")
    public ResponseEntity<EncargadoDto> deleteEncargadoById(@PathVariable("id") int id) {
        Optional<EncargadoDto> oDto = servicio.findById(id);
        if (oDto.isPresent()) {
            EncargadoDto dto = oDto.get();
            servicio.delete(dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
