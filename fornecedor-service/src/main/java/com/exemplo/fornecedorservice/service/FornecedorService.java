package com.exemplo.fornecedorservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exemplo.fornecedorservice.dto.ProdutoDTO;
import com.exemplo.fornecedorservice.interfaces.ProdutoInterface;
import com.exemplo.fornecedorservice.model.Fornecedor;
import com.exemplo.fornecedorservice.repository.FornecedorRepository;

/**
 * Regra de negocio de Fornecedor. O controller nao fala direto com o repository,
 * fala com este service.
 */
@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final ProdutoInterface produtoInterface;

    public FornecedorService(FornecedorRepository fornecedorRepository, ProdutoInterface produtoInterface) {
        this.fornecedorRepository = fornecedorRepository;
        this.produtoInterface = produtoInterface;
    }

    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> buscarPorId(Long id) {
        return fornecedorRepository.findById(id);
    }

    public Fornecedor salvar(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public List<ProdutoDTO> listarProdutos() {
        return produtoInterface.listarTodos();
    }
}
