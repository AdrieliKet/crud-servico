package com.dev.adrieli.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.adrieli.entity.Cliente;
import com.dev.adrieli.service.ClienteService;


@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
	@Autowired
	ClienteService clienteService;
	
	@GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Cliente>> findAll(Pageable pageable) {
		return ResponseEntity.ok(clienteService.findAll(pageable));
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> findById(@PathVariable long id) {
		Cliente cliente = clienteService.findById(id);
		return ResponseEntity.ok(cliente);
	}

	@PostMapping(value = "/")
	public ResponseEntity<Cliente> add(@RequestBody Cliente cliente) 
			throws URISyntaxException {
		Cliente clienteNovo = clienteService.save(cliente);
		return ResponseEntity.created(new URI("/api/cliente/" + 
				clienteNovo.getId())).body(clienteNovo);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente> update(@Valid @RequestBody Cliente cliente, @PathVariable long id) {
		cliente.setId(id);
		clienteService.update(cliente);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable long id) {
		clienteService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
