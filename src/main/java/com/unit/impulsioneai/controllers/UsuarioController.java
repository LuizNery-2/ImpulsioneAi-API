package com.unit.impulsioneai.controllers;


import com.unit.impulsioneai.Services.EmpreendedorService;
import com.unit.impulsioneai.dtos.UsuarioRecordDto;
import com.unit.impulsioneai.models.EmpreendedorModel;
import com.unit.impulsioneai.models.UsuarioModel;
import com.unit.impulsioneai.repositories.EmpreendedoresRepository;
import com.unit.impulsioneai.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    EmpreendedoresRepository empreendedoresRepository;

    @Autowired
    EmpreendedorService empreendedorService;

    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioModel> saveUsuario(@RequestBody @Valid UsuarioRecordDto usuarioRecordDto)
    {
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioRecordDto,usuarioModel);
        String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioRecordDto.senha());
        usuarioModel.setSenha(encryptedPassword);
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
        EmpreendedorModel empreendedor = empreendedoresRepository.findByEmail(email);

        if (usuario != null) {
            Map<String, String> response = new HashMap<>();
            response.put("id", usuario.getIdUsuario().toString());
            response.put("nome", usuario.getNome());
            return ResponseEntity.ok(response);
        } else if (empreendedor != null) {
            Map<String, String> response = new HashMap<>();
            response.put("id", empreendedor.getIdEmpreededor().toString());
            response.put("nome", empreendedor.getNomeCompleto());
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensagem", "Usuário ou empreendedor não encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }



    @PutMapping("editarSenha/{id}")
    public ResponseEntity<Object> updateSenhaUsuario(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid UsuarioRecordDto usuarioRecordDto) {
        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            UsuarioModel usuarioModel = usuarioOptional.get();
            // Altera a senha do usuário
            alterarSenhaUsuario(usuarioModel, usuarioRecordDto.getSenha());
            return ResponseEntity.status(HttpStatus.OK).body(usuarioModel);
        } else {
            // Se o ID não pertencer a um usuário, verifica no banco de dados de empreendedores
            Optional<EmpreendedorModel> empreendedorOptional = empreendedoresRepository.findById(id);
            if (empreendedorOptional.isPresent()) {
                EmpreendedorModel empreendedorModel = empreendedorOptional.get();
                // Altera a senha do empreendedor
                alterarSenhaEmpreendedor(empreendedorModel, usuarioRecordDto.getSenha());
                return ResponseEntity.status(HttpStatus.OK).body(empreendedorModel);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário ou empreendedor não encontrado");
            }
        }
    }


    private void alterarSenhaEmpreendedor(EmpreendedorModel empreendedorModel, String novaSenha) {
        if (novaSenha != null && !novaSenha.isEmpty()) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(novaSenha);
            empreendedorModel.setSenha(encryptedPassword);
            empreendedoresRepository.save(empreendedorModel);
        }
    }

    private void alterarSenhaUsuario(UsuarioModel usuarioModel, String novaSenha) {
        if (novaSenha != null && !novaSenha.isEmpty()) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(novaSenha);
            usuarioModel.setSenha(encryptedPassword);
            usuarioRepository.save(usuarioModel);
        }
    }


    @PostMapping("/usuario/{idUsuario}/{idEmpreendedor}")
    public ResponseEntity<Object> favoritarEmpreendedor(@PathVariable(value = "idUsuario") UUID idUsuario,@PathVariable(value = "idEmpreendedor") UUID idEmpreendedor){
        Optional<UsuarioModel> usuarioO = usuarioRepository.findById(idUsuario);
        Optional<EmpreendedorModel> empreendedorO = empreendedoresRepository.findById(idEmpreendedor);
        if (usuarioO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        if (empreendedorO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empreendedor não encontrado");
        }
        var usuarioModel = usuarioO.get();
        var empreendedorModel = empreendedorO.get();

        empreendedorService.favoritarEmpreendedor(usuarioModel,empreendedorModel);
        return ResponseEntity.status(HttpStatus.OK).body("Empreendedor adicionado aos favoritos");


    }
    @DeleteMapping("/usuario/{idUsuario}/{idEmpreendedor}")
    public ResponseEntity<Object> desfavoritarEmpreendedor(@PathVariable(value = "idUsuario") UUID idUsuario,@PathVariable(value = "idEmpreendedor") UUID idEmpreendedor){
        Optional<UsuarioModel> usuarioO = usuarioRepository.findById(idUsuario);
        Optional<EmpreendedorModel> empreendedorO = empreendedoresRepository.findById(idEmpreendedor);
        if (usuarioO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        if (empreendedorO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empreendedor não encontrado");
        }
        var usuarioModel = usuarioO.get();
        var empreendedorModel = empreendedorO.get();

        empreendedorService.desfavoritarEmpreendedor(usuarioModel,empreendedorModel);
        return ResponseEntity.status(HttpStatus.OK).body("Empreendedor removido dos favoritos");


    }


}
