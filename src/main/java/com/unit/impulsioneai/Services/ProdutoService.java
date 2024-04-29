package com.unit.impulsioneai.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unit.impulsioneai.models.ProdutoModel;
import com.unit.impulsioneai.repositories.ProdutoRepository;

import com.unit.impulsioneai.models.EmpreendedorModel;
import com.unit.impulsioneai.repositories.EmpreendedoresRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    EmpreendedoresRepository empreendedoresRepository;

    public List<ProdutoModel> filtrarPorFaixaDePreco(double precoMin, double precoMax) {
        return produtoRepository.findByPrecoBetween(precoMin, precoMax);
    }

    public void cadastrarEmpreendedorProdutos (ProdutoModel produtoModel, EmpreendedorModel empreendedorModel){

        empreendedorModel.getProdutos().add(produtoModel);
        produtoModel.setEmpreendedor(empreendedorModel);
        produtoModel.setNicho(produtoModel.getEmpreendedor().getNicho());
        empreendedoresRepository.save(empreendedorModel);
        produtoRepository.save(produtoModel);

    }
}
