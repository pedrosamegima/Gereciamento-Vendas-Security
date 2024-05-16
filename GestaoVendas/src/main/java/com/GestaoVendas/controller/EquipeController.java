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

import com.GestaoVendas.entities.Equipe;
import com.GestaoVendas.service.EquipeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name= "equipe", description = "API REST DE GERENCIAMENTO DE EQUIPES")
@RestController
@RequestMapping("/equipe")
public class EquipeController {
private final EquipeService equipeService;
	
	@Autowired
	public EquipeController(EquipeService equipeService) {
		this.equipeService = equipeService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza a equipe por Id")
	public ResponseEntity<Equipe> getEquipeById(@PathVariable Long id) {
		Equipe equipe = equipeService.getEquipeById(id);
		if (equipe != null) {
			return ResponseEntity.ok(equipe);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Aprensenta todos as equipes")
	public ResponseEntity<List<Equipe>> Equipe() {
		List<Equipe> equipe = equipeService.getAllEquipe();
		return ResponseEntity.ok(equipe);
	}
	//query
	@GetMapping("/nome/{nome}")
	@Operation(summary = "Aprensenta o nome das equipes")
	public ResponseEntity<List<Equipe>> getEquipesPorNome(@PathVariable String nome){
		List<Equipe> equipe = equipeService.getEquipesPorNome(nome);
		return ResponseEntity.ok(equipe);
	}
	//@Query
	@GetMapping("/cidade/{cidade}")
	@Operation(summary = "Aprensenta a cidade das equipes")
	public List<Equipe> findEquipePorCidade(@PathVariable String cidade){
	return equipeService.findByCidade(cidade);
	}

	@PostMapping("/")
	@Operation(summary = "Cadastra uma equipe")
	public ResponseEntity<Equipe> criarEquipe(@RequestBody Equipe equipe) {
		Equipe criarEquipe = equipeService.salvarEquipe(equipe);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarEquipe);
	}


	@PutMapping("/{id}")
	@Operation(summary = "Altera a equipe")
	public ResponseEntity<Equipe> updateEquipe(@PathVariable Long id,@RequestBody Equipe equipe) {
		Equipe updatedEquipe = equipeService.updateEquipe(id, equipe);
		if (updatedEquipe != null) {
			return ResponseEntity.ok(updatedEquipe);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta a equipe")
	public ResponseEntity<String> deleteEquipe(@PathVariable Long id) {
		boolean deleted = equipeService.deleteEquipe(id);
		if (deleted) {
			return ResponseEntity.ok().body("A equipe foi excluído com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}


}


