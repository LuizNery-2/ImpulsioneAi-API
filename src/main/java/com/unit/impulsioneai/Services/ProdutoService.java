package com.unit.impulsioneai.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unit.impulsioneai.models.ProdutoModel;
import com.unit.impulsioneai.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoModel> filtrarPorFaixaDePreco(double precoMin, double precoMax) {
        return produtoRepository.findByPrecoBetween(precoMin, precoMax);
    }
}
