package com.sena.dao;

import com.sena.modelo.Cliente;
import com.sena.modelo.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operaciones CRUD de clientes
 * Adaptado a BD: softwarefacturacion
 * @author [TU NOMBRE]
 * @version 1.0
 */
public class ClienteDAO {
    
    private Conexion conexion;
    
    public ClienteDAO() {
        this.conexion = new Conexion();
    }
    
    public boolean insertar(Cliente cliente) {
        String sql = "INSERT INTO clientes (`Nombre`, `Apellido`, `Cédula`, `NCelular`, `Email`, `Dirección`) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.getConnection().prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getCedula());
            ps.setString(4, cliente.getNCelular());
            ps.setString(5, cliente.getEmail());
            ps.setString(6, cliente.getDireccion());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("✗ Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }
    
    public List<Cliente> consultarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes ORDER BY `Nombre` ASC";
        try (Statement stmt = conexion.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("IdCliente"));
                cliente.setNombre(rs.getString("Nombre"));
                cliente.setApellido(rs.getString("Apellido"));
                cliente.setCedula(rs.getString("Cédula"));
                cliente.setNCelular(rs.getString("NCelular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setDireccion(rs.getString("Dirección"));
                lista.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al consultar clientes: " + e.getMessage());
        }
        return lista;
    }
    
    public List<Cliente> listar() {
        return consultarTodos();
    }
    
    public Cliente consultarPorId(int id) {
        String sql = "SELECT * FROM clientes WHERE `IdCliente` = ?";
        try (PreparedStatement ps = conexion.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("IdCliente"));
                cliente.setNombre(rs.getString("Nombre"));
                cliente.setApellido(rs.getString("Apellido"));
                cliente.setCedula(rs.getString("Cédula"));
                cliente.setNCelular(rs.getString("NCelular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setDireccion(rs.getString("Dirección"));
                return cliente;
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al consultar cliente por ID: " + e.getMessage());
        }
        return null;
    }
    
    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET `Nombre`=?, `Apellido`=?, `Cédula`=?, `NCelular`=?, `Email`=?, `Dirección`=? WHERE `IdCliente`=?";
        try (PreparedStatement ps = conexion.getConnection().prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getCedula());
            ps.setString(4, cliente.getNCelular());
            ps.setString(5, cliente.getEmail());
            ps.setString(6, cliente.getDireccion());
            ps.setInt(7, cliente.getIdCliente());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("✗ Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminar(int id) {
        String sql = "DELETE FROM clientes WHERE `IdCliente` = ?";
        try (PreparedStatement ps = conexion.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("✗ Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }
    
    public void cerrar() {
        conexion.cerrarConexion();
    }
}