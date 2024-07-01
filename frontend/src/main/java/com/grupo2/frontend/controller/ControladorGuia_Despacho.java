package com.grupo2.frontend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grupo2.frontend.dto.EncargadoDto;
import com.grupo2.frontend.dto.Guia_DespachoDto;
import com.grupo2.frontend.service.ICrudServiceEncargado;
import com.grupo2.frontend.service.ICrudServiceGuia_Despacho;

import jakarta.validation.Valid;

@Controller
@RequestMapping("guia_despacho")
public class ControladorGuia_Despacho {
    
    @Autowired
    public ICrudServiceGuia_Despacho servicio;

    @Autowired
    public ICrudServiceEncargado servicioEncargado;

    @GetMapping("listar/REST")
    public String listarREST(Model model, @RequestParam(name = "search", required = false) String search) {
        List<Guia_DespachoDto> guias;
        try {
            guias = servicio.findAllREST(search);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Ocurrió un error al cargar las guías de despacho");
            e.printStackTrace();
            return "rest/guia_despacho/index";
        }

        model.addAttribute("guias", guias);
        model.addAttribute("search", search);
        model.addAttribute("message", "Se han cargado todas las guías de despacho");

        return "rest/guia_despacho/index";
    }

    @GetMapping("listar/nuevo/REST")
    public String agregarREST(Model model) {
        List<EncargadoDto> encargados;
        try {
            encargados = servicioEncargado.findAllREST(null);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Ocurrió un error al cargar los encargados");
            e.printStackTrace();
            return "rest/guia_despacho/form";
        }

        model.addAttribute("guia", new Guia_DespachoDto());
        model.addAttribute("encargados", encargados);
        return "rest/guia_despacho/form";
    }

    @GetMapping("editar/REST/{id}")
    public String editarREST(@PathVariable int id, Model model) {
        Guia_DespachoDto guia = null;
        List<EncargadoDto> encargados;
        try {
            Optional<Guia_DespachoDto> dto = servicio.findByIdREST(id);
            guia = dto.orElse(null);
            encargados = servicioEncargado.findAllREST(null);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            e.printStackTrace();
            return "rest/guia_despacho/index";
        }
        if (guia == null) {
            model.addAttribute("errorMessage", "Ocurrió un error en editar/REST/{id}");
        } else {
            model.addAttribute("guia", guia);
            model.addAttribute("encargados", encargados);
        }
        return "rest/guia_despacho/form";
    }

    @PostMapping("grabar/REST")
    public String saveREST(@Valid Guia_DespachoDto p, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Error en los datos ingresados");
            return "rest/guia_despacho/form";
        }

        Guia_DespachoDto guia;
        try {
            if (p.getId() == 0) {
                guia = servicio.saveREST(p);
            } else {
                guia = servicio.editarREST(p);
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            e.printStackTrace();
            return "rest/guia_despacho/index";
        }

        if (guia == null) {
            model.addAttribute("errorMessage", "Ocurrió un error al guardar/actualizar la guía de despacho");
            return "rest/guia_despacho/form";
        }

        return "redirect:/guia_despacho/listar/REST";
    }

    @GetMapping("eliminar/REST/{id}")
    public String deleteREST(@PathVariable int id, Model model) {
        Guia_DespachoDto guia;
        try {
            guia = servicio.deleteREST(id);
            if (guia == null) {
                model.addAttribute("errorMessage", "Ocurrió un error en eliminar/REST/{id}");
            } else {
                return "redirect:/guia_despacho/listar/REST";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            e.printStackTrace();
            return "rest/guia_despacho/index";
        }
        return "redirect:/guia_despacho/listar/REST";
    }
}
