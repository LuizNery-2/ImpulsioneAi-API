package com.unit.impulsioneai.controllers;

import com.unit.impulsioneai.dtos.NichoRecordDto;
import com.unit.impulsioneai.models.NichoModel;
import com.unit.impulsioneai.repositories.NichoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class NichoController {


    @Autowired
    NichoRepository nichoRepository;

    @PostMapping("/nichos")
    public ResponseEntity<NichoModel> saveNicho (@RequestBody @Valid NichoRecordDto nichoRecordDto){
        var nichoModel = new NichoModel();
        BeanUtils.copyProperties(nichoRecordDto,nichoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(nichoRepository.save(nichoModel));
    }
    @GetMapping("/nichos")
    public ResponseEntity<List<NichoModel>> getAllNichos(){
        return ResponseEntity.status(HttpStatus.OK).body(nichoRepository.findAll());
    }

    @GetMapping("/nichos/{id}")
    public ResponseEntity<Object> getNicho(@PathVariable(value = "id") int id){
        Optional<NichoModel> nichoO = nichoRepository.findById(id);
        if (nichoO.isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nicho não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(nichoO.get());
    }

    @PutMapping("/nichos/{id}")
    public ResponseEntity<Object> updateNicho(@PathVariable(value = "id") int id, @RequestBody @Valid NichoRecordDto nichoRecordDto)
    {
        Optional<NichoModel> nichoO = nichoRepository.findById(id);
        if (nichoO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nicho não encontrado");
        }
        var nichoModel = nichoO.get();
        BeanUtils.copyProperties(nichoRecordDto,nichoModel);
        return ResponseEntity.status(HttpStatus.OK).body(nichoRepository.save(nichoModel));

    }
    @DeleteMapping("/nichos/{id}")
    public ResponseEntity<Object> deleteNicho(@PathVariable(value = "id") int id){
        Optional<NichoModel> nichoO = nichoRepository.findById(id);
        if (nichoO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nicho não encontrado");
        }
        var nichoModel = nichoO.get();
        nichoRepository.delete(nichoModel);
        return ResponseEntity.status(HttpStatus.OK).body("Nicho deletado com sucesso");


    }
}
