package com.dev.adrieli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dev.adrieli.entity.Servico;
import com.dev.adrieli.repository.ServicoRepository;

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
    
    public Page<Servico> buscarServicoPagamentoPendente(Pageable pageable) {
        
        return servicoRepository.buscarServicoPagamentoPendente(pageable);
    }
   
   
    public Servico save(Servico servico)  {
    	if (servico.getValorPago() == 0 && servico.getDataPagamento() == null) {
    		servico.setStatus("pendente");
    	} else {
    		servico.setStatus("realizado");
    	}
    	return servicoRepository.save(servico);
    }
    
    public void update(Servico servico) {      
    	servicoRepository.save(servico);       
    }    
  
    
    public void deleteById(Long id)  {
        if (!existsById(id)) {         
        	servicoRepository.deleteById(id);
        }        
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
