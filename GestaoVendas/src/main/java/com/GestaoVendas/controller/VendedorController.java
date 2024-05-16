package com.GestaoVendas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GestaoVendas.entities.Vendedor;
import com.GestaoVendas.service.VendedorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/vendedor")
@Tag(name= "venda", description = "API REST DE GERENCIAMENTO DE VENDEDORES")
public class VendedorController {
	private final VendedorService vendedorService;

	@Autowired
	public VendedorController(VendedorService vendedorService) {
		this.vendedorService = vendedorService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza vendedor por ID")
	public ResponseEntity<Vendedor> getVendedorById(@PathVariable Long id) {
		Vendedor vendedor = vendedorService.getVendedorById(id);
		if (vendedor != null) {
			return ResponseEntity.ok(vendedor);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Apresenta todas os vendedores")
	public ResponseEntity<List<Vendedor>> Vendedor() {
		List<Vendedor> vendedor = vendedorService.getAllVendedor();
		return ResponseEntity.ok(vendedor);
	}
	//@Query
	@GetMapping("/nome/{nome}")
	@Operation(summary = "Apresenta todos os nomes dos vendedores")
	public ResponseEntity<List<Vendedor>> getVendedorPorNome(@PathVariable String nome){
		List<Vendedor> vendedor = vendedorService.getVendedorPorNome(nome);
		return ResponseEntity.ok(vendedor);
	}
	//@Query
	@GetMapping("/meta/{meta}")
	@Operation(summary = "Apresenta todas metas dos vendedores")
	public ResponseEntity<List<Vendedor>> getVendedorPorMeta(@PathVariable double meta){
		List<Vendedor> vendedor = vendedorService.getVendedorPorMeta(meta);
		return ResponseEntity.ok(vendedor);
	}

	@PostMapping("/")
	@Operation(summary = "Cadastra um vendedor")
	public ResponseEntity<Vendedor> criarVendedor(@RequestBody Vendedor vendedor) {
		Vendedor criarVendedor = vendedorService.salvarVendedor(vendedor);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarVendedor);
	}


	@PutMapping("/{id}")
	@Operation(summary = "Altera o vendedor")
	public ResponseEntity<Vendedor> updateVendedor(@PathVariable Long id,@RequestBody Vendedor vendedor) {
		Vendedor updatedVendedor = vendedorService.updateVendedor(id, vendedor);
		if (updatedVendedor != null) {
			return ResponseEntity.ok(updatedVendedor);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um vendedor")
	public ResponseEntity<String> deleteVendedor(@PathVariable Long id) {
		boolean deleted = vendedorService.deleteVendedor(id);
		if (deleted) {
			return ResponseEntity.ok().body("O vendedor foi excluído com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}


}


