<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sena.modelo.Usuario" %>

<!DOCTYPE html>
<html>
<head> 
    <meta charset="UTF-8">
    <title>Lista de Usuarios</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        table { width: 100%; border-collapse: collapse; margin: 20px 0; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background: #667eea; color: white; }
        tr:hover { background: #f5f5f5; }
        a { color: #007bff; text-decoration: none; margin: 0 5px; }
        a:hover { text-decoration: underline; }
        .btn-new { 
            display: inline-block; 
            padding: 10px 20px; 
            background: #28a745; 
            color: white; 
            text-decoration: none; 
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .btn-delete { color: #dc3545; }
        .btn-edit { color: #007bff; }
    </style>
</head>
<body>

    <h2>📋 Usuarios</h2>

    <a href="<%= request.getContextPath() %>/web/usuario/registrar.jsp" class="btn-new">➕ Nuevo Usuario</a>

    <table>
        <tr>
            <th>ID</th>
            <th>Usuario</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Email</th>
            <th>Celular</th>
            <th>Rol</th>
            <th>Acciones</th>
        </tr>

        <%
            List<Usuario> lista = (List<Usuario>) request.getAttribute("usuarios");

            if(lista != null){
            for(Usuario u : lista){
        %>

        <tr>
            <td><%= u.getIdUsuario() %></td>
            <td><%= u.getUsuario() %></td>
            <td><%= u.getNombre() %></td>
            <td><%= u.getApellido() %></td>
            <td><%= u.getEmail() %></td>
            <td><%= u.getNCelular() %></td>
            <td><%= u.getRol() %></td>
            <td>
                <a href="<%= request.getContextPath() %>/web/usuario/editar.jsp?id=<%= u.getIdUsuario() %>" class="btn-edit">✏️ Editar</a>
                <a href="<%= request.getContextPath() %>/UsuarioServlet?accion=eliminar&id=<%= u.getIdUsuario() %>" 
                    class="btn-delete"
                    onclick="return confirm('¿Seguro que desea eliminar este proveedor?')">🗑️ Eliminar</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
            <tr>
                <td colspan="8" style="text-align:center;"><em>No hay usuarios registrados</em></td>
            </tr>
        <%
        }
        %>

    </table>

    <br>
    <a href="${pageContext.request.contextPath}/index.jsp">🏠 Inicio</a>

</body>
</html>