package com.unit.impulsioneai.dtos;

import com.unit.impulsioneai.models.EmpreendedorModel;
import com.unit.impulsioneai.models.ProdutoModel;

import java.util.List;

public record PesquisaRecordDto(List<ProdutoModel> produtos, List<EmpreendedorModel> empreendedores) {
}
