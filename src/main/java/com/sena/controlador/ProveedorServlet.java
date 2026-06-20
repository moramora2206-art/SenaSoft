package com.sena.controlador;

import com.sena.dao.ProveedorDAO;
import com.sena.modelo.Proveedor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ProveedorServlet")
public class ProveedorServlet extends HttpServlet {

    ProveedorDAO dao = new ProveedorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";  // 👈 Valor por defecto

        switch (accion) {
            
            // ✅ CASO "nuevo": Mostrar formulario de registro
            case "nuevo":
                System.out.println("📝 Acción: mostrar formulario de registro");
                request.getRequestDispatcher("web/proveedor/registrar.jsp")
                       .forward(request, response);
                break;
                
            // ✅ CASO "eliminar": Eliminar proveedor
            case "eliminar":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(id);
                    System.out.println("✅ Proveedor eliminado: " + id);
                } catch (NumberFormatException e) {
                    System.err.println("❌ ID inválido para eliminar");
                }
                response.sendRedirect("ProveedorServlet");
                break;
                
            // ✅ CASO "listar" (default): Mostrar lista de proveedores
            case "listar":
            default:
                List<Proveedor> lista = dao.listar();
                request.setAttribute("proveedores", lista);
                System.out.println("📋 Listando " + lista.size() + " proveedores");
                request.getRequestDispatcher("web/proveedor/listar.jsp")
                       .forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        
        if (accion == null) {
            response.sendRedirect("ProveedorServlet");
            return;
        }

        String nombre = request.getParameter("nombreProveedor");
        String telefono = request.getParameter("nCelular"); 
        String email = request.getParameter("email");
        String contacto = request.getParameter("nombreContacto");
        String nit = request.getParameter("nit");

        Proveedor p = new Proveedor();
        p.setNombreProveedor(nombre);
        p.setNCelular(telefono);
        p.setEmail(email);
        p.setNombreContacto(contacto);
        try {
            p.setNit(Integer.parseInt(nit));
        } catch (NumberFormatException e) {
            p.setNit(0); // Valor por defecto si no es número
        }

        try {
            if ("insertar".equals(accion)) {
                dao.insertar(p);
                System.out.println("✅ Proveedor insertado: " + nombre);
                
            } else if ("actualizar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("id"));
                p.setIdProveedor(id);
                dao.actualizar(p);
                System.out.println("✅ Proveedor actualizado: " + id);
            }
            
            response.sendRedirect("ProveedorServlet");
            
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}