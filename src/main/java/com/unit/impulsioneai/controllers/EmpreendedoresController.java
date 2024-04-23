package com.unit.impulsioneai.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.unit.impulsioneai.Services.EmpreendedorService;
import com.unit.impulsioneai.models.EnderecoModel;
import com.unit.impulsioneai.repositories.EnderecoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    EmpreendedorService empreendedorService;


    @PostMapping("/empreendedores")
    public ResponseEntity<EmpreendedorModel> saveEmpreendedor(@RequestBody @Valid EmpreendedoresRecordDto empreendedor)
    {

        Logger logger = LoggerFactory.getLogger(getClass());
        try {
            logger.info("Recebida solicitação para salvar empreendedor: {}", empreendedor);

            var empreendedorModel = new EmpreendedorModel();
            var enderecoModel = new EnderecoModel();
            BeanUtils.copyProperties(empreendedor,empreendedorModel);
            BeanUtils.copyProperties(empreendedor.endereco(), enderecoModel);
            empreendedorModel = empreendedorService.associateEmpreendedorNicho(empreendedorModel, empreendedor.idNicho());
            String encryptedPassword = new BCryptPasswordEncoder().encode(empreendedor.senha());

            enderecoModel.setEmpreendedor(empreendedorModel);
            empreendedorModel.setEndereco(enderecoModel);
            empreendedorModel.setSenha(encryptedPassword);
            empreendedoresRepository.save(empreendedorModel);
            enderecoRepository.save(enderecoModel);


            logger.info("Empreendedor salvo com sucesso: {}", empreendedorModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(empreendedoresRepository.save(empreendedorModel));
        } catch (Exception e) {
            logger.error("Erro ao salvar empreendedor: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


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
