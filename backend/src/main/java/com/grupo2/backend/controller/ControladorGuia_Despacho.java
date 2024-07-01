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


import com.grupo2.backend.dto.Guia_DespachoDto;

import com.grupo2.backend.service.ICrudServiceGuia_Despacho;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.Valid;

@RestController
@RequestMapping("guia_despacho")
public class ControladorGuia_Despacho {
    @Autowired
    private ICrudServiceGuia_Despacho servicio;

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PostMapping("/REST")
    public ResponseEntity<Guia_DespachoDto> agregarEncargado(@Valid @NonNull @RequestBody Guia_DespachoDto dto) {
        return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping("/REST")
    public ResponseEntity<List<Guia_DespachoDto>> getAllEncargado(
            @RequestParam(name = "search", required = false) String search) {
        List<Guia_DespachoDto> guias = servicio.findAll(search);
        if (guias.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(guias, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping("/REST/{id}")
    public ResponseEntity<Guia_DespachoDto> getEncargadoById(@PathVariable("id") int id) {
        Optional<Guia_DespachoDto> oDto = servicio.findById(id);
        if (oDto.isPresent()) {
            Guia_DespachoDto dto = oDto.get();
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PutMapping("/REST")
    public ResponseEntity<Guia_DespachoDto> updateEncargadoById(@Valid @NonNull @RequestBody Guia_DespachoDto dto) {
        Optional<Guia_DespachoDto> oDto = servicio.findById(dto.getId());
        if (oDto.isPresent()) {
            return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
        }
        return new ResponseEntity<Guia_DespachoDto>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @DeleteMapping("/REST/{id}")
    public ResponseEntity<Guia_DespachoDto> deleteEncargadoById(@PathVariable("id") int id) {
        Optional<Guia_DespachoDto> oDto = servicio.findById(id);
        if (oDto.isPresent()) {
            Guia_DespachoDto dto = oDto.get();
            servicio.delete(dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<Guia_DespachoDto>(HttpStatus.NO_CONTENT);
        }
    }
    
}
