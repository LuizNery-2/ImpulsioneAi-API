package com.unit.impulsioneai.controllers;


import com.unit.impulsioneai.Services.TokenService;
import com.unit.impulsioneai.dtos.LoginRecordDTO;
import com.unit.impulsioneai.dtos.AuthenticatedResponseRecordDto;
import com.unit.impulsioneai.models.AdminModel;
import com.unit.impulsioneai.models.EmpreendedorModel;
import com.unit.impulsioneai.models.UsuarioModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    public ResponseEntity<AuthenticatedResponseRecordDto> login(@RequestBody @Valid LoginRecordDTO loginRecordDTO)
    {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRecordDTO.email(), loginRecordDTO.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var principal = auth.getPrincipal();
        if (principal instanceof AdminModel adminModel){
            var token = tokenService.genereteToken((AdminModel) auth.getPrincipal());
            return ResponseEntity.status(HttpStatus.OK).body(new AuthenticatedResponseRecordDto(token,adminModel.getIdAdmin(),"admin"));
        }
        else if (principal instanceof EmpreendedorModel empreendedorModel)
        {
            var token = tokenService.genereteToken((EmpreendedorModel) auth.getPrincipal());
            return ResponseEntity.status(HttpStatus.OK).body(new AuthenticatedResponseRecordDto(token,empreendedorModel.getIdEmpreededor(),"empreendedores"));
        }
            var token = tokenService.genereteToken((UsuarioModel) auth.getPrincipal());
            UsuarioModel usuarioModel =(UsuarioModel) principal;
        return ResponseEntity.status(HttpStatus.OK).body(new AuthenticatedResponseRecordDto(token,usuarioModel.getIdUsuario(),"usuarios"));
    }
}
