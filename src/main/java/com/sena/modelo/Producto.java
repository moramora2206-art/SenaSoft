package com.sena.modelo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Clase modelo que representa un producto
 * Adaptado a BD: softwarefacturacion
 * @author [TU NOMBRE]
 * @version 1.0
 */
public class Producto {
    
    private int idProducto;
    private int idProveedor;
    private String nombreProducto;
    private String codigoSKU;
    private int stock;
    private BigDecimal precioUnitario;
    private Date fechaVencimiento;
    private String descripcion;
    
    public Producto() {}
    
    public Producto(String nombreProducto, String codigoSKU, BigDecimal precioUnitario, int stock) {
        this.nombreProducto = nombreProducto;
        this.codigoSKU = codigoSKU;
        this.precioUnitario = precioUnitario;
        this.stock = stock;
    }
    
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
    public String getCodigoSKU() { return codigoSKU; }
    public void setCodigoSKU(String codigoSKU) { this.codigoSKU = codigoSKU; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
    public Date getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(Date fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nombreProducto='" + nombreProducto + '\'' + '}';
    }
}