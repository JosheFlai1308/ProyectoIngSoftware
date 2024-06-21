package com.grupo2.frontend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grupo2.frontend.dto.PedidoDto;
import com.grupo2.frontend.service.ICrudServicePedido;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("pedido")
public class ControladorPedido {

    @Autowired
    public ICrudServicePedido servicio;

    // http://localhost:8081/pedido/listar/REST
    @GetMapping("listar/REST")
    public String listarREST(Model model, @RequestParam(name = "search", required = false) String search, HttpServletRequest request) {
        List<PedidoDto> pedidos = null;
        try {
            pedidos = servicio.findAllREST(search);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            e.printStackTrace();
            return "rest/pedido/index";
        }
        if (pedidos != null) {
            model.addAttribute("pedidos", pedidos);
            model.addAttribute("search", search);
            model.addAttribute("Message", "Se han cargado todos los pedidos");
        } else {
            model.addAttribute("errorMessage", "Ocurrió un error en listar/REST");
        }
        return "rest/pedido/index";
    }

    // http://localhost:8081/pedido/nuevo/REST
    @GetMapping("listar/nuevo/REST")
    public String agregarREST(Model model) {
        model.addAttribute("pedido", new PedidoDto());
        return "rest/pedido/form";
    }

    // http://localhost:8081/pedido/editar/REST/id
    @GetMapping("editar/REST/{id}")
    public String editarREST(@PathVariable int id, Model model) {
        PedidoDto pedido = null;
        try {
            Optional<PedidoDto> dto = servicio.findByIdREST(id);
            pedido = dto.get();
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            e.printStackTrace();
            return "rest/pedido/index";
        }
        if (pedido == null) {
            model.addAttribute("errorMessage", "Ocurrió un error en editar/REST/{id}");
        } else {
            model.addAttribute("pedido", pedido);
        }
        return "rest/pedido/form";
    }

    // http://localhost:8081/pedido/grabar/REST
    @PostMapping("grabar/REST")
    public String saveREST(@Valid PedidoDto p, Model model) {
        PedidoDto pedido = null;
        if (p.getId_pedido() == 0) {
            try {
                pedido = servicio.saveREST(p);
            } catch (Exception e) {
                model.addAttribute("errorMessage", e.getMessage());
                e.printStackTrace();
                return "rest/pedido/index";
            }
            if (pedido == null) {
                model.addAttribute("errorMessage", "Ocurrió un error en grabar/REST SAVE");
                return "rest/pedido/form";
            }
        } else {
            try {
                pedido = servicio.editarREST(p);
            } catch (Exception e) {
                model.addAttribute("errorMessage", e.getMessage());
                e.printStackTrace();
                return "rest/pedido/index";
            }
            if (pedido == null) {
                model.addAttribute("errorMessage", "Ocurrió un error en grabar/REST EDITAR");
            }
        }
        return "redirect:/pedido/listar/REST";
    }

    // http://localhost:8081/pedido/eliminar/id
    @GetMapping("eliminar/REST/{id}")
    public String deleteREST(@PathVariable int id, Model model) {
        PedidoDto pedido;
        try {
            pedido = servicio.deleteREST(id);
            if (pedido == null) {
                model.addAttribute("errorMessage", "Ocurrió un error en eliminar/REST/{id}");
            } else {
                return "redirect:/pedido/listar/REST";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            e.printStackTrace();
            return "rest/pedido/index";
        }
        return "redirect:/pedido/listar/REST";
    }

    // REST
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////77
    // FX

    public List<PedidoDto> getAll(Model model) {
        try {
            List<PedidoDto> pedidos = servicio.findAllREST(null);
            model.addAttribute("null", pedidos);
            return pedidos;
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public PedidoDto found(String idp) {
        StringBuilder nros = new StringBuilder();
        for (char l : idp.toCharArray()) {
            if (Character.isDigit(l)) {
                nros.append(l);
            }
        }
        int id = Integer.parseInt(nros.toString());
        try {
            Optional<PedidoDto> dto = servicio.findByIdREST(id);
            return dto.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
