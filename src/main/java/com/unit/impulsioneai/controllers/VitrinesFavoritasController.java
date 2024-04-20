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

import com.unit.impulsioneai.dtos.VitrinesFavoritasRecordDto;
import com.unit.impulsioneai.models.VitrinesFavoritasModel;
import com.unit.impulsioneai.repositories.VitrinesFavoritasRepository;

import jakarta.validation.Valid;

@RestController
public class VitrinesFavoritasController {
    @Autowired
    VitrinesFavoritasRepository vitrinesFavoritasRepository;

    @PostMapping("/vitrinesFavoritas")
    public ResponseEntity<VitrinesFavoritasModel> saveVitrines (@RequestBody @Valid VitrinesFavoritasRecordDto VitrinesFavoritasRecordDto){
        var VitrinesFavoritasModel = new VitrinesFavoritasModel();
        BeanUtils.copyProperties(VitrinesFavoritasRecordDto,VitrinesFavoritasModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(vitrinesFavoritasRepository.save(VitrinesFavoritasModel));
    }

    @GetMapping("/vitrinesFavoritas")
    public ResponseEntity<List<VitrinesFavoritasModel>> getAllVitrines(){
        return ResponseEntity.status(HttpStatus.OK).body(vitrinesFavoritasRepository.findAll());
    }
}
