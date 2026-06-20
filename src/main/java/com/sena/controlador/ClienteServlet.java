package com.sena.controlador;

import com.sena.dao.ClienteDAO;
import com.sena.modelo.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {

    ClienteDAO dao = new ClienteDAO();

    // 🔍 CONSULTAR Y ELIMINAR
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion != null && accion.equals("eliminar")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(id);
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        List<Cliente> lista = dao.listar();
        request.setAttribute("clientes", lista);

        request.getRequestDispatcher("/web/cliente/listar.jsp").forward(request, response);
    }

    // ➕ INSERTAR / ✏️ ACTUALIZAR
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion"); 

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String cedula = request.getParameter("cedula");
        String nCelular = request.getParameter("nCelular");
        String direccion = request.getParameter("direccion");

        Cliente c = new Cliente();
        c.setNombre(nombre);
        c.setApellido(apellido);
        c.setEmail(email);
        c.setCedula(cedula);
        c.setNCelular(nCelular);
        c.setDireccion(direccion);

        try {
            if (accion.equals("insertar")) {
                dao.insertar(c);
            } else if (accion.equals("actualizar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                c.setIdCliente(id);
                dao.actualizar(c);
            }
        } catch(Exception e){
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
        }

        response.sendRedirect("ClienteServlet");
    }
}