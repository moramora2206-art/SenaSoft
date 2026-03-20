package com.sena.main;

import com.sena.dao.*;
import com.sena.modelo.*;
/*import java.math.BigDecimal;
import java.util.ArrayList;*/
import java.util.List;

/**
 * Clase principal para probar las funcionalidades
 * Sistema de Facturación - SENA
 * @author [TU NOMBRE]
 * @version 1.0
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE FACTURACIÓN - SENA                    ║");
        System.out.println("║   Tecnólogo en Análisis y Desarrollo de Software   ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");
        
        // PRUEBA 1: Registrar cliente
        System.out.println("📋 PRUEBA 1: Registrar Cliente");
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = new Cliente("Carlos", "Rodríguez", "1233567890", "3001112233", "carlos@email.com", "Calle 10 #5-20");
        if (clienteDAO.insertar(cliente)) {
            System.out.println("✓ Cliente registrado correctamente\n");
        }
        
        // PRUEBA 2: Listar clientes
        System.out.println("📋 PRUEBA 2: Listar Clientes");
        List<Cliente> clientes = clienteDAO.consultarTodos();
        for (Cliente c : clientes) {
            System.out.println("   ID: " + c.getIdCliente() + " | " + c.getNombre() + " " + c.getApellido());
        }
        System.out.println();
        
        // PRUEBA 3: Listar productos
        System.out.println("📋 PRUEBA 3: Listar Productos");
        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> productos = productoDAO.consultarTodos();
        for (Producto p : productos) {
            System.out.println("   " + p.getCodigoSKU() + " | " + p.getNombreProducto() + " | $ " + p.getPrecioUnitario());
        }
        System.out.println();
        
        // PRUEBA 4: Validar Login
        System.out.println("📋 PRUEBA 4: Validar Login");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario userLogin = usuarioDAO.validarLogin("admin", "123456");
        if (userLogin != null) {
            System.out.println("✓ Login exitoso: " + userLogin.getNombre() + " " + userLogin.getApellido());
            System.out.println("  Rol: " + userLogin.getRol() + "\n");
        } else {
            System.out.println("✗ Login fallido - Inserte usuarios primero\n");
        }
        
        // PRUEBA 5: Listar proveedores
        System.out.println("📋 PRUEBA 5: Listar Proveedores");
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        List<Proveedor> proveedores = proveedorDAO.consultarTodos();
        for (Proveedor p : proveedores) {
            System.out.println("   " + p.getNombreProveedor() + " | NIT: " + p.getNit());
        }
        
        clienteDAO.cerrar();
        productoDAO.cerrar();
        usuarioDAO.cerrar();
        proveedorDAO.cerrar();
        
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║   PRUEBAS FINALIZADAS EXITOSAMENTE                 ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }
}