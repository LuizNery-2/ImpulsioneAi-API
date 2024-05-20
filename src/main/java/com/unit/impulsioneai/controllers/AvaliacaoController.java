package com.unit.impulsioneai.controllers;

import java.util.List;
import java.util.Optional;

import com.unit.impulsioneai.models.EmpreendedorModel;
import com.unit.impulsioneai.models.UsuarioModel;
import com.unit.impulsioneai.repositories.EmpreendedoresRepository;
import com.unit.impulsioneai.repositories.UsuarioRepository;
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
    @Autowired
    EmpreendedoresRepository empreendedoresRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/avaliacao")
    public ResponseEntity<AvaliacaoModel> saveDepoimento (@RequestBody @Valid AvaliacaoRecordDto avaliacaoRecordDto){
        var avaliacaoModel = new AvaliacaoModel();
        BeanUtils.copyProperties(avaliacaoRecordDto,avaliacaoModel);

        Optional<EmpreendedorModel> empreendedorO = empreendedoresRepository.findById(avaliacaoRecordDto.idEmpreendedor());
        Optional<UsuarioModel> usuarioO = usuarioRepository.findById(avaliacaoRecordDto.usuario());

        if (empreendedorO.isEmpty()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empreendedor não encontrado");
        }
        if (usuarioO.isEmpty())
        {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        avaliacaoRepository.save(avaliacaoModel);
        var empreendedorModel = empreendedorO.get();
        var usuarioModel = usuarioO.get();

        avaliacaoModel.setEmpreendedor(empreendedorModel);
        avaliacaoModel.setUsuario(usuarioModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoRepository.save(avaliacaoModel));
    }

    @GetMapping("/avaliacao")
    public ResponseEntity<List<AvaliacaoModel>> getAllDepoimentos(){
        return ResponseEntity.status(HttpStatus.OK).body(avaliacaoRepository.findAll());
    }
}
