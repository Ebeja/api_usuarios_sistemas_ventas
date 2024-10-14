package com.example.demo.repository;



import com.example.demo.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {
    Optional<Vendedor> findByUsuario(String usuario);
    List<Vendedor> findByEstado(int estado);
}

