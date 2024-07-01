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

import com.grupo2.frontend.dto.EncargadoDto;

@Component
@Service
public class CrudServiceEncargado implements ICrudServiceEncargado {

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
    public List<EncargadoDto> findAllREST(String search) throws Exception {
        String url = "http://localhost:8080/encargado/REST";

        if (search != null) {
            url += "?search=" + search;
        }

        HttpHeaders headers = httpHeaders();

        ResponseEntity<EncargadoDto[]> responseEntity = new RestTemplate().exchange(
                url, HttpMethod.GET, new HttpEntity<>(headers), EncargadoDto[].class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return Arrays.asList(responseEntity.getBody());
        } else {
            throw new Exception(this.getClass().getCanonicalName() + " method:findAllREST Error API Rest");
        }
    }

    @Override
    public Optional<EncargadoDto> findByIdREST(int id) throws Exception {
        EncargadoDto dto = null;

        ResponseEntity<EncargadoDto> responseEntity = new RestTemplate().exchange(
                "http://localhost:8080/encargado/REST/" + id,
                HttpMethod.GET, new HttpEntity<>(httpHeaders()), new ParameterizedTypeReference<EncargadoDto>() {
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
    public EncargadoDto editarREST(EncargadoDto p) throws Exception {
        EncargadoDto dto = null;

        ResponseEntity<EncargadoDto> responseEntity = new RestTemplate().exchange(
                "http://localhost:8080/encargado/REST",
                HttpMethod.PUT, new HttpEntity<>(p, httpHeaders()), new ParameterizedTypeReference<EncargadoDto>() {
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
    public EncargadoDto saveREST(EncargadoDto p) throws Exception {
        EncargadoDto dto = null;

        ResponseEntity<EncargadoDto> responseEntity = new RestTemplate().exchange(
                "http://localhost:8080/encargado/REST",
                HttpMethod.POST, new HttpEntity<>(p, httpHeaders()), new ParameterizedTypeReference<EncargadoDto>() {
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
    public EncargadoDto deleteREST(int id) throws Exception {
        EncargadoDto dto = null;

        ResponseEntity<EncargadoDto> responseEntity = new RestTemplate().exchange(
                "http://localhost:8080/encargado/REST/" + id,
                HttpMethod.DELETE, new HttpEntity<>(httpHeaders()), new ParameterizedTypeReference<EncargadoDto>() {
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
