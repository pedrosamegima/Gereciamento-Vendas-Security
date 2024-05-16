package com.GestaoVendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GestaoVendas.entities.Produto;
import com.GestaoVendas.repository.ProdutoRepository;

@Service
public class ProdutoService {
	private final ProdutoRepository produtoRepository;
	
	@Autowired
	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	public List<Produto> getAllProduto() {
		return produtoRepository.findAll();
	}

	public Produto getProdutoById(Long id) {
		Optional<Produto> Produto = produtoRepository.findById(id);
		return Produto.orElse(null);
	}
	//Query Method
	public List<Produto> getProdutosPorNome(String nome){
		return produtoRepository.findByNome(nome);
	}
	//@Query
	public List<Produto> findByPreco(double preco){
		return produtoRepository.findByPreco(preco);
	}
	
	public Produto salvarProduto(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto updateProduto(Long id, Produto updatedProduto) {
		Optional<Produto> existingProduto = produtoRepository.findById(id);
		if (existingProduto.isPresent()) {
			updatedProduto.setId(id);
			return produtoRepository.save(updatedProduto);
		}
		return null;
	}

	public boolean deleteProduto(Long id) {
		Optional<Produto> existingProduto = produtoRepository.findById(id);
		if (existingProduto.isPresent()) {
			produtoRepository.deleteById(id);
			return true;
		}
		return false;
	}



}
	

