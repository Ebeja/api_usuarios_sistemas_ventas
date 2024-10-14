package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vendedores")
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento
    @Column(name = "id_user") // Asegúrate de que coincide con el nombre de la columna
    private int idUser;
    private String nombre;
    private String apellido;
    private String dni;
    private String usuario;
    @Column(name = "clave_cifrada") // Asegúrate de que coincide con el nombre de la columna
    private String claveCifrada;  // Aquí se almacena la contraseña cifrada
    private int estado;

    // Getters y Setters
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClaveCifrada() {
        return claveCifrada;
    }

    public void setClaveCifrada(String claveCifrada) {
        this.claveCifrada = claveCifrada;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}

