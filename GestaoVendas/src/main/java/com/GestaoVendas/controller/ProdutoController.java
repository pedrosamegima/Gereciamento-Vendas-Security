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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GestaoVendas.entities.Produto;
import com.GestaoVendas.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/produto")
@Tag(name= "produto", description = "API REST DE GERENCIAMENTO DE PRODUTOS")
public class ProdutoController {
	private final ProdutoService produtoService;
	@Autowired
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza produto por ID")
	public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
		Produto produto = produtoService.getProdutoById(id);
		if (produto != null) {
			return ResponseEntity.ok(produto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Aprensenta todos os produtos")
	public ResponseEntity<List<Produto>> getAllProduto() {
		List<Produto> produto = produtoService.getAllProduto();
		return ResponseEntity.ok(produto);
	}
	
	@GetMapping("/nome/{nome}")
	@Operation(summary = "Aprensenta o nome dos produtos")
	public ResponseEntity<List<Produto>> getProdutosPorNome(@PathVariable String nome){
	List<Produto> produtos = produtoService.getProdutosPorNome(nome);
	return ResponseEntity.ok(produtos);
	}
	
	//@Query
	@GetMapping("/preco/{preco}")
	@Operation(summary = "Aprensenta o preco dos produtos")
	public List<Produto> findProdutosPorPreco(@PathVariable double preco){
	return produtoService.findByPreco(preco);
	}

	@PostMapping("/")
	@Operation(summary = "Cadastra um produto")
	public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
		Produto criarProduto = produtoService.salvarProduto(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarProduto);
	}


	@PutMapping("/{id}")
	@Operation(summary = "Altera o produto")
	public ResponseEntity<Produto> updateProduto(@PathVariable Long id,@RequestBody Produto produto) {
		Produto updatedProduto = produtoService.updateProduto(id, produto);
		if (updatedProduto != null) {
			return ResponseEntity.ok(updatedProduto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um produto")
	public ResponseEntity<String> deleteProduto(@PathVariable Long id) {
		boolean deleted = produtoService.deleteProduto(id);
		if (deleted) {
			return ResponseEntity.ok().body("O produto foi excluído com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}


}


