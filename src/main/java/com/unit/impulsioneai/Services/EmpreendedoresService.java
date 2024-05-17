package com.unit.impulsioneai.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unit.impulsioneai.models.EmpreendedorModel;
import com.unit.impulsioneai.models.NichoModel;
import com.unit.impulsioneai.repositories.EmpreendedoresRepository;

@Service
public class EmpreendedoresService {
    @Autowired
    private EmpreendedoresRepository empreendedorRepository;

    public List<EmpreendedorModel> filtrarPorNicho(NichoModel nicho) {
        return empreendedorRepository.findByNicho(nicho);
    }
}
