package com.sena.dao;

import com.sena.modelo.Conexion;
import com.sena.modelo.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operaciones CRUD de productos
 * Adaptado a BD: softwarefacturacion
 * @author [TU NOMBRE]
 * @version 1.0
 */
public class ProductoDAO {
    
    private Conexion conexion;
    
    public ProductoDAO() {
        this.conexion = new Conexion();
    }
    
    public boolean insertar(Producto producto) {
        String sql = "INSERT INTO productos (`IdProveedor`, `Nombre_Producto`, `Codigo_SKU`, `Stock`, `Precio_Unitario`, `Descripcion`) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.getConnection().prepareStatement(sql)) {
            ps.setInt(1, producto.getIdProveedor());
            ps.setString(2, producto.getNombreProducto());
            ps.setString(3, producto.getCodigoSKU());
            ps.setInt(4, producto.getStock());
            ps.setBigDecimal(5, producto.getPrecioUnitario());
            ps.setString(6, producto.getDescripcion());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("✗ Error al insertar producto: " + e.getMessage());
            return false;
        }
    }
    
    public List<Producto> consultarTodos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos ORDER BY `Nombre_Producto` ASC";
        try (Statement stmt = conexion.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("IdProducto"));
                producto.setIdProveedor(rs.getInt("IdProveedor"));
                producto.setNombreProducto(rs.getString("Nombre_Producto"));
                producto.setCodigoSKU(rs.getString("Codigo_SKU"));
                producto.setStock(rs.getInt("Stock"));
                producto.setPrecioUnitario(rs.getBigDecimal("Precio_Unitario"));
                producto.setDescripcion(rs.getString("Descripcion"));
                lista.add(producto);
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al consultar productos: " + e.getMessage());
        }
        return lista;
    }
    
    public List<Producto> listar() {
        return consultarTodos();
    }
    
    public Producto consultarPorId(int id) {
        String sql = "SELECT * FROM productos WHERE `IdProducto` = ?";
        try (PreparedStatement ps = conexion.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("IdProducto"));
                producto.setIdProveedor(rs.getInt("IdProveedor"));
                producto.setNombreProducto(rs.getString("Nombre_Producto"));
                producto.setCodigoSKU(rs.getString("Codigo_SKU"));
                producto.setStock(rs.getInt("Stock"));
                producto.setPrecioUnitario(rs.getBigDecimal("Precio_Unitario"));
                producto.setDescripcion(rs.getString("Descripcion"));
                return producto;
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al consultar producto: " + e.getMessage());
        }
        return null;
    }
    
    public boolean actualizar(Producto producto) {
        String sql = "UPDATE productos SET `IdProveedor`=?, `Nombre_Producto`=?, `Codigo_SKU`=?, `Stock`=?, `Precio_Unitario`=?, `Descripcion`=? WHERE `IdProducto`=?";
        try (PreparedStatement ps = conexion.getConnection().prepareStatement(sql)) {
            ps.setInt(1, producto.getIdProveedor());
            ps.setString(2, producto.getNombreProducto());
            ps.setString(3, producto.getCodigoSKU());
            ps.setInt(4, producto.getStock());
            ps.setBigDecimal(5, producto.getPrecioUnitario());
            ps.setString(6, producto.getDescripcion());
            ps.setInt(7, producto.getIdProducto());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("✗ Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }
    
    public boolean actualizarStock(int productoId, int cantidad) {
        String sql = "UPDATE productos SET `Stock` = `Stock` - ? WHERE `IdProducto` = ? AND `Stock` >= ?";
        try (PreparedStatement ps = conexion.getConnection().prepareStatement(sql)) {
            ps.setInt(1, cantidad);
            ps.setInt(2, productoId);
            ps.setInt(3, cantidad);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("✗ Error al actualizar stock: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminar(int id) {
        String sql = "DELETE FROM productos WHERE `IdProducto` = ?";
        try (PreparedStatement ps = conexion.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("✗ Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }
    
    public void cerrar() {
        conexion.cerrarConexion();
    }
}