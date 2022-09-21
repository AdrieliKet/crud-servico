package com.dev.adrieli.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.adrieli.entity.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long>{
	@Query("select s from Servico s where s.status='pendente' or s.valorPago = 0")
	List<Servico> buscarServicoPagamentoPendente();
	
	@Query("select s from Servico s where s.status='cancelado'")
	List<Servico> buscarServicoCancelado();
	
}
