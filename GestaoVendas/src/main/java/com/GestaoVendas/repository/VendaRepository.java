package com.GestaoVendas.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.GestaoVendas.entities.Venda;

public interface VendaRepository extends JpaRepository <Venda, Long> {

	//Query Method
	//List<Venda>findByNome(Date data);
	
	//JPQL
	@Query("SELECT a FROM Venda a WHERE a.data = :data")
	List<Venda> findByData (@Param("data")Date data);

}
