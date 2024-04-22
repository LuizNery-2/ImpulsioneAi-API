package com.unit.impulsioneai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unit.impulsioneai.Services.EmpreendedoresService;
import com.unit.impulsioneai.Services.ProdutoService;
import com.unit.impulsioneai.models.EmpreendedorModel;
import com.unit.impulsioneai.models.ProdutoModel;


@RestController
public class FiltrosController {
    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private EmpreendedoresService empreendedorService;

    @GetMapping("/filtrarProdutos")
    public ResponseEntity<List<ProdutoModel>> filtrarProdutosPorFaixaDePreco(@RequestParam("precoMin") double precoMin,
                                                                         @RequestParam("precoMax") double precoMax) {
        List<ProdutoModel> produtosFiltrados = produtoService.filtrarPorFaixaDePreco(precoMin, precoMax);
        return ResponseEntity.ok(produtosFiltrados);
    }

    @GetMapping("/filtrarEmpreendedores")
    public ResponseEntity<List<EmpreendedorModel>> filtrarEmpreendedoresPorNicho(@RequestParam("nicho") String nicho) {
        List<EmpreendedorModel> empreendedoresFiltrados = empreendedorService.filtrarPorNicho(nicho);
        return ResponseEntity.ok(empreendedoresFiltrados);
    }
}
