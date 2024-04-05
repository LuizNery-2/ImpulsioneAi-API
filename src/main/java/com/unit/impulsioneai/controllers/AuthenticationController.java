package com.unit.impulsioneai.controllers;


import com.unit.impulsioneai.Services.TokenService;
import com.unit.impulsioneai.dtos.EmpreendedoresRecordDto;
import com.unit.impulsioneai.dtos.LoginRecordDTO;
import com.unit.impulsioneai.dtos.TokenRecordDTO;
import com.unit.impulsioneai.models.EmpreendedorModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity<TokenRecordDTO> login(@RequestBody @Valid LoginRecordDTO loginRecordDTO)
    {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRecordDTO.email(), loginRecordDTO.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.genereteToken((EmpreendedorModel) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new TokenRecordDTO(token));
    }
}
