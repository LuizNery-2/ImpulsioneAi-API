package com.unit.impulsioneai.controllers;

import com.unit.impulsioneai.dtos.ProdutoRecordDto;
import com.unit.impulsioneai.models.ProdutoModel;
import com.unit.impulsioneai.repositories.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@RestController
public class ProdutoController {
    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping("/produtos")
    public ResponseEntity<ProdutoModel> saveProdutos(@RequestBody @Valid ProdutoRecordDto produtoRecordDto){
        var produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoRecordDto, produtoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtoModel));
    }
    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoModel>> getAllProdutos(){

        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAll());

    }
    @GetMapping("/produtos/{id}")
    public ResponseEntity<Object> getProduto(@PathVariable(value = "id")UUID id){
        Optional<ProdutoModel> produtoO = produtoRepository.findById(id);
        if (produtoO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoO.get());

    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<Object> updadeProduto (@PathVariable(value = "id") UUID id, @RequestBody @Valid ProdutoRecordDto produtoRecordDto){
        Optional<ProdutoModel> produtoO = produtoRepository.findById(id);
        if (produtoO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        var produtoModel = produtoO.get();
        BeanUtils.copyProperties(produtoRecordDto, produtoModel);
        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produtoModel));

    }
    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Object> deleteProduto(@PathVariable(value = "id") UUID id){
        Optional<ProdutoModel> produtoO = produtoRepository.findById(id);
        if (produtoO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        var produtoModel = produtoO.get();
        produtoRepository.delete(produtoModel);
        return ResponseEntity.status(HttpStatus.OK).body("Produto excluido com sucesso");
    }
}
