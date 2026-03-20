package com.sena.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para establecer conexión con la base de datos MySQL
 * Sistema de Facturación - SENA
 * BD: softwarefacturacion
 * @author [TU NOMBRE]
 * @version 1.0
 */
public class Conexion {
    
    private static final String URL = "jdbc:mysql://localhost:3306/softwarefacturacion";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "Car*2011";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    private Connection conexion;
    
    public Conexion() {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("✓ Conexión exitosa a la base de datos");
        } catch (ClassNotFoundException e) {
            System.err.println("✗ Error: Driver no encontrado - " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("✗ Error: No se pudo conectar - " + e.getMessage());
        }
    }
    
    public Connection getConnection() {
        return conexion;
    }
    
    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("✓ Conexión cerrada");
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al cerrar conexión - " + e.getMessage());
        }
    }
}