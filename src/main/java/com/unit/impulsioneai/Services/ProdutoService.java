package com.unit.impulsioneai.Services;

import com.unit.impulsioneai.models.EmpreendedorModel;
import com.unit.impulsioneai.models.ProdutoModel;
import com.unit.impulsioneai.repositories.EmpreendedoresRepository;
import com.unit.impulsioneai.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    EmpreendedoresRepository empreendedoresRepository;
    @Autowired
    ProdutoRepository produtoRepository;

    public void cadastrarEmpreendedorProdutos (ProdutoModel produtoModel, EmpreendedorModel empreendedorModel){

        empreendedorModel.getProdutos().add(produtoModel);
        produtoModel.setEmpreendedor(empreendedorModel);
        produtoModel.setNicho(produtoModel.getEmpreendedor().getNicho());
        empreendedoresRepository.save(empreendedorModel);
        produtoRepository.save(produtoModel);

    }
}
