package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.model.Administrador;
import com.example.demo.model.Vendedor;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint para login
    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest loginRequest) {
        return usuarioService.validarLogin(loginRequest);
    }

    // Endpoint para crear un nuevo administrador
    @PostMapping("/administradores")
    public ApiResponse crearAdministrador(@RequestBody Administrador administrador) {
        usuarioService.crearAdministrador(administrador);
        return new ApiResponse(true, "Administrador creado exitosamente");
    }

    // Endpoint para crear un nuevo vendedor
    @PostMapping("/vendedores")
    public ApiResponse crearVendedor(@RequestBody Vendedor vendedor) {
        usuarioService.crearVendedor(vendedor);
        return new ApiResponse(true, "Vendedor creado exitosamente");
    }

    // Endpoint para obtener todos los administradores
    @GetMapping("/administradores")
    public List<Administrador> obtenerAdministradores() {
        return usuarioService.obtenerAdministradores(); // Asegúrate de que este método esté implementado en UsuarioService
    }

    // Endpoint para obtener todos los vendedores
    @GetMapping("/vendedores")
    public List<Vendedor> obtenerVendedores() {
        return usuarioService.obtenerVendedores(); // Asegúrate de que este método esté implementado en UsuarioService
    }

    // Endpoint para actualizar parcialmente un administrador
    @PatchMapping("/administradores/{id}")
    public ApiResponse actualizarParcialAdministrador(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        Administrador updatedAdmin = usuarioService.actualizarParcialAdministrador(id, updates);
        return new ApiResponse(true, "Administrador actualizado exitosamente");
    }

    // Endpoint para actualizar parcialmente un vendedor
    @PatchMapping("/vendedores/{id}")
    public ApiResponse actualizarParcialVendedor(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        Vendedor updatedVendedor = usuarioService.actualizarParcialVendedor(id, updates);
        return new ApiResponse(true, "Vendedor actualizado exitosamente");
    }


    // Endpoint para eliminar un administrador
    @DeleteMapping("/administradores/{id}")
    public ApiResponse eliminarAdministrador(@PathVariable int id) {
        usuarioService.eliminarAdministrador(id); // Asegúrate de que este método esté implementado en UsuarioService
        return new ApiResponse(true, "Administrador eliminado exitosamente");
    }

    // Endpoint para eliminar un vendedor
    @DeleteMapping("/vendedores/{id}")
    public ApiResponse eliminarVendedor(@PathVariable int id) {
        usuarioService.eliminarVendedor(id); // Asegúrate de que este método esté implementado en UsuarioService
        return new ApiResponse(true, "Vendedor eliminado exitosamente");
    }
}
