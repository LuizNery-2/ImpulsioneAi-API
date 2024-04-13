package com.unit.impulsioneai.controllers;


import com.unit.impulsioneai.dtos.UsuarioRecordDto;
import com.unit.impulsioneai.models.UsuarioModel;
import com.unit.impulsioneai.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@RestController
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioModel> saveUsuario(@RequestBody @Valid UsuarioRecordDto usuarioRecordDto)
    {
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioRecordDto,usuarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuarioModel));
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioModel>> getAllUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Object> getUsuario(@PathVariable(value = "id")UUID id){
        Optional<UsuarioModel> usuarioO = usuarioRepository.findById(id);
        if (usuarioO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioO.get());
    }

    @PutMapping("usuarios/{id}")
    public ResponseEntity<Object> updateUsuario (@PathVariable(value = "id")UUID id,@RequestBody @Valid UsuarioRecordDto usuarioRecordDto){
        Optional<UsuarioModel> usuarioO = usuarioRepository.findById(id);
        if (usuarioO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        var usuarioModel = usuarioO.get();
        BeanUtils.copyProperties(usuarioRecordDto,usuarioModel);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuarioModel));

    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "id")UUID id){
        Optional<UsuarioModel> usuarioO = usuarioRepository.findById(id);
        if (usuarioO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        var usuarioModel = usuarioO.get();
       usuarioRepository.delete(usuarioModel);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso");
    }


    @GetMapping("/verificaUsuarios")
    public ResponseEntity<Object> verificarUsuarioPorEmail(@RequestParam String email) {
        UsuarioModel usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            Map<String, String> response = new HashMap<>();
            response.put("id", usuario.getIdUsuario().toString());
            response.put("nome", usuario.getNome());
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensagem", "Usuário não encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PutMapping("editarSenha/{id}")
    public ResponseEntity<Object> updateSenhaUsuario(@PathVariable(value = "id") UUID id,
                                                 @RequestBody @Valid UsuarioRecordDto usuarioRecordDto) {
        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        UsuarioModel usuarioModel = usuarioOptional.get();
        
        
        if (usuarioRecordDto.getSenha() != null && !usuarioRecordDto.getSenha().isEmpty()) {
            
            usuarioModel.setSenha(usuarioRecordDto.getSenha());
        }

       
        usuarioModel = usuarioRepository.save(usuarioModel);

        return ResponseEntity.status(HttpStatus.OK).body(usuarioModel);
    }


}
