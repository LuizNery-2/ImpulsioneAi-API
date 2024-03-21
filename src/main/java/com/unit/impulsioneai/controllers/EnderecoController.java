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

import com.unit.impulsioneai.dtos.EnderecoRecordDto;
import com.unit.impulsioneai.models.EnderecoModel;
import com.unit.impulsioneai.repositories.EnderecoRepository;

import jakarta.validation.Valid;

@RestController
public class EnderecoController {
    @Autowired
    EnderecoRepository enderecoRepository;

    @PostMapping("/endereco")
    public ResponseEntity<EnderecoModel> saveEndereco(@RequestBody @Valid EnderecoRecordDto EnderecoRecordDto)
    {
        var EnderecoModel = new EnderecoModel();
        BeanUtils.copyProperties(EnderecoRecordDto,EnderecoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoRepository.save(EnderecoModel));
    }

    @GetMapping("/endereco")
    public ResponseEntity<List<EnderecoModel>> getAllEndereco(){
        return ResponseEntity.status(HttpStatus.OK).body(enderecoRepository.findAll());
    }

    @GetMapping("/endereco/{id}")
    public ResponseEntity<Object> getEndereco(@PathVariable(value = "id")UUID id){
        Optional<EnderecoModel> enderecoO = enderecoRepository.findById(id);
        if (enderecoO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(enderecoO.get());
    }

    @PutMapping("endereco/{id}")
    public ResponseEntity<Object> updateEndereco (@PathVariable(value = "id")UUID id,@RequestBody @Valid EnderecoRecordDto EnderecoRecordDto){
        Optional<EnderecoModel> enderecoO = enderecoRepository.findById(id);
        if (enderecoO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado");
        }
        var EnderecoModel = enderecoO.get();
        BeanUtils.copyProperties(EnderecoRecordDto,EnderecoModel);
        return ResponseEntity.status(HttpStatus.OK).body(enderecoRepository.save(EnderecoModel));

    }

    @DeleteMapping("/endereco/{id}")
    public ResponseEntity<Object> deleteEndereco(@PathVariable(value = "id")UUID id){
        Optional<EnderecoModel> enderecoO = enderecoRepository.findById(id);
        if (enderecoO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado");
        }
        var EnderecoModel = enderecoO.get();
       enderecoRepository.delete(EnderecoModel);
        return ResponseEntity.status(HttpStatus.OK).body("Endereco deletado com sucesso");
    }
}
