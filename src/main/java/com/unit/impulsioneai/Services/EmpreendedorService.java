package com.unit.impulsioneai.Services;


import com.unit.impulsioneai.dtos.EmpreendedoresRecordDto;
import com.unit.impulsioneai.models.EmpreendedorModel;
import com.unit.impulsioneai.models.NichoModel;
import com.unit.impulsioneai.repositories.EmpreendedoresRepository;
import com.unit.impulsioneai.repositories.NichoRepository;
import com.unit.impulsioneai.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.StyledEditorKit;
import java.util.Optional;

@Service
public class EmpreendedorService {

    @Autowired
    EmpreendedoresRepository empreendedoresRepository;
    @Autowired
    NichoRepository nichoRepository;
    @Autowired
    ProdutoRepository produtoRepository;

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
}
