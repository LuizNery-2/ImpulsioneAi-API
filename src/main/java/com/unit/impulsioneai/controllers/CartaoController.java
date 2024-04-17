package com.unit.impulsioneai.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.unit.impulsioneai.dtos.CartaoRecordDto;
import com.unit.impulsioneai.models.CartaoModel;
import com.unit.impulsioneai.repositories.CartaoRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin (origins = "*")
public class CartaoController {
    @Autowired
    CartaoRepository cartaoRepository;

    
    @PostMapping("/cartao")
    public ResponseEntity<CartaoModel> saveCartao(@RequestBody @Valid CartaoRecordDto CartaoRecordDto) {
        var CartaoModel = new CartaoModel();
        BeanUtils.copyProperties(CartaoRecordDto, CartaoModel);
        
        // Criptografando o número do cartão
        String encryptedNumeroCartao = new BCryptPasswordEncoder().encode(CartaoRecordDto.getNumeroCartao());
        CartaoModel.setNumeroCartao(encryptedNumeroCartao);
        
        // Criptografando o CVV do cartão
        String encryptedCvv = new BCryptPasswordEncoder().encode(CartaoRecordDto.getCvv());
        CartaoModel.setCvv(encryptedCvv);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(cartaoRepository.save(CartaoModel));
    }
}
