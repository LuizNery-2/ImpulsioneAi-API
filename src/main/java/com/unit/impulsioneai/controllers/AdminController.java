package com.unit.impulsioneai.controllers;

import com.unit.impulsioneai.dtos.AdminRecordDto;
import com.unit.impulsioneai.models.AdminModel;
import com.unit.impulsioneai.models.EmpreendedorModel;
import com.unit.impulsioneai.repositories.AdminRepository;
import com.unit.impulsioneai.repositories.EmpreendedoresRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AdminController {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    EmpreendedoresRepository empreendedoresRepository;

    @PostMapping("/admin")
    public ResponseEntity<AdminModel> saveAdmin(@RequestBody AdminRecordDto adminRecordDto){
        AdminModel adminModel =  new AdminModel();
        BeanUtils.copyProperties(adminRecordDto, adminModel);
        String encryptedPassword = new BCryptPasswordEncoder().encode(adminRecordDto.password());
        adminModel.setNomeExibicao(adminRecordDto.nome().split(" ")[0]);
        adminModel.setPassword(encryptedPassword);

        return ResponseEntity.status(HttpStatus.CREATED).body(adminRepository.save(adminModel));

    }
    @GetMapping("/admin")
    public ResponseEntity<List<AdminModel>> getAllAdmins(){
        return ResponseEntity.status(HttpStatus.OK).body(adminRepository.findAll());
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<Object> getAdmin(@PathVariable(value = "id")UUID idAdmin){
        Optional<AdminModel> adminO = adminRepository.findById(idAdmin);
        if (adminO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }
        var adminModel = adminO.get();
        return ResponseEntity.status(HttpStatus.OK).body(adminModel);

    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<Object> updadeAdmin(@PathVariable(value = "id")UUID id, @RequestBody AdminRecordDto adminRecordDto){
        Optional<AdminModel> adminO = adminRepository.findById(id);
        if (adminO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }
        var adminModel = adminO.get();
        BeanUtils.copyProperties(adminRecordDto,adminModel);
        return ResponseEntity.status(HttpStatus.OK).body(adminRepository.save(adminModel));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Object> deleteAdmin(@PathVariable(value = "id")UUID id){
        Optional<AdminModel> adminO = adminRepository.findById(id);
        if (adminO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }
        var adminModel = adminO.get();
        adminRepository.delete(adminModel);
        return ResponseEntity.status(HttpStatus.OK).body("Admin deletado com sucesso");
    }

    @GetMapping("/admin/solicitacoes")
    public ResponseEntity<List<EmpreendedorModel>> getSolicitacaoEmpreendedores() {
        List<EmpreendedorModel> empreendedores = empreendedoresRepository.findAll();
        empreendedores.removeIf(EmpreendedorModel::isVerificado);
        return ResponseEntity.ok(empreendedores);
    }

    @PutMapping("/admin/solicitacoes/{id}")
    public ResponseEntity<Object> aceitarSolicitacoess(@PathVariable(value = "id") UUID id){
        Optional<EmpreendedorModel> empreendedorModelO = empreendedoresRepository.findById(id);
        if (empreendedorModelO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empreendedro nao encontrado");
        }
        var empreendedorModel = empreendedorModelO.get();
        empreendedorModel.setVerificado(true);

        return ResponseEntity.status(HttpStatus.OK).body(empreendedoresRepository.save(empreendedorModel));

    }

    @DeleteMapping("/admin/solicitacoes/{id}")
    public ResponseEntity<Object> deletarEmpreendedor(@PathVariable(value = "id") UUID id){
        Optional<EmpreendedorModel> empreendedorModelO = empreendedoresRepository.findById(id);
        if (empreendedorModelO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empreendedor nao encontrado");
        }
        var empreendedorModel = empreendedorModelO.get();
        empreendedoresRepository.delete(empreendedorModel);
        return ResponseEntity.status(HttpStatus.OK).body("Empreendedor deletado com sucesso");

    }


}
