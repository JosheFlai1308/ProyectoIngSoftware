package com.grupo2.frontend.service;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.grupo2.frontend.dto.Guia_DespachoDto;
import com.grupo2.frontend.dto.EncargadoDto;

@Component
@Service
public class CrudServiceGuia_Despacho implements ICrudServiceGuia_Despacho {

    @Autowired
    private ICrudServiceEncargado servicioEncargado;

    private String getBasicAuthHeader() {
        String credentials = "admin:password";
        return new String(Base64.getEncoder().encodeToString(credentials.getBytes()));
    }

    private HttpHeaders httpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic " + getBasicAuthHeader());
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }

    @Override
    public List<Guia_DespachoDto> findAllREST(String search) throws Exception {
        String url = "http://localhost:8080/guia_despacho/REST";

        if (search != null) {
            url += "?search=" + search;
        }

        HttpHeaders headers = httpHeaders();

        ResponseEntity<Guia_DespachoDto[]> responseEntity = new RestTemplate().exchange(
                url, HttpMethod.GET, new HttpEntity<>(headers), Guia_DespachoDto[].class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            List<Guia_DespachoDto> guias = Arrays.asList(responseEntity.getBody());
            for (Guia_DespachoDto guia : guias) {
                if (guia.getEncargados() != null && !guia.getEncargados().isEmpty()) {
                    guia.setEncargadoNombre(guia.getEncargados().get(0).getNombre_encargado());
                }
            }
            return guias;
        } else {
            throw new Exception(this.getClass().getCanonicalName() + " method:findAllREST Error API Rest");
        }
    }

    @Override
    public Optional<Guia_DespachoDto> findByIdREST(int id) throws Exception {
        Guia_DespachoDto dto = null;

        ResponseEntity<Guia_DespachoDto> responseEntity = new RestTemplate().exchange(
                "http://localhost:8080/guia_despacho/REST/" + id,
                HttpMethod.GET, new HttpEntity<>(httpHeaders()), new ParameterizedTypeReference<Guia_DespachoDto>() {
                });

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            if (responseEntity.hasBody()) {
                dto = responseEntity.getBody();

                if (dto == null) {
                    throw new Exception("Failed to edit Guia_DespachoDto; response body was null.");
                }

                if (dto.getEncargadoId() != null) {
                    EncargadoDto encargado = servicioEncargado.findByIdREST(dto.getEncargadoId()).orElse(null);
                    if (encargado != null) {
                        dto.setEncargadoNombre(encargado.getNombre_encargado());
                    }
                }
            }
        } else {
            throw new Exception(this.getClass().getCanonicalName() + " method:findByIdREST Error API Rest");
        }
        return Optional.of(dto);
    }

    @Override
    public Guia_DespachoDto editarREST(Guia_DespachoDto p) throws Exception {
        Guia_DespachoDto dto = null;

        ResponseEntity<Guia_DespachoDto> responseEntity = new RestTemplate().exchange(
                "http://localhost:8080/guia_despacho/REST",
                HttpMethod.PUT, new HttpEntity<>(p, httpHeaders()), new ParameterizedTypeReference<Guia_DespachoDto>() {
                });

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            if (responseEntity.hasBody()) {
                dto = responseEntity.getBody();
            }
        } else {
            throw new Exception(this.getClass().getCanonicalName() + " method:editarREST Error API Rest");
        }
        return dto;
    }

    @Override
    public Guia_DespachoDto saveREST(Guia_DespachoDto p) throws Exception {
        Guia_DespachoDto dto = null;

        ResponseEntity<Guia_DespachoDto> responseEntity = new RestTemplate().exchange(
                "http://localhost:8080/guia_despacho/REST",
                HttpMethod.POST, new HttpEntity<>(p, httpHeaders()),
                new ParameterizedTypeReference<Guia_DespachoDto>() {
                });

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            if (responseEntity.hasBody()) {
                dto = responseEntity.getBody();
            }
        } else {
            throw new Exception(this.getClass().getCanonicalName() + " method:saveREST Error API Rest");
        }
        return dto;
    }

    @Override
    public Guia_DespachoDto deleteREST(int id) throws Exception {
        Guia_DespachoDto dto = null;

        ResponseEntity<Guia_DespachoDto> responseEntity = new RestTemplate().exchange(
                "http://localhost:8080/guia_despacho/REST/" + id,
                HttpMethod.DELETE, new HttpEntity<>(httpHeaders()), new ParameterizedTypeReference<Guia_DespachoDto>() {
                });

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            if (responseEntity.hasBody()) {
                dto = responseEntity.getBody();
            }
        } else {
            throw new Exception(this.getClass().getCanonicalName() + " method:deleteREST Error API Rest");
        }
        return dto;
    }
}