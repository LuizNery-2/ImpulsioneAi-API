package com.unit.impulsioneai.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.unit.impulsioneai.dtos.AvaliacaoRecordDto;
import com.unit.impulsioneai.models.AvaliacaoModel;
import com.unit.impulsioneai.repositories.AvaliacaoRepository;

import jakarta.validation.Valid;

@RestController
public class AvaliacaoController {
    @Autowired
    AvaliacaoRepository  avaliacaoRepository;

    @PostMapping("/avaliacao")
    public ResponseEntity<AvaliacaoModel> saveDepoimento (@RequestBody @Valid AvaliacaoRecordDto AvaliacaoRecordDto){
        var AvaliacaoModel = new AvaliacaoModel();
        BeanUtils.copyProperties(AvaliacaoRecordDto,AvaliacaoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoRepository.save(AvaliacaoModel));
    }

    @GetMapping("/avaliacao")
    public ResponseEntity<List<AvaliacaoModel>> getAllDepoimentos(){
        return ResponseEntity.status(HttpStatus.OK).body(avaliacaoRepository.findAll());
    }
}
