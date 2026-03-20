package com.sena.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Clase modelo que representa una factura
 * Adaptado a BD: softwarefacturacion
 * @author [TU NOMBRE]
 * @version 1.0
 */
public class Factura {
    
    private int idFactura;
    private int idUsuario;
    private int idCliente;
    private String nombreCliente;
    private Date fechaVenta;
    private String formaDePago;
    private BigDecimal descuento;
    private BigDecimal total;
    private String observaciones;
    private List<DetalleFactura> detalles;
    
    public Factura() {}
    
    public Factura(int idUsuario, int idCliente, BigDecimal total) {
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
        this.total = total;
    }
    
    public int getIdFactura() { return idFactura; }
    public void setIdFactura(int idFactura) { this.idFactura = idFactura; }
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public Date getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(Date fechaVenta) { this.fechaVenta = fechaVenta; }
    public String getFormaDePago() { return formaDePago; }
    public void setFormaDePago(String formaDePago) { this.formaDePago = formaDePago; }
    public BigDecimal getDescuento() { return descuento; }
    public void setDescuento(BigDecimal descuento) { this.descuento = descuento; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public List<DetalleFactura> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleFactura> detalles) { this.detalles = detalles; }
    
    @Override
    public String toString() {
        return "Factura{" + "idFactura=" + idFactura + ", total=" + total + '}';
    }
}