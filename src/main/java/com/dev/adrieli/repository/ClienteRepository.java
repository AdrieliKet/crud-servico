package com.dev.adrieli.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.adrieli.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
