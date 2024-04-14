package com.unit.impulsioneai.repositories;

import com.unit.impulsioneai.models.EmpreendedorModel;
import com.unit.impulsioneai.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, UUID> {
   List<ProdutoModel>findByNomeContainingIgnoreCase(String nome);

   @Query("SELECT p FROM ProdutoModel p WHERE p.nome LIKE %:pesquisa%")
   List<ProdutoModel> findBySearch(@Param("pesquisa") String pesquisa);

}
