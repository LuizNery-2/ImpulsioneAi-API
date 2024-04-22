package com.unit.impulsioneai.repositories;

import com.unit.impulsioneai.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, UUID> {
   List<ProdutoModel>findByNomeContainingIgnoreCase(String nome);

   List<ProdutoModel> findByPrecoBetween(double precoMin, double precoMax);


}
