package com.dev.adrieli.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.adrieli.entity.Servico;
import com.dev.adrieli.repository.ServicoRepository;
import com.dev.adrieli.service.ServicoService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/servico")
public class ServicoController {
	@Autowired
	ServicoService servicoService;
	
	@Autowired
	ServicoRepository servicoRepository;
	
	@GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Page<Servico>> findAll(Pageable pageable) {
		return ResponseEntity.ok(servicoService.findAll(pageable));
	}
	
	@GetMapping(value = "/buscarPagamentoPendente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Page<Servico>> buscarServicoPagamentoPendente(Pageable pageable) {
		return ResponseEntity.ok(servicoService.buscarServicoPagamentoPendente(pageable));
	}
	@GetMapping(value = "/buscarCancelado", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Page<Servico>> buscarServicoCancelado(Pageable pageable) {
		return ResponseEntity.ok(servicoRepository.buscarServicoCancelado(pageable));
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Servico> findById(@PathVariable long id) {
		Servico servico = servicoService.findById(id);
		return ResponseEntity.ok(servico);
	}
	//localhost:8080/api/servico/realizarPagamento?id=1&valorPago=30.0
	@GetMapping(value = "/realizarPagamento", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin("http://localhost:3000")
	public Servico realizarPagamento(@RequestParam long id, @RequestParam Double valorPago) {
		return servicoService.realizarPagamento(id, valorPago);
	}
	
	@GetMapping(value = "/cancelarServico/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin("http://localhost:3000")
	public Servico cancelarServico(@PathVariable long id) {
		return servicoService.cancelarServico(id);
	}

	@PostMapping(value = "/")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Servico> add(@RequestBody Servico servico) 
			throws URISyntaxException {
		Servico servicoNovo = servicoService.save(servico);
		return ResponseEntity.created(new URI("/api/servico/" + 
				servicoNovo.getId())).body(servicoNovo);
	}

	@PutMapping(value = "/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Servico> update(@Valid @RequestBody Servico servico, @PathVariable long id) {
		servico.setId(id);
		servicoService.update(servico);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Void> deleteById(@PathVariable long id) {
		servicoService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
