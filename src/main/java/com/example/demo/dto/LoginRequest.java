package com.example.demo.dto;

public class LoginRequest {
    private String usuario;
    private String clave;

    // Getters
    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

    // Setters
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
