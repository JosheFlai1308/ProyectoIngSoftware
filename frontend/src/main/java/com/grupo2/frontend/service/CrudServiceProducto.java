package com.grupo2.frontend.service;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.grupo2.frontend.dto.ProductoDto;

@Component
@Service
public class CrudServiceProducto  implements ICrudServiceProducto {

    private String getBasicAuthHeader() {
        String credentials = "admin:password";
        return new String(Base64.getEncoder().encodeToString(credentials.getBytes()));
    }
    private  HttpHeaders httpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic " + getBasicAuthHeader());
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }
    @Override
    public List<ProductoDto> findAllREST(String key) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllREST'");
    }

    @Override
    public Optional<ProductoDto> findByIdREST(int id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByIdREST'");
    }

    @Override
    public ProductoDto editarREST(ProductoDto dto) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editarREST'");
    }

    @Override
    public ProductoDto saveREST(ProductoDto dto) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveREST'");
    }

    @Override
    public ProductoDto deleteREST(int id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteREST'");
    }
    
}
