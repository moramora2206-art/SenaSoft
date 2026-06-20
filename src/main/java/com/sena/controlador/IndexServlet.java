package com.sena.controlador;

import com.sena.dao.ClienteDAO;
import com.sena.dao.ProductoDAO;
import com.sena.dao.FacturaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ClienteDAO clienteDAO = new ClienteDAO();
        ProductoDAO productoDAO = new ProductoDAO();
        FacturaDAO facturaDAO = new FacturaDAO();

        int totalClientes = clienteDAO.consultarTodos().size();
        int totalProductos = productoDAO.consultarTodos().size();
        int totalFacturas = facturaDAO.consultarTodas().size();

        request.setAttribute("totalClientes", totalClientes);
        request.setAttribute("totalProductos", totalProductos);
        request.setAttribute("totalFacturas", totalFacturas);

        request.setAttribute("facturas", facturaDAO.consultarTodas());

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}