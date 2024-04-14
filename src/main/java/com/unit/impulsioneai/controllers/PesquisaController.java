package com.unit.impulsioneai.controllers;


import com.unit.impulsioneai.dtos.PesquisaRecordDto;
import com.unit.impulsioneai.models.EmpreendedorModel;
import com.unit.impulsioneai.models.ProdutoModel;
import com.unit.impulsioneai.repositories.EmpreendedoresRepository;
import com.unit.impulsioneai.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class PesquisaController {

    @Autowired
    EmpreendedoresRepository empreendedoresRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/buscar/{pesquisa}")
    public ResponseEntity<Object> pesquisar(@PathVariable(value = "pesquisa") String pesquisa){

        List<EmpreendedorModel> empreendedoresPesquisados = empreendedoresRepository.findBySearch(pesquisa);
        List<ProdutoModel> produtosPesquisados = produtoRepository.findBySearch(pesquisa);
        if (empreendedoresPesquisados.isEmpty() && produtosPesquisados.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum empreendedor ou produto encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(new PesquisaRecordDto(produtosPesquisados, empreendedoresPesquisados));
    }


}
