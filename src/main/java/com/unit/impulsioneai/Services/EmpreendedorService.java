package com.unit.impulsioneai.Services;


import com.unit.impulsioneai.models.EmpreendedorModel;
import com.unit.impulsioneai.models.NichoModel;
import com.unit.impulsioneai.models.UsuarioModel;
import com.unit.impulsioneai.repositories.EmpreendedoresRepository;
import com.unit.impulsioneai.repositories.NichoRepository;
import com.unit.impulsioneai.repositories.ProdutoRepository;
import com.unit.impulsioneai.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EmpreendedorService {

    @Autowired
    EmpreendedoresRepository empreendedoresRepository;
    @Autowired
    NichoRepository nichoRepository;
    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public EmpreendedorModel associateEmpreendedorNicho(EmpreendedorModel empreendedor, int idNicho){

        Optional<NichoModel> nichoO =  nichoRepository.findById(idNicho);

        if (nichoO.isEmpty()){
            return empreendedor;
        }
        var nicho = nichoO.get();
        nicho.getEmpreendimentos().add(empreendedor);
        empreendedor.setNicho(nicho);
        return empreendedor;

    }

    public void favoritarEmpreendedor(UsuarioModel usuario, EmpreendedorModel empreendedor) {
        if (!usuario.getEmpreendedoresFavoritos().contains(empreendedor)) {
            usuario.getEmpreendedoresFavoritos().add(empreendedor);
            empreendedor.setNumeroFavoritos(empreendedor.getNumeroFavoritos() + 1);
            usuarioRepository.save(usuario); // Salva o usuário atualizado
            empreendedoresRepository.save(empreendedor); // Salva o empreendedor atualizado
        }
    }
    public void desfavoritarEmpreendedor(UsuarioModel usuario, EmpreendedorModel empreendedor) {
        if (usuario.getEmpreendedoresFavoritos().contains(empreendedor)) {
            usuario.getEmpreendedoresFavoritos().remove(empreendedor);
            empreendedor.setNumeroFavoritos(empreendedor.getNumeroFavoritos() - 1);
            usuarioRepository.save(usuario); // Salva o usuário atualizado
            empreendedoresRepository.save(empreendedor); // Salva o empreendedor atualizado
        }
    }


    public EmpreendedorModel updatePlanoAssinatura(UUID id, String planoAssinatura) {
        Optional<EmpreendedorModel> optionalEmpreendedor = empreendedoresRepository.findById(id);
        if (optionalEmpreendedor.isPresent()) {
            EmpreendedorModel empreendedor = optionalEmpreendedor.get();
            empreendedor.setPlanoAssinatura(planoAssinatura);
            return empreendedoresRepository.save(empreendedor);
        } else {
            throw new EntityNotFoundException("Empreendedor not found with id " + id);
        }
    }



}
