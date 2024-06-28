package com.grupo2.frontend.service;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.grupo2.frontend.dto.AdministradorDTO;
@Component
@Service
public class CrudServiceAdministrador implements ICrudServiceAdministrador {

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
    public List<AdministradorDTO> findAllREST(String search) throws Exception {
        String url = "http://localhost:8080/administrador/REST";

        if (search != null) {
            url += "?search=" + search;
        }

        HttpHeaders headers = httpHeaders();

        ResponseEntity<AdministradorDTO[]> responseEntity = new RestTemplate().exchange(
                url, HttpMethod.GET, new HttpEntity<>(headers), AdministradorDTO[].class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return Arrays.asList(responseEntity.getBody());
        } else {
            throw new Exception(this.getClass().getCanonicalName() + " method:findAllREST Error API Rest");
        }
    }

    @Override
    public Optional<AdministradorDTO> findByIdREST(int id) throws Exception {
        AdministradorDTO dto = null;

        ResponseEntity<AdministradorDTO> responseEntity = new RestTemplate().exchange(
                "http://localhost:8080/amdministrador/REST/" + id,
                HttpMethod.GET, new HttpEntity<>(httpHeaders()), new ParameterizedTypeReference<AdministradorDTO>() {
                });

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            if (responseEntity.hasBody()) {
                dto = responseEntity.getBody();
            }
        } else {
            throw new Exception(this.getClass().getCanonicalName() + " method:findByIdREST Error API Rest");
        }
        return Optional.of(dto);
    }

    @Override
    public AdministradorDTO editarREST(AdministradorDTO a) throws Exception {
        AdministradorDTO dto = null;

        ResponseEntity<AdministradorDTO> responseEntity = new RestTemplate().exchange(
                "http://localhost:8080/administrador/REST",
                HttpMethod.PUT, new HttpEntity<>(a, httpHeaders()), new ParameterizedTypeReference<AdministradorDTO>() {
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
    public AdministradorDTO saveREST(AdministradorDTO a) throws Exception {
        AdministradorDTO dto = null;

        ResponseEntity<AdministradorDTO> responseEntity = new RestTemplate().exchange(
                "http://localhost:8080/administrador/REST",
                HttpMethod.POST, new HttpEntity<>(a, httpHeaders()), new ParameterizedTypeReference<AdministradorDTO>() {
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
    public AdministradorDTO deleteREST(int id) throws Exception {
        AdministradorDTO dto = null;

        ResponseEntity<AdministradorDTO> responseEntity = new RestTemplate().exchange(
                "http://localhost:8080/administrador/REST/" + id,
                HttpMethod.DELETE, new HttpEntity<>(httpHeaders()), new ParameterizedTypeReference<AdministradorDTO>() {
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
