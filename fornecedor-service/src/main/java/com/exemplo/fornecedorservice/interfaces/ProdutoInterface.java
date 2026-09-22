package com.exemplo.fornecedorservice.interfaces;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.exemplo.fornecedorservice.dto.ProdutoDTO;

@FeignClient(name = "produtos-service")
public interface ProdutoInterface {

    @GetMapping("/produtos")
    List<ProdutoDTO> listarTodos();
}
