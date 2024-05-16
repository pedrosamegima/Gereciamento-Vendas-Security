package com.GestaoVendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GestaoVendas.entities.Vendedor;
import com.GestaoVendas.repository.VendedorRepository;

@Service
public class VendedorService {
	private final VendedorRepository vendedorRepository;
	
	@Autowired
	public VendedorService(VendedorRepository vendedorRepository) {
		this.vendedorRepository = vendedorRepository;
	}

	public List<Vendedor> getAllVendedor() {
		return vendedorRepository.findAll();
	}

	public Vendedor getVendedorById(Long id) {
		Optional<Vendedor> Vendedor = vendedorRepository.findById(id);
		return Vendedor.orElse(null);
	}
	//Query Method
	public List<Vendedor> getVendedorPorNome(String nome){
		return vendedorRepository.findByNome(nome);
	}
	//Query Method
	public List<Vendedor> getVendedorPorMeta(double meta){
		return vendedorRepository.findByMeta(meta);
	}
	public Vendedor salvarVendedor(Vendedor vendedor) {
		return vendedorRepository.save(vendedor);
	}

	public Vendedor updateVendedor(Long id, Vendedor updatedVendedor) {
		Optional<Vendedor> existingVendedor = vendedorRepository.findById(id);
		if (existingVendedor.isPresent()) {
			updatedVendedor.setId(id);
			return vendedorRepository.save(updatedVendedor);
		}
		return null;
	}

	public boolean deleteVendedor(Long id) {
		Optional<Vendedor> existingVendedor = vendedorRepository.findById(id);
		if (existingVendedor.isPresent()) {
			vendedorRepository.deleteById(id);
			return true;
		}
		return false;
	}



}


