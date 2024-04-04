package com.unit.impulsioneai.controllers;

import com.unit.impulsioneai.dtos.AnuncioRecordDto;
import com.unit.impulsioneai.models.AnuncioModel;
import com.unit.impulsioneai.repositories.AnuncioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AunucioController {
    @Autowired
    AnuncioRepository anuncioRepository;

    @PostMapping("/anuncios")
    public ResponseEntity<AnuncioModel> saveAnuncio(@Valid @RequestBody AnuncioRecordDto anuncioRecordDto){

        var anuncioModel = new AnuncioModel();
        BeanUtils.copyProperties(anuncioRecordDto, anuncioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(anuncioRepository.save(anuncioModel));

    }

    @GetMapping("/anuncios")
    public ResponseEntity<List<AnuncioModel>> getAllAnuncios(){
        return ResponseEntity.status(HttpStatus.OK).body(anuncioRepository.findAll());

    }
    @GetMapping("/anuncios/{id}")
    public ResponseEntity<Object> getAnuncio(@PathVariable(value = "id")UUID idAnuncio){

        Optional<AnuncioModel> anuncioO = anuncioRepository.findById(idAnuncio);
        if (anuncioO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Anuncio não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(anuncioO.get());

    }
    @PutMapping("/anuncios/{id}")
    public ResponseEntity<Object> updateAnuncio(@PathVariable(value = "id") UUID idAnuncio, @Valid @RequestBody AnuncioRecordDto anuncioRecordDto){
        Optional<AnuncioModel> anuncioO = anuncioRepository.findById(idAnuncio);
        if (anuncioO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Anuncio não encontrado");
        }
        var anuncioModel = anuncioO.get();
        BeanUtils.copyProperties(anuncioRecordDto,anuncioModel);
        return ResponseEntity.status(HttpStatus.OK).body(anuncioRepository.save(anuncioModel));

    }

    @DeleteMapping("/anuncios/{id}")
    public ResponseEntity<String> deleteAnuncio(@PathVariable(value = "id") UUID idAnuncio){
        Optional<AnuncioModel> anuncioO = anuncioRepository.findById(idAnuncio);
        if (anuncioO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Anuncio não encontrado");
        }
        anuncioRepository.deleteById(idAnuncio);
        return ResponseEntity.status(HttpStatus.OK).body("Anuncio deletado com sucesso");

    }



}



