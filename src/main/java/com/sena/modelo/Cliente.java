package com.sena.modelo;

/**
 * Clase modelo que representa un cliente
 * Adaptado a BD: softwarefacturacion
 * @author [TU NOMBRE]
 * @version 1.0
 */
public class Cliente {
    
    private int idCliente;
    private String nombre;
    private String apellido;
    private String cedula;
    private String nCelular;
    private String email;
    private String direccion;
    
    public Cliente() {}
    
    public Cliente(String nombre, String apellido, String cedula, String nCelular, String email, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.nCelular = nCelular;
        this.email = email;
        this.direccion = direccion;
    }
    
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    public String getNCelular() { return nCelular; }
    public void setNCelular(String nCelular) { this.nCelular = nCelular; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    
    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nombre='" + nombre + '\'' + ", apellido='" + apellido + '\'' + '}';
    }
}