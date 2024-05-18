package com.unit.impulsioneai.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.unit.impulsioneai.dtos.PlanoAssinaturaRecordDto;
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

import com.unit.impulsioneai.models.PlanoAssinaturaModel;
import com.unit.impulsioneai.repositories.PlanoAssinaturaRepository;

import jakarta.validation.Valid;

@RestController
public class PlanoAssinaturaController {
    @Autowired
    PlanoAssinaturaRepository planoAssinaturaRepository;

    @PostMapping("/assinaturas")
    public ResponseEntity<PlanoAssinaturaModel> saveAssinatura(@RequestBody @Valid PlanoAssinaturaRecordDto planoAssinaturaRecordDto)
    {
        var planoAssinaturaModel = new PlanoAssinaturaModel();
        BeanUtils.copyProperties(planoAssinaturaRecordDto,planoAssinaturaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(planoAssinaturaRepository.save(planoAssinaturaModel));
    }

    @GetMapping("/assinaturas")
    public ResponseEntity<List<PlanoAssinaturaModel>> getAllAssinaturas(){
        return ResponseEntity.status(HttpStatus.OK).body(planoAssinaturaRepository.findAll());
    }

    @GetMapping("/assinaturas/{id}")
    public ResponseEntity<Object> getAssinaturas(@PathVariable(value = "id")UUID id){
        Optional<PlanoAssinaturaModel> assinaturaO = planoAssinaturaRepository.findById(id);
        if (assinaturaO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O empreendedor não possui assinaturas");
        }
        return ResponseEntity.status(HttpStatus.OK).body(assinaturaO.get());
    }

    @PutMapping("assinaturas/{id}")
    public ResponseEntity<Object> updateAssinatura (@PathVariable(value = "id")UUID id,@RequestBody @Valid PlanoAssinaturaRecordDto planoAssinaturaRecordDto){
        Optional<PlanoAssinaturaModel> assinaturaO = planoAssinaturaRepository.findById(id);
        if (assinaturaO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O empreendedor não possui assinaturas para serem atualizadas");
        }
        var planoAssinaturaModel = assinaturaO.get();
        BeanUtils.copyProperties(planoAssinaturaRecordDto,planoAssinaturaModel);
        return ResponseEntity.status(HttpStatus.OK).body(planoAssinaturaRepository.save(planoAssinaturaModel));

    }

    @DeleteMapping("/assinaturas/{id}")
    public ResponseEntity<Object> deleteAssinatura(@PathVariable(value = "id")UUID id){
        Optional<PlanoAssinaturaModel> assinaturaO = planoAssinaturaRepository.findById(id);
        if (assinaturaO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O empreendedor não possui assinaturas para serem deletadas");
        }
        var PlanoAssinaturaModel = assinaturaO.get();
       planoAssinaturaRepository.delete(PlanoAssinaturaModel);
        return ResponseEntity.status(HttpStatus.OK).body("Assinatura deletada com sucesso");
    }
}
