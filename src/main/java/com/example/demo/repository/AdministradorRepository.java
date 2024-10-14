package com.example.demo.repository;

import com.example.demo.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    Optional<Administrador> findByUsuario(String usuario);
    List<Administrador> findByEstado(int estado);  // Método para obtener administradores con estado 1
}

