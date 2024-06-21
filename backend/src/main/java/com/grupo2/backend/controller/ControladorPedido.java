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

import com.grupo2.backend.dto.PedidoDto;
import com.grupo2.backend.service.ICrudServicePedido;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pedido")
public class ControladorPedido {
    
    @Autowired
    private ICrudServicePedido servicio;

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PostMapping("/REST")
    public ResponseEntity<PedidoDto> agregarPedido(@Valid @NonNull @RequestBody PedidoDto dto) {
        return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping("/REST")
    public ResponseEntity<List<PedidoDto>> getAllPedidos(
            @RequestParam(name = "search", required = false) String search) {
        List<PedidoDto> pedidos = servicio.findAll(search);
        if (pedidos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping("/REST/{id}")
    public ResponseEntity<PedidoDto> getPedidoById(@PathVariable("id") int id) {
        Optional<PedidoDto> oDto = servicio.findById(id);
        if (oDto.isPresent()) {
            return new ResponseEntity<>(oDto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PutMapping("/REST")
    public ResponseEntity<PedidoDto> updatePedidoById(@Valid @NonNull @RequestBody PedidoDto dto) {
        Optional<PedidoDto> oDto = servicio.findById(dto.getId_pedido());
        if (oDto.isPresent()) {
            return new ResponseEntity<>(servicio.save(dto), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @DeleteMapping("/REST/{id}")
    public ResponseEntity<PedidoDto> deletePedidoById(@PathVariable("id") int id) {
        Optional<PedidoDto> oDto = servicio.findById(id);
        if (oDto.isPresent()) {
            PedidoDto dto = oDto.get();
            servicio.delete(dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
