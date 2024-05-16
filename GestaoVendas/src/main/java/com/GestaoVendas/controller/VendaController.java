package com.GestaoVendas.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GestaoVendas.entities.Venda;
import com.GestaoVendas.service.VendaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name= "venda", description = "API REST DE GERENCIAMENTO DE VENDAS")
@RestController
@RequestMapping("/venda")
public class VendaController {
	private final VendaService vendaService;

	@Autowired
	public VendaController (VendaService vendaService) {
		this.vendaService = vendaService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza venda por ID")
	public ResponseEntity<Venda> getVendaById(@PathVariable Long id) {
		Venda venda = vendaService.getVendaById(id);
		if (venda != null) {
			return ResponseEntity.ok(venda);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Apresenta todas as vendas")
	public ResponseEntity<List<Venda>> Venda(){
		List<Venda> venda = vendaService.getAllVenda();
		return ResponseEntity.ok(venda);
	}
	//query
	@GetMapping("/data/{data}")
	@Operation(summary = "Apresenta todas as datas")
	public ResponseEntity<List<Venda>> getVendasPorData(@PathVariable Date data){
		List<Venda> venda = vendaService.getVendasPorData(data);
		return ResponseEntity.ok(venda);
	}

	@PostMapping("/")
	@Operation(summary = "Cadastra uma venda")
	public ResponseEntity<Venda> criarVenda(@RequestBody Venda venda) {
		Venda criarVenda = vendaService.salvarVenda(venda);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarVenda);
	}


	@PutMapping("/{id}")
	@Operation(summary= "Altera a venda")
	public ResponseEntity<Venda> updateVenda(@PathVariable Long id,@RequestBody Venda venda) {
		Venda updatedVenda = vendaService.updateVenda(id, venda);
		if (updatedVenda != null) {
			return ResponseEntity.ok(updatedVenda);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation (summary = "Deleta a venda")
	public ResponseEntity<String> deleteVenda(@PathVariable Long id) {
		boolean deleted = vendaService.deleteVenda(id);
		if (deleted) {
			return ResponseEntity.ok().body("A venda foi excluído com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}


}

