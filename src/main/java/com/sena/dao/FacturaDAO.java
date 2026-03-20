package com.sena.dao;

import com.sena.modelo.Conexion;
import com.sena.modelo.DetalleFactura;
import com.sena.modelo.Factura;
/*import java.math.BigDecimal;*/
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operaciones CRUD de facturas
 * Adaptado a BD: softwarefacturacion
 * @author [TU NOMBRE]
 * @version 1.0
 */
public class FacturaDAO {
    
    private Conexion conexion;
    
    public FacturaDAO() {
        this.conexion = new Conexion();
    }
    
    public boolean crearFactura(Factura factura, List<DetalleFactura> detalles) {
        Connection conn = conexion.getConnection();
        boolean exito = false;
        
        try {
            conn.setAutoCommit(false);
            
            String sqlFactura = "INSERT INTO factura (`IdUsuario`, `IdCliente`, `Fecha_Venta`, `Forma_dePago`, `Descuento`, `Total`, `Observaciones`) VALUES (?, ?, CURDATE(), ?, ?, ?, ?)";
            PreparedStatement psFactura = conn.prepareStatement(sqlFactura, Statement.RETURN_GENERATED_KEYS);
            
            psFactura.setInt(1, factura.getIdUsuario());
            psFactura.setInt(2, factura.getIdCliente());
            psFactura.setString(3, factura.getFormaDePago());
            psFactura.setBigDecimal(4, factura.getDescuento());
            psFactura.setBigDecimal(5, factura.getTotal());
            psFactura.setString(6, factura.getObservaciones());
            
            psFactura.executeUpdate();
            
            ResultSet rs = psFactura.getGeneratedKeys();
            int facturaId = 0;
            if (rs.next()) {
                facturaId = rs.getInt(1);
            }
            rs.close();
            psFactura.close();
            
            String sqlDetalle = "INSERT INTO detalle_factura (`IdFactura`, `IdProducto`, `Cantidad`, `Subtotal`) VALUES (?, ?, ?, ?)";
            PreparedStatement psDetalle = conn.prepareStatement(sqlDetalle);
            
            ProductoDAO productoDAO = new ProductoDAO();
            
            for (DetalleFactura detalle : detalles) {
                psDetalle.setInt(1, facturaId);
                psDetalle.setInt(2, detalle.getIdProducto());
                psDetalle.setInt(3, detalle.getCantidad());
                psDetalle.setBigDecimal(4, detalle.getSubtotal());
                psDetalle.addBatch();
                
                productoDAO.actualizarStock(detalle.getIdProducto(), detalle.getCantidad());
            }
            
            psDetalle.executeBatch();
            psDetalle.close();
            
            conn.commit();
            exito = true;
            
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.err.println("✗ Error al hacer rollback: " + ex.getMessage());
            }
            System.err.println("✗ Error al crear factura: " + e.getMessage());
            e.printStackTrace();
            exito = false;
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        
        return exito;
    }
    
    public List<Factura> consultarTodas() {
        List<Factura> lista = new ArrayList<>();
        String sql = "SELECT f.*, c.`Nombre`, c.`Apellido` FROM factura f " +
                     "INNER JOIN clientes c ON f.`IdCliente` = c.`IdCliente` " +
                     "ORDER BY f.`Fecha_Venta` DESC";
        try (Statement stmt = conexion.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Factura factura = new Factura();
                factura.setIdFactura(rs.getInt("IdFactura"));
                factura.setIdUsuario(rs.getInt("IdUsuario"));
                factura.setIdCliente(rs.getInt("IdCliente"));
                factura.setNombreCliente(rs.getString("Nombre") + " " + rs.getString("Apellido"));
                factura.setFechaVenta(rs.getDate("Fecha_Venta"));
                factura.setFormaDePago(rs.getString("Forma_dePago"));
                factura.setDescuento(rs.getBigDecimal("Descuento"));
                factura.setTotal(rs.getBigDecimal("Total"));
                factura.setObservaciones(rs.getString("Observaciones"));
                lista.add(factura);
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al consultar facturas: " + e.getMessage());
        }
        return lista;
    }
    
    public Factura consultarPorId(int id) {
        String sql = "SELECT f.*, c.`Nombre`, c.`Apellido` FROM factura f " +
                     "INNER JOIN clientes c ON f.`IdCliente` = c.`IdCliente` WHERE f.`IdFactura` = ?";
        try (PreparedStatement ps = conexion.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Factura factura = new Factura();
                factura.setIdFactura(rs.getInt("IdFactura"));
                factura.setIdUsuario(rs.getInt("IdUsuario"));
                factura.setIdCliente(rs.getInt("IdCliente"));
                factura.setNombreCliente(rs.getString("Nombre") + " " + rs.getString("Apellido"));
                factura.setFechaVenta(rs.getDate("Fecha_Venta"));
                factura.setFormaDePago(rs.getString("Forma_dePago"));
                factura.setDescuento(rs.getBigDecimal("Descuento"));
                factura.setTotal(rs.getBigDecimal("Total"));
                factura.setObservaciones(rs.getString("Observaciones"));
                factura.setDetalles(consultarDetalles(id));
                return factura;
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al consultar factura: " + e.getMessage());
        }
        return null;
    }
    
    public List<DetalleFactura> consultarDetalles(int facturaId) {
        List<DetalleFactura> lista = new ArrayList<>();
        String sql = "SELECT df.*, p.`Nombre_Producto` FROM detalle_factura df " +
                     "INNER JOIN productos p ON df.`IdProducto` = p.`IdProducto` " +
                     "WHERE df.`IdFactura` = ?";
        try (PreparedStatement ps = conexion.getConnection().prepareStatement(sql)) {
            ps.setInt(1, facturaId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DetalleFactura detalle = new DetalleFactura();
                detalle.setIdDetalle(rs.getInt("IdDetalle"));
                detalle.setIdFactura(rs.getInt("IdFactura"));
                detalle.setIdProducto(rs.getInt("IdProducto"));
                detalle.setNombreProducto(rs.getString("Nombre_Producto"));
                detalle.setCantidad(rs.getInt("Cantidad"));
                detalle.setSubtotal(rs.getBigDecimal("Subtotal"));
                lista.add(detalle);
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al consultar detalles: " + e.getMessage());
        }
        return lista;
    }
    
    public void cerrar() {
        conexion.cerrarConexion();
    }
}