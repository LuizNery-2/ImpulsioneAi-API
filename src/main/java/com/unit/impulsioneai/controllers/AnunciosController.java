package com.unit.impulsioneai.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.unit.impulsioneai.dtos.AnunciosRecordDto;
import com.unit.impulsioneai.models.AnunciosModel;
import com.unit.impulsioneai.repositories.AnunciosRepository;

import jakarta.validation.Valid;

@RestController
public class AnunciosController {
    @Autowired
    AnunciosRepository anunciosRepository;

    @PostMapping("/anuncios")
    public ResponseEntity<AnunciosModel> saveAnuncio(@RequestBody @Valid AnunciosRecordDto anunciosRecordDto)
    {
        var anunciosModel = new AnunciosModel();
        BeanUtils.copyProperties(anunciosRecordDto,anunciosModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(anunciosRepository.save(anunciosModel));
    }

    @GetMapping("/anuncios")
    public ResponseEntity<List<AnunciosModel>> getAllAnuncios(){
        return ResponseEntity.status(HttpStatus.OK).body(anunciosRepository.findAll());
    }

    @GetMapping("/anuncios/{id}")
    public ResponseEntity<Object> getAnuncios(@PathVariable(value = "id")UUID id){
        Optional<AnunciosModel> anuncioO = anunciosRepository.findById(id);
        if (anuncioO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Anuncio não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(anuncioO.get());
    }

    @PutMapping("anuncios/{id}")
    public ResponseEntity<Object> updateAnuncio (@PathVariable(value = "id")UUID id,@RequestBody @Valid AnunciosRecordDto AnunciosRecordDto){
        Optional<AnunciosModel> anuncioO = anunciosRepository.findById(id);
        if (anuncioO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Anuncio não encontrado");
        }
        var AnunciosModel = anuncioO.get();
        BeanUtils.copyProperties(AnunciosRecordDto,AnunciosModel);
        return ResponseEntity.status(HttpStatus.OK).body(anunciosRepository.save(AnunciosModel));

    }

    @DeleteMapping("/anuncios/{id}")
    public ResponseEntity<Object> deleteAnuncio(@PathVariable(value = "id")UUID id){
        Optional<AnunciosModel> anuncioO = anunciosRepository.findById(id);
        if (anuncioO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Anuncio não encontrado");
        }
        var AnunciosModel = anuncioO.get();
        anunciosRepository.delete(AnunciosModel);
        return ResponseEntity.status(HttpStatus.OK).body("Anuncio deletado com sucesso");
    }
}
