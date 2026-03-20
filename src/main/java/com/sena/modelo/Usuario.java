package com.sena.modelo;

/**
 * Clase modelo que representa un usuario (Login)
 * Adaptado a BD: softwarefacturacion
 * @author [TU NOMBRE]
 * @version 1.0
 */
public class Usuario {
    
    private int idUsuario;
    private String usuario;
    private String contraseña;
    private String nombre;
    private String apellido;
    private String email;
    private String nCelular;
    private String rol;
    
    public Usuario() {}
    
    public Usuario(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }
    
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNCelular() { return nCelular; }
    public void setNCelular(String nCelular) { this.nCelular = nCelular; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    
    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", usuario='" + usuario + "', rol='" + rol + '\'' + '}';
    }
}