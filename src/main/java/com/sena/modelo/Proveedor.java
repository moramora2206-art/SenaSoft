package com.sena.modelo;

/**
 * Clase modelo que representa un proveedor
 * Adaptado a BD: softwarefacturacion
 * @author [TU NOMBRE]
 * @version 1.0
 */
public class Proveedor {
    
    private int idProveedor;
    private String nombreProveedor;
    private int nit;
    private String nombreContacto;
    private String nCelular;
    private String email;
    
    public Proveedor() {}
    
    public Proveedor(String nombreProveedor, int nit, String nombreContacto, String nCelular, String email) {
        this.nombreProveedor = nombreProveedor;
        this.nit = nit;
        this.nombreContacto = nombreContacto;
        this.nCelular = nCelular;
        this.email = email;
    }
    
    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }
    public String getNombreProveedor() { return nombreProveedor; }
    public void setNombreProveedor(String nombreProveedor) { this.nombreProveedor = nombreProveedor; }
    public int getNit() { return nit; }
    public void setNit(int nit) { this.nit = nit; }
    public String getNombreContacto() { return nombreContacto; }
    public void setNombreContacto(String nombreContacto) { this.nombreContacto = nombreContacto; }
    public String getNCelular() { return nCelular; }
    public void setNCelular(String nCelular) { this.nCelular = nCelular; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    @Override
    public String toString() {
        return "Proveedor{" + "idProveedor=" + idProveedor + ", nombreProveedor='" + nombreProveedor + '\'' + '}';
    }
}