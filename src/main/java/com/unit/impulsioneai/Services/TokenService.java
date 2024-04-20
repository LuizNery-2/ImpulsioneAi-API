package com.unit.impulsioneai.Services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.unit.impulsioneai.models.AdminModel;
import com.unit.impulsioneai.models.EmpreendedorModel;
import com.unit.impulsioneai.models.UsuarioModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.impulsiona.secret}")
    private String secret;
    public String genereteToken(EmpreendedorModel empreendedorModel){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("ImpulsionaAPI")
                    .withSubject(empreendedorModel.getEmail())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
        }
        catch (JWTCreationException exception){
            throw new RuntimeException("Erro while generating tokken: " + exception);
        }
    }
    public String genereteToken(UsuarioModel usuarioModel){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("ImpulsionaAPI")
                    .withSubject(usuarioModel.getEmail())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
        }
        catch (JWTCreationException exception){
            throw new RuntimeException("Erro while generating tokken: " + exception);
        }
    }
    public String genereteToken(AdminModel adminModel){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("ImpulsionaAPI")
                    .withSubject(adminModel.getEmail())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
        }
        catch (JWTCreationException exception){
            throw new RuntimeException("Erro while generating tokken: " + exception);
        }
    }
    public String validateToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return JWT.require(algorithm)
                    .withIssuer("ImpulsionaAPI")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException exception){
            return "";
        }


    }

    private Instant getExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
