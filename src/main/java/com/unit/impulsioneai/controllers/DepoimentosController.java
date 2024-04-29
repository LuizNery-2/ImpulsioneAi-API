package com.unit.impulsioneai.controllers;

import java.util.List;
import java.util.Optional;
import com.unit.impulsioneai.models.EmpreendedorModel;
import com.unit.impulsioneai.repositories.EmpreendedoresRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.unit.impulsioneai.dtos.DepoimentosRecordDto;
import com.unit.impulsioneai.models.DepoimentosModel;
import com.unit.impulsioneai.repositories.DepoimentosRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class DepoimentosController {

    @Autowired
    DepoimentosRepository depoimentosRepository;

    @Autowired
    EmpreendedoresRepository empreendedoresRepository;

    @PostMapping("/depoimento")
    public ResponseEntity<Object> saveDepoimento (@RequestBody @Valid DepoimentosRecordDto depoimentosRecordDto){
        var depoimentoModel = new DepoimentosModel();
        Optional<EmpreendedorModel> empreendedorO = empreendedoresRepository.findById(depoimentosRecordDto.idEmpreendedor());
        if (empreendedorO.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empreendedor não encontrado");
        }
        var empreendedorModel = empreendedorO.get();
        BeanUtils.copyProperties(depoimentosRecordDto,depoimentoModel);
        depoimentoModel.setEmpreendedor(empreendedorModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(depoimentosRepository.save(depoimentoModel));
    }

    @GetMapping("/depoimento")
    public ResponseEntity<List<DepoimentosModel>> getAllDepoimentos(){
        return ResponseEntity.status(HttpStatus.OK).body(depoimentosRepository.findAll());
    }

    @GetMapping("/depoimento/{id}")
    public ResponseEntity<Object> getDepoimentos(@PathVariable(value = "id") String id){
        Optional<DepoimentosModel> depoimentoO = depoimentosRepository.findById(id);
        if (depoimentoO.isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Depoimento não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(depoimentoO.get());
    }

    @PutMapping("/depoimento/{id}")
    public ResponseEntity<Object> updateDepoimento(@PathVariable(value = "id") String id, @RequestBody @Valid DepoimentosRecordDto  depoimentosRecordDto)
    {
        Optional<DepoimentosModel> depoimentoO = depoimentosRepository.findById(id);
        if (depoimentoO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Depoimento não encontrado");
        }
        var DepoimentosModel = depoimentoO.get();
        BeanUtils.copyProperties(depoimentosRecordDto,DepoimentosModel);
        return ResponseEntity.status(HttpStatus.OK).body(depoimentosRepository.save(DepoimentosModel));

    }

    @DeleteMapping("/depoimento/{id}")
    public ResponseEntity<Object> deleteDepoimento(@PathVariable(value = "id") String id){
        Optional<DepoimentosModel> depoimentoO = depoimentosRepository.findById(id);

        if (depoimentoO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Depoimento não encontrado");
        }
        var DepoimentosModel = depoimentoO.get();
        Optional<EmpreendedorModel> empreendedorO = empreendedoresRepository.findById(DepoimentosModel.getEmpreendedor().getIdEmpreededor());
        if (empreendedorO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empreendedor não encontrado");
        }
        var empreendedor = empreendedorO.get();
        empreendedor.setDepoimento(null);
        empreendedoresRepository.save(empreendedor);
        depoimentosRepository.delete(DepoimentosModel);
        return ResponseEntity.status(HttpStatus.OK).body("Depoimento deletado com sucesso");


    }
    

}
