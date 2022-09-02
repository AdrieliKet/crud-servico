package com.dev.adrieli.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "servico")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class Servico {
	
	 private static final long serialVersionUID = 1L;
	 
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;
	 private String nomeCliente;
	 private Date dataInicio;
	 private Date dataTermino;
	 private String descricao;
	 private double valorServico;
	 private double valorPago;
	 private Date dataPagamento;
	 private String status;
	 @ManyToOne
	 @JoinColumn(name = "idCliente")
	 private Cliente cliente;
	 @Temporal(TemporalType.TIMESTAMP)
	 private Date dataCadastro = new Date();

}
