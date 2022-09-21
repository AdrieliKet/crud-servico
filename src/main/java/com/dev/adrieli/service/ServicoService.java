package com.dev.adrieli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dev.adrieli.entity.Servico;
import com.dev.adrieli.repository.ServicoRepository;

import java.util.List;

@Service
public class ServicoService {
	
	@Autowired
	ServicoRepository servicoRepository;
	
	private boolean existsById(Long id) {
        return servicoRepository.existsById(id);
    }
    
    public Servico findById(Long id)  {
    	Servico servico = servicoRepository.findById(id).orElse(null);
        return servico;
    }
    
    public Page<Servico> findAll(Pageable pageable) {
        
        return servicoRepository.findAll(pageable);
    }
    
    public List<Servico> buscarServicoPagamentoPendente() {
        
        return servicoRepository.buscarServicoPagamentoPendente();
    }
   
   
    public Servico save(Servico servico)  {
    	if (servico.getValorPago() == 0 && servico.getDataPagamento() == null) {
    		servico.setStatus("pendente");
    	} else {
    		servico.setStatus("realizado");
    	}
    	return servicoRepository.save(servico);
    }
    
    public Servico update(Servico servico) {
        if (servico.getValorPago() >0 && servico.getDataPagamento() != null) {
            servico.setStatus("realizado");
        }
        return servicoRepository.save(servico);
    }    
  
    
    public void delete(Long id)  {
        Servico servico = servicoRepository.findById(id).get();
        servicoRepository.delete(servico);
    }
    
    public Long count() {
        return servicoRepository.count();
    }
    
    public Servico realizarPagamento(Long id, Double valorPago) {
		Servico servico = servicoRepository.findById(id).get();
		servico.setStatus("realizado");
		servico.setValorPago(valorPago);
		return servicoRepository.save(servico);
	}
    
    public Servico cancelarServico(Long idServico) {
		Servico servico = servicoRepository.findById(idServico).get();
		servico.setStatus("cancelado");
		return servicoRepository.save(servico);
	}
    
}
