package com.sena.modelo;

import java.math.BigDecimal;

/**
 * Clase modelo que representa un detalle de factura
 * Adaptado a BD: softwarefacturacion
 * @author [TU NOMBRE]
 * @version 1.0
 */
public class DetalleFactura {
    
    private int idDetalle;
    private int idFactura;
    private int idProducto;
    private String nombreProducto;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    
    public DetalleFactura() {}
    
    public DetalleFactura(int idProducto, int cantidad, BigDecimal subtotal) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }
    
    public int getIdDetalle() { return idDetalle; }
    public void setIdDetalle(int idDetalle) { this.idDetalle = idDetalle; }
    public int getIdFactura() { return idFactura; }
    public void setIdFactura(int idFactura) { this.idFactura = idFactura; }
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    
    @Override
    public String toString() {
        return "DetalleFactura{" + "idProducto=" + idProducto + ", cantidad=" + cantidad + ", subtotal=" + subtotal + '}';
    }
}