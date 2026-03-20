package com.sena.dao;

import com.sena.modelo.Conexion;
import com.sena.modelo.Proveedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operaciones CRUD de proveedores
 * Adaptado a BD: softwarefacturacion
 * @author [TU NOMBRE]
 * @version 1.0
 */
public class ProveedorDAO {
    
    private Conexion conexion;
    
    public ProveedorDAO() {
        this.conexion = new Conexion();
    }
    
    public boolean insertar(Proveedor proveedor) {
        String sql = "INSERT INTO proveedores (`Nombre_Proveedor`, `NIT`, `Nombre_Contacto`, `NCelular`, `Email`) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.getConnection().prepareStatement(sql)) {
            ps.setString(1, proveedor.getNombreProveedor());
            ps.setInt(2, proveedor.getNit());
            ps.setString(3, proveedor.getNombreContacto());
            ps.setString(4, proveedor.getNCelular());
            ps.setString(5, proveedor.getEmail());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("✗ Error al insertar proveedor: " + e.getMessage());
            return false;
        }
    }
    
    public List<Proveedor> consultarTodos() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedores ORDER BY `Nombre_Proveedor` ASC";
        try (Statement stmt = conexion.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("IdProveedor"));
                proveedor.setNombreProveedor(rs.getString("Nombre_Proveedor"));
                proveedor.setNit(rs.getInt("NIT"));
                proveedor.setNombreContacto(rs.getString("Nombre_Contacto"));
                proveedor.setNCelular(rs.getString("NCelular"));
                proveedor.setEmail(rs.getString("Email"));
                lista.add(proveedor);
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al consultar proveedores: " + e.getMessage());
        }
        return lista;
    }
    
    public Proveedor consultarPorId(int id) {
        String sql = "SELECT * FROM proveedores WHERE `IdProveedor` = ?";
        try (PreparedStatement ps = conexion.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("IdProveedor"));
                proveedor.setNombreProveedor(rs.getString("Nombre_Proveedor"));
                proveedor.setNit(rs.getInt("NIT"));
                proveedor.setNombreContacto(rs.getString("Nombre_Contacto"));
                proveedor.setNCelular(rs.getString("NCelular"));
                proveedor.setEmail(rs.getString("Email"));
                return proveedor;
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al consultar proveedor: " + e.getMessage());
        }
        return null;
    }
    
    public boolean actualizar(Proveedor proveedor) {
        String sql = "UPDATE proveedores SET `Nombre_Proveedor`=?, `NIT`=?, `Nombre_Contacto`=?, `NCelular`=?, `Email`=? WHERE `IdProveedor`=?";
        try (PreparedStatement ps = conexion.getConnection().prepareStatement(sql)) {
            ps.setString(1, proveedor.getNombreProveedor());
            ps.setInt(2, proveedor.getNit());
            ps.setString(3, proveedor.getNombreContacto());
            ps.setString(4, proveedor.getNCelular());
            ps.setString(5, proveedor.getEmail());
            ps.setInt(6, proveedor.getIdProveedor());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("✗ Error al actualizar proveedor: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminar(int id) {
        String sql = "DELETE FROM proveedores WHERE `IdProveedor` = ?";
        try (PreparedStatement ps = conexion.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("✗ Error al eliminar proveedor: " + e.getMessage());
            return false;
        }
    }
    
    public void cerrar() {
        conexion.cerrarConexion();
    }
}