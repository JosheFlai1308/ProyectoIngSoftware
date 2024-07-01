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

import com.grupo2.frontend.dto.ProveedorDto;

@Component
@Service
public class CrudServiceProveedor implements ICrudServiceProveedor{

	public List<Float> getNotasConformidad(int id_proveedor){
		return null;
		
	}
    
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
	public List<ProveedorDto> findAllREST(String search) throws Exception {
		String url = "http://localhost:8080/proveedor/REST";

		if (search != null) {
			url += "?search=" + search;
		}

		HttpHeaders headers = httpHeaders();

		ResponseEntity<ProveedorDto[]> responseEntity = new RestTemplate().exchange(
				url, HttpMethod.GET, new HttpEntity<>(headers), ProveedorDto[].class);

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			return Arrays.asList(responseEntity.getBody());
		} else {
			throw new Exception(this.getClass().getCanonicalName() + " method:findAllREST Error API Rest");
		}
	}

	@Override
	public Optional<ProveedorDto> findByIdREST(int id) throws Exception {
		ProveedorDto dto = null;

		ResponseEntity<ProveedorDto> responseEntity = new RestTemplate().exchange(
				"http://localhost:8080/proveedor/REST/" + id,
				HttpMethod.GET, new HttpEntity<>(httpHeaders()), new ParameterizedTypeReference<ProveedorDto>() {
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
	public ProveedorDto editarREST(ProveedorDto p) throws Exception {
		ProveedorDto dto = null;

		ResponseEntity<ProveedorDto> responseEntity = new RestTemplate().exchange("http://localhost:8080/proveedor/REST",
				HttpMethod.PUT, new HttpEntity<>(p, httpHeaders()), new ParameterizedTypeReference<ProveedorDto>() {
				});

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			if (responseEntity.hasBody()) {
				dto = responseEntity.getBody();
			}
		} else {
			throw new Exception(
					this.getClass().getCanonicalName() + " FrontEdnd Service method:editarREST Error API Rest");
		}
		return dto;

	}

	@Override
	public ProveedorDto saveREST(ProveedorDto p) throws Exception {
		ProveedorDto dto = null;

		ResponseEntity<ProveedorDto> responseEntity = new RestTemplate().exchange("http://localhost:8080/proveedor/REST",
				HttpMethod.POST, new HttpEntity<>(p, httpHeaders()), new ParameterizedTypeReference<ProveedorDto>() {
				});

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			if (responseEntity.hasBody()) {
				dto = responseEntity.getBody();
			}
		} else {
			throw new Exception(
					this.getClass().getCanonicalName() + " FrontEdnd Service method:saveREST Error API Rest");
		}
		return dto;

	}

	@Override
	public ProveedorDto deleteREST(int id) throws Exception {
		ProveedorDto dto = null;

		ResponseEntity<ProveedorDto> responseEntity = new RestTemplate().exchange(
				"http://localhost:8080/proveedor/REST/" + id,
				HttpMethod.DELETE, new HttpEntity<>(httpHeaders()), new ParameterizedTypeReference<ProveedorDto>() {
				});

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			if (responseEntity.hasBody()) {
				dto = responseEntity.getBody();
			}
		} else {
			throw new Exception(
					this.getClass().getCanonicalName() + " FrontEdnd Service method:deleteREST Error API Rest");
		}
		return dto;

	}
}
