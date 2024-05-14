package com.unit.impulsioneai.controllers;

import com.unit.impulsioneai.models.EmpreendedorModel;
import com.unit.impulsioneai.models.NichoModel;
import com.unit.impulsioneai.repositories.EmpreendedoresRepository;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.unit.impulsioneai.dtos.CartaoRecordDto;
import com.unit.impulsioneai.models.CartaoModel;
import com.unit.impulsioneai.repositories.CartaoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin (origins = "*")
public class CartaoController {
    @Autowired
    CartaoRepository cartaoRepository;
    @Autowired
    EmpreendedoresRepository empreendedoresRepository;

    
    @PostMapping("/cartao")
    public ResponseEntity<CartaoModel> saveCartao(@RequestBody @Valid CartaoRecordDto cartaoRecordDto) {
        var cartaoModel = new CartaoModel();
        Optional<EmpreendedorModel> empreendedorO = empreendedoresRepository.findById(cartaoRecordDto.idEmpreendedor());
        if (empreendedorO.isEmpty()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empreendedor não encontrado");
        }
        var empreendedorModel = empreendedorO.get();
        BeanUtils.copyProperties(cartaoRecordDto, cartaoModel);
        ;

        // Criptografando o número do cartão
        String encryptedNumeroCartao = new BCryptPasswordEncoder().encode(cartaoRecordDto.getNumeroCartao());
        cartaoModel.setNumeroCartao(encryptedNumeroCartao);
        
        // Criptografando o CVV do cartão
        String encryptedCvv = new BCryptPasswordEncoder().encode(cartaoRecordDto.getCvv());
        cartaoModel.setCvv(encryptedCvv);
        cartaoRepository.save(cartaoModel);
        cartaoModel.setEmpreendedor(empreendedorModel);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(cartaoRepository.save(cartaoModel));
    }

    @GetMapping("/cartao/{id}")
    public ResponseEntity<Object> empreendedoresCartao(@PathVariable(value = "id") UUID id) {
        Optional<EmpreendedorModel> empreendedorO = empreendedoresRepository.findById(id);
        if (empreendedorO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empreendedor não encontrado");
        }
        
        CartaoModel cartao = empreendedorO.get().getCartao();
        if (cartao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado para este empreendedor");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(cartao);
    }



    

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @DeleteMapping("/deleteCartao/{id}")
    public ResponseEntity<Object> deleteCartao(@PathVariable(value = "id") int id) {
        System.out.println("Received request to delete Cartao with id: " + id);

        Optional<CartaoModel> cartaoO = cartaoRepository.findById(id);
        if (cartaoO.isEmpty()) {
            System.out.println("Cartao not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado");
        }

        
        int deletedCount = entityManager.createNativeQuery("DELETE FROM tb_cartoes WHERE id_cartao = :id")
                .setParameter("id", id)
                .executeUpdate();

        if (deletedCount > 0) {
            System.out.println("Cartao deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body("Cartão deletado com sucesso");
        } else {
            System.out.println("Failed to delete Cartao with id: " + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao deletar o Cartão");
        }
    }

}
