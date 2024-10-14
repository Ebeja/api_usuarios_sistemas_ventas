package com.example.demo.service;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.model.Administrador;
import com.example.demo.model.Vendedor;
import com.example.demo.repository.AdministradorRepository;
import com.example.demo.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Método para crear un nuevo administrador
    public Administrador crearAdministrador(Administrador admin) {
        String passwordHash = passwordEncoder.encode(admin.getClaveCifrada());
        admin.setClaveCifrada(passwordHash);
        return administradorRepository.save(admin);
    }

    // Método para crear un nuevo vendedor
    public Vendedor crearVendedor(Vendedor vendedor) {
        String passwordHash = passwordEncoder.encode(vendedor.getClaveCifrada());
        vendedor.setClaveCifrada(passwordHash);
        return vendedorRepository.save(vendedor);
    }

    // Método para actualizar parcialmente un administrador
    public Administrador actualizarParcialAdministrador(int id, Map<String, Object> updates) {
        Optional<Administrador> optionalAdmin = administradorRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            Administrador admin = optionalAdmin.get();

            // Actualiza los campos que se envían en el mapa
            updates.forEach((key, value) -> {
                switch (key) {
                    case "nombre":
                        admin.setNombre((String) value);
                        break;
                    case "apellido":
                        admin.setApellido((String) value);
                        break;
                    case "dni":
                        admin.setDni((String) value);
                        break;
                    case "usuario":
                        admin.setUsuario((String) value);
                        break;
                    case "claveCifrada":
                        admin.setClaveCifrada(passwordEncoder.encode((String) value)); // Asegúrate de cifrar la nueva contraseña
                        break;
                    case "estado":
                        admin.setEstado((Integer) value);
                        break;
                }
            });

            return administradorRepository.save(admin);
        } else {
            throw new RuntimeException("Administrador no encontrado con ID: " + id);
        }
    }

    // Método para actualizar parcialmente un vendedor
    public Vendedor actualizarParcialVendedor(int id, Map<String, Object> updates) {
        Optional<Vendedor> optionalVendedor = vendedorRepository.findById(id);
        if (optionalVendedor.isPresent()) {
            Vendedor vendedor = optionalVendedor.get();

            // Actualiza los campos que se envían en el mapa
            updates.forEach((key, value) -> {
                switch (key) {
                    case "nombre":
                        vendedor.setNombre((String) value);
                        break;
                    case "apellido":
                        vendedor.setApellido((String) value);
                        break;
                    case "dni":
                        vendedor.setDni((String) value);
                        break;
                    case "usuario":
                        vendedor.setUsuario((String) value);
                        break;
                    case "claveCifrada":
                        vendedor.setClaveCifrada(passwordEncoder.encode((String) value)); // Asegúrate de cifrar la nueva contraseña
                        break;
                    case "estado":
                        vendedor.setEstado((Integer) value);
                        break;
                    // Agrega otros campos si es necesario
                }
            });

            return vendedorRepository.save(vendedor);
        } else {
            throw new RuntimeException("Vendedor no encontrado con ID: " + id);
        }
    }


    // Métodos para obtener todos los administradores
    public List<Administrador> obtenerAdministradores() {
        return administradorRepository.findByEstado(1);
    }

    // Métodos para obtener todos los vendedores
    public List<Vendedor> obtenerVendedores() {
        return vendedorRepository.findByEstado(1);
    }

    // Método para eliminar un administrador por ID
    public void eliminarAdministrador(int id) {
        Optional<Administrador> adminOpt = administradorRepository.findById(id);
        if (adminOpt.isPresent()) {
            Administrador administrador = adminOpt.get();
            administrador.setEstado(0); // Cambia el estado a 0
            administradorRepository.save(administrador); // Guarda la entidad actualizada
        } else {
            throw new RuntimeException("Administrador no encontrado con ID: " + id);
        }
    }


    // Método para eliminar un vendedor por ID
    public void eliminarVendedor(int id) {
        Optional<Vendedor> vendedorOpt = vendedorRepository.findById(id);
        if (vendedorOpt.isPresent()) {
            Vendedor vendedor = vendedorOpt.get();
            vendedor.setEstado(0); // Cambia el estado a 0
            vendedorRepository.save(vendedor); // Guarda la entidad actualizada
        } else {
            throw new RuntimeException("Vendedor no encontrado con ID: " + id);
        }
    }

    // Método para validar el login (implementa tu lógica de validación aquí)
    public ApiResponse validarLogin(LoginRequest loginRequest) {
        // Verifica el login en la tabla de administradores
        Optional<Administrador> administrador = administradorRepository.findByUsuario(loginRequest.getUsuario());

        if (administrador.isPresent()) {
            // Validar la contraseña
            if (passwordEncoder.matches(loginRequest.getClave(), administrador.get().getClaveCifrada())) {
                // Verifica si el estado es activo (1)
                if (administrador.get().getEstado() == 1) {
                    return new ApiResponse(true, "Login exitoso como Administrador.");
                } else {
                    return new ApiResponse(false, "El administrador está inactivo.");
                }
            } else {
                return new ApiResponse(false, "Contraseña incorrecta para el administrador.");
            }
        }

        // Si no se encontró en administradores, verificar en la tabla de vendedores
        Optional<Vendedor> vendedor = vendedorRepository.findByUsuario(loginRequest.getUsuario());

        if (vendedor.isPresent()) {
            // Validar la contraseña
            if (passwordEncoder.matches(loginRequest.getClave(), vendedor.get().getClaveCifrada())) {
                // Verifica si el estado es activo (1)
                if (vendedor.get().getEstado() == 1) {
                    return new ApiResponse(true, "Login exitoso como Vendedor.");
                } else {
                    return new ApiResponse(false, "El vendedor está inactivo.");
                }
            } else {
                return new ApiResponse(false, "Contraseña incorrecta para el vendedor.");
            }
        }

        // Si no se encontró ni en administradores ni en vendedores
        return new ApiResponse(false, "Usuario no encontrado.");
    }


}