package com.exemplo.fornecedorservice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.exemplo.fornecedorservice.model.Fornecedor;
import com.exemplo.fornecedorservice.repository.FornecedorRepository;

/**
 * Popula o banco H2 em memoria com fornecedores de teste assim que a aplicacao sobe.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final FornecedorRepository fornecedorRepository;

    public DataInitializer(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    @Override
    public void run(String... args) {
        fornecedorRepository.save(new Fornecedor("Tech Distribuidora", "12.345.678/0001-90"));
        fornecedorRepository.save(new Fornecedor("Global Suprimentos", "23.456.789/0001-01"));
        fornecedorRepository.save(new Fornecedor("Mega Componentes", "34.567.890/0001-12"));
        fornecedorRepository.save(new Fornecedor("Prime Importadora", "45.678.901/0001-23"));
        fornecedorRepository.save(new Fornecedor("Alfa Materiais", "56.789.012/0001-34"));
    }
}
