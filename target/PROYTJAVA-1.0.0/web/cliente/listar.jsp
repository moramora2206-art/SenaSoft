<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sena.modelo.Cliente" %>

<!DOCTYPE html>
<html>
<head> 
    <meta charset="UTF-8">
    <title>Lista de Clientes</title>
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
    <h2>📋 Clientes</h2>
    
    <a href="<%= request.getContextPath() %>/web/cliente/registrar.jsp" class="btn-new">➕ Nuevo Cliente</a>
    
    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Email</th>
            <th>Cédula</th>
            <th>Celular</th>
            <th>Dirección</th>
            <th>Acciones</th>
        </tr>
        
        <%
        List<Cliente> lista = (List<Cliente>) request.getAttribute("clientes");
        
        if(lista != null && !lista.isEmpty()){
            for(Cliente c : lista){
        %>
        
        <tr>
            <td><%= c.getIdCliente() %></td>
            <td><%= c.getNombre() %></td>
            <td><%= c.getApellido() %></td>
            <td><%= c.getEmail() %></td>
            <td><%= c.getCedula() %></td>
            <td><%= c.getNCelular() %></td>
            <td><%= c.getDireccion() %></td>
            <td>
                <a href="<%= request.getContextPath() %>/web/cliente/editar.jsp?id=<%= c.getIdCliente() %>" class="btn-edit">✏️ Editar</a>
                <a href="<%= request.getContextPath() %>/ClienteServlet?accion=eliminar&id=<%= c.getIdCliente() %>" 
                   class="btn-delete"
                   onclick="return confirm('¿Seguro que desea eliminar este cliente?')">🗑️ Eliminar</a>
            </td>
        </tr>
        
        <%
            }
        } else {
        %>
            <tr>
                <td colspan="8" style="text-align:center;"><em>No hay clientes registrados</em></td>
            </tr>
        <%
        }
        %>
        
    </table>
    
    <br>
    <a href="<%= request.getContextPath() %>/index.jsp">🏠 Volver al Inicio</a>
</body>
</html>