package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.PostagemModel;
import com.generation.blogpessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagem")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class PostagemController {

	@Autowired
	private PostagemRepository repository;
	
	//select*from tb_postagem; maneira que o hibernate e jpa recebe a info
	@GetMapping
	public ResponseEntity<List<PostagemModel>> getAll(){
		return ResponseEntity.ok(repository.findAll());		
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostagemModel> getById(@PathVariable Long id){
		return repository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		
	}
	
	@GetMapping("/título/{título}")
	public ResponseEntity<List<PostagemModel>>getByTítulo(@PathVariable String título){
		return ResponseEntity.ok(repository.findAllByTítuloContainingIgnoreCase(título));
	}
	
	@PostMapping
	public ResponseEntity<PostagemModel> post(@Valid @RequestBody PostagemModel postagem){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(postagem));
	}

				
	@PutMapping 
	public ResponseEntity<PostagemModel> put(@Valid @RequestBody PostagemModel postagem) { 
		return repository.findById(postagem.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
					.body(repository.save(postagem))) 
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	

	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		Optional<PostagemModel> postagem = repository.findById(id);
		
		if(postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	
		repository.deleteById(id);
	}
	
	
}


