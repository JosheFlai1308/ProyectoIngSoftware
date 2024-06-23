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

import com.grupo2.frontend.dto.Linea_detalle_pDto;

@Component
@Service
public class CrudServiceLinea_detalle_p implements ICrudServiceLinea_detalle_p {

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
	public List<Linea_detalle_pDto> findAllREST(String search) throws Exception {
		String url = "http://localhost:8080/linea_detalle_p/REST";

		if (search != null) {
			url += "?search=" + search;
		}

		HttpHeaders headers = httpHeaders();

		ResponseEntity<Linea_detalle_pDto[]> responseEntity = new RestTemplate().exchange(
				url, HttpMethod.GET, new HttpEntity<>(headers), Linea_detalle_pDto[].class);

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			return Arrays.asList(responseEntity.getBody());
		} else {
			throw new Exception(this.getClass().getCanonicalName() + " method:findAllREST Error API Rest");
		}
	}

	@Override
	public Optional<Linea_detalle_pDto> findByIdREST(int id) throws Exception {
		Linea_detalle_pDto dto = null;

		ResponseEntity<Linea_detalle_pDto> responseEntity = new RestTemplate().exchange(
				"http://localhost:8080/linea_detalle_p/REST/" + id,
				HttpMethod.GET, new HttpEntity<>(httpHeaders()), new ParameterizedTypeReference<Linea_detalle_pDto>() {
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
	public Linea_detalle_pDto editarREST(Linea_detalle_pDto p) throws Exception {
		Linea_detalle_pDto dto = null;

		ResponseEntity<Linea_detalle_pDto> responseEntity = new RestTemplate().exchange(
				"http://localhost:8080/linea_detalle_p/REST",
				HttpMethod.PUT, new HttpEntity<>(p, httpHeaders()),
				new ParameterizedTypeReference<Linea_detalle_pDto>() {
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
	public Linea_detalle_pDto saveREST(Linea_detalle_pDto p) throws Exception {
		Linea_detalle_pDto dto = null;

		ResponseEntity<Linea_detalle_pDto> responseEntity = new RestTemplate().exchange(
				"http://localhost:8080/linea_detalle_p/REST",
				HttpMethod.POST, new HttpEntity<>(p, httpHeaders()),
				new ParameterizedTypeReference<Linea_detalle_pDto>() {
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
	public Linea_detalle_pDto deleteREST(int id) throws Exception {
		Linea_detalle_pDto dto = null;

		ResponseEntity<Linea_detalle_pDto> responseEntity = new RestTemplate().exchange(
				"http://localhost:8080/linea_detalle_p/REST/" + id,
				HttpMethod.DELETE, new HttpEntity<>(httpHeaders()),
				new ParameterizedTypeReference<Linea_detalle_pDto>() {
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
