package com.GestaoVendas.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.GestaoVendas.entities.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long> {

	//Query Method
	List<Produto>findByNome(String nome);
	
	//JPQL
	@Query("SELECT a FROM Produto a WHERE a.preco = :preco")
	List<Produto> findByPreco (@Param("preco")double preco);

}
