package org.generation.games.controller;


import java.util.List;

import org.generation.games.model.Games;
import org.generation.games.repository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "*")
public class GamesController {
	
	@Autowired
	private GamesRepository repo;
	
	@GetMapping
	public ResponseEntity<List<Games>> getAll ()
	{
		return ResponseEntity.ok(repo.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Games> getById (@PathVariable Long id)
	{
		return repo.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	
	}
	
	@GetMapping ("nome/{nome}")
	public ResponseEntity<List<Games>> getByName (@PathVariable String nome){
		return ResponseEntity.ok(repo.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping ("ano/{lancamento}")
	public ResponseEntity<List<Games>> getByAno (@PathVariable int lancamento){
		return ResponseEntity.ok(repo.findAllByAnoLancamento(lancamento));
	}

}
