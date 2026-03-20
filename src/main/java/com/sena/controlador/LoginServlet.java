package com.sena.controlador;

import com.sena.dao.UsuarioDAO;
import com.sena.modelo.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String usuario = request.getParameter("usuario");
        String contraseña = request.getParameter("contrasena");
        
        if (usuario == null || usuario.trim().isEmpty() || 
            contraseña == null || contraseña.trim().isEmpty()) {
            request.setAttribute("mensaje", "Usuario y contraseña son obligatorios");
            request.setAttribute("tipoMensaje", "danger");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario user = usuarioDAO.validarLogin(usuario, contraseña);
        usuarioDAO.cerrar();
        
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", user);
            session.setAttribute("usuarioId", user.getIdUsuario());
            session.setAttribute("usuarioNombre", user.getNombre() + " " + user.getApellido());
            session.setAttribute("usuarioRol", user.getRol());
            session.setMaxInactiveInterval(1800);
            response.sendRedirect("index.jsp");
        } else {
            request.setAttribute("mensaje", "Usuario o contraseña incorrectos");
            request.setAttribute("tipoMensaje", "danger");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}