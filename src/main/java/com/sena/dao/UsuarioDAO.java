package com.sena.dao;

import com.sena.modelo.Conexion;
import com.sena.modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operaciones CRUD de usuarios + Login
 * Adaptado a BD: softwarefacturacion
 * @author [TU NOMBRE]
 * @version 1.0
 */
public class UsuarioDAO {
    
    private Conexion conexion;
    
    public UsuarioDAO() {
        this.conexion = new Conexion();
    }
    
    public boolean insertar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (`Usuario`, `Contraseña`, `Nombre`, `Apellido`, `Email`, `NCelular`, `Rol`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContraseña());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getApellido());
            ps.setString(5, usuario.getEmail());
            ps.setString(6, usuario.getNCelular());
            ps.setString(7, usuario.getRol());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("✗ Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }
    
    public List<Usuario> consultarTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios ORDER BY `Nombre` ASC";
        try (Statement stmt = conexion.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("IdUsuario"));
                usuario.setUsuario(rs.getString("Usuario"));
                usuario.setContraseña(rs.getString("Contraseña"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setApellido(rs.getString("Apellido"));
                usuario.setEmail(rs.getString("Email"));
                usuario.setNCelular(rs.getString("NCelular"));
                usuario.setRol(rs.getString("Rol"));
                lista.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al consultar usuarios: " + e.getMessage());
        }
        return lista;
    }
    
    public List<Usuario> listar() {
        return consultarTodos();
    }
    
    public Usuario validarLogin(String usuario, String contraseña) {
        String sql = "SELECT * FROM usuarios WHERE `Usuario` = ? AND `Contraseña` = ?";
        try (PreparedStatement ps = conexion.getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setString(2, contraseña);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario user = new Usuario();
                user.setIdUsuario(rs.getInt("IdUsuario"));
                user.setUsuario(rs.getString("Usuario"));
                user.setNombre(rs.getString("Nombre"));
                user.setApellido(rs.getString("Apellido"));
                user.setRol(rs.getString("Rol"));
                return user;
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al validar login: " + e.getMessage());
        }
        return null;
    }
    
    public Usuario consultarPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE `IdUsuario` = ?";
        try (PreparedStatement ps = conexion.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("IdUsuario"));
                usuario.setUsuario(rs.getString("Usuario"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setApellido(rs.getString("Apellido"));
                usuario.setEmail(rs.getString("Email"));
                usuario.setNCelular(rs.getString("NCelular"));
                usuario.setRol(rs.getString("Rol"));
                return usuario;
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al consultar usuario: " + e.getMessage());
        }
        return null;
    }
    
    public boolean actualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET `Usuario`=?, `Contraseña`=?, `Nombre`=?, `Apellido`=?, `Email`=?, `NCelular`=?, `Rol`=? WHERE `IdUsuario`=?";
        try (PreparedStatement ps = conexion.getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContraseña());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getApellido());
            ps.setString(5, usuario.getEmail());
            ps.setString(6, usuario.getNCelular());
            ps.setString(7, usuario.getRol());
            ps.setInt(8, usuario.getIdUsuario());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("✗ Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE `IdUsuario` = ?";
        try (PreparedStatement ps = conexion.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("✗ Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }
    
    public void cerrar() {
        conexion.cerrarConexion();
    }
}