package com.sena.controlador;

import com.sena.dao.UsuarioDAO;
import com.sena.modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

    UsuarioDAO dao = new UsuarioDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Usuario> lista = dao.listar();
        request.setAttribute("usuarios", lista);

        request.getRequestDispatcher("web/usuario/listar.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String ncelular = request.getParameter("ncelular");
        String rol = request.getParameter("rol");

        Usuario u = new Usuario();
        u.setUsuario(usuario);
        u.setContraseña(password);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setEmail(email);
        u.setNCelular(ncelular);
        u.setRol(rol);

        dao.insertar(u);

        response.sendRedirect("UsuarioServlet");
    }
}