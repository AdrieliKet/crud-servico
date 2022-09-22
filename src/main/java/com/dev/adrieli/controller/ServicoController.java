package com.dev.adrieli.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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
	
	@GetMapping(value = "/")
	@CrossOrigin("http://localhost:3000")
	public List<Servico> findAll() {
		return servicoService.findAll();
	}
	
	@GetMapping(value = "/buscarPagamentoPendente")
	@CrossOrigin("http://localhost:3000")
	public List<Servico> buscarServicoPagamentoPendente() {
		return servicoService.buscarServicoPagamentoPendente();
	}
	@GetMapping(value = "/buscarCancelado")
	@CrossOrigin("http://localhost:3000")
	public List<Servico> buscarServicoCancelado() {
		return servicoRepository.buscarServicoCancelado();
	}

	@GetMapping(value = "/{id}")
	@CrossOrigin("http://localhost:3000")
	public Servico findById(@PathVariable long id) {
		Servico servico = servicoService.findById(id);
		return servico;
	}
	//localhost:8080/api/servico/realizarPagamento?id=1&valorPago=30.0
	@GetMapping(value = "/realizarPagamento/")
	@CrossOrigin("http://localhost:3000")
	public Servico realizarPagamento(@RequestParam long id, @RequestParam Double valorPago) {
		return servicoService.realizarPagamento(id, valorPago);
	}
	
	@PutMapping(value = "/cancelarServico/{id}")
	@CrossOrigin("http://localhost:3000")
	public Servico cancelarServico(@PathVariable long id) {
		return servicoService.cancelarServico(id);
	}

	@PostMapping(value = "/")
	@CrossOrigin("http://localhost:3000")
	public Servico add(@RequestBody Servico servico) {
		return  servicoService.save(servico);
	}

	@PutMapping(value = "/{id}")
	@CrossOrigin("http://localhost:3000")
	public Servico update(@Valid @RequestBody Servico servico, @PathVariable long id) {
		return servicoService.update(servico);
	}

	@DeleteMapping(path = "/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Void> delete(@PathVariable long id) {
		servicoService.delete(id);
		return ResponseEntity.ok().build();
	}
}
