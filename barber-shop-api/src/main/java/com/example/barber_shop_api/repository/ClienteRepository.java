package com.example.barber_shop_api.repository;

import com.example.barber_shop_api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}