package com.unit.impulsioneai.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.unit.impulsioneai.dtos.EmpreendedoresRecordDto;
import com.unit.impulsioneai.models.EmpreendedorModel;
import com.unit.impulsioneai.repositories.EmpreendedoresRepository;

import jakarta.validation.Valid;
@CrossOrigin (origins = "*")
@RestController
public class EmpreendedoresController {
    @Autowired
    EmpreendedoresRepository empreendedoresRepository;

    @PostMapping("/empreendedores")
    public ResponseEntity<EmpreendedorModel> saveEmpreendedor(@RequestBody @Valid EmpreendedoresRecordDto empreendedoresRecordDto)
    {
        var empreendedorModel = new EmpreendedorModel();
        BeanUtils.copyProperties(empreendedoresRecordDto,empreendedorModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(empreendedoresRepository.save(empreendedorModel));
    }

    @GetMapping("/empreendedores")
    public ResponseEntity<List<EmpreendedorModel>> getAllEmpreendedores(){
        return ResponseEntity.status(HttpStatus.OK).body(empreendedoresRepository.findAll());
    }

    @GetMapping("/empreendedores/{id}")
    public ResponseEntity<Object> getEmpreendedores(@PathVariable(value = "id")UUID id){
        Optional<EmpreendedorModel> empreendedorO = empreendedoresRepository.findById(id);
        if (empreendedorO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empreendedor não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(empreendedorO.get());
    }

    @PutMapping("empreendedores/{id}")
    public ResponseEntity<Object> updateEmpreendedor (@PathVariable(value = "id")UUID id,@RequestBody @Valid EmpreendedoresRecordDto empreendedoresRecordDto){
        Optional<EmpreendedorModel> empreendedorO = empreendedoresRepository.findById(id);
        if (empreendedorO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empreendedor não encontrado");
        }
        var empreendedorModel = empreendedorO.get();
        BeanUtils.copyProperties(empreendedoresRecordDto,empreendedorModel);
        return ResponseEntity.status(HttpStatus.OK).body(empreendedoresRepository.save(empreendedorModel));

    }

    @DeleteMapping("/empreendedores/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "id")UUID id){
        Optional<EmpreendedorModel> empreendedorO = empreendedoresRepository.findById(id);
        if (empreendedorO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empreendedor não encontrado");
        }
        var empreendedorModel = empreendedorO.get();
       empreendedoresRepository.delete(empreendedorModel);
        return ResponseEntity.status(HttpStatus.OK).body("Empreendedor deletado com sucesso");
    }
}
