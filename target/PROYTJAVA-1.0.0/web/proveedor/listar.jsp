<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sena.modelo.Proveedor" %>

<html>
<head>
    <title>Proveedores</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        h2 { color: #333; }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }

        th {
            background: #667eea;
            color: white;
        }

        tr:hover { background: #f5f5f5; }

        a {
            text-decoration: none;
            margin: 0 5px;
        }

        .btn-new {
            display: inline-block;
            padding: 10px 20px;
            background: #28a745;
            color: white;
            border-radius: 5px;
        }

        .btn-edit { color: #007bff; }
        .btn-delete { color: #dc3545; }

        input, select {
            padding: 8px;
            margin: 5px;
            width: 250px;
        }

        button {
            padding: 10px 15px;
            background: #667eea;
            color: white;
            border: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>

    <h2>🏢 Proveedores</h2>

    <a href="<%= request.getContextPath() %>/web/proveedor/registrar.jsp" class="btn-new">
        ➕ Nuevo Proveedor
    </a>

    <table>
        <tr>
            <th>ID</th>
            <th>Proveedor</th>
            <th>NIT</th>
            <th>Contacto</th>
            <th>Celular</th>
            <th>Email</th>
            <th>Acciones</th>
        </tr>

        <%
            List<Proveedor> lista = (List<Proveedor>) request.getAttribute("proveedores");

            if(lista != null){
            for(Proveedor p : lista){
        %>

        <tr>
            <td><%= p.getIdProveedor() %></td>
            <td><%= p.getNombreProveedor() %></td>
            <td><%= p.getNit() %></td>
            <td><%= p.getNombreContacto() %></td>
            <td><%= p.getNCelular() %></td>
            <td><%= p.getEmail() %></td>
            <td>
                <a href="<%= request.getContextPath() %>/web/proveedor/editar.jsp?id=<%= p.getIdProveedor() %>" class="btn-edit">✏️ Editar</a>
                <a href="<%= request.getContextPath() %>/ProveedorServlet?accion=eliminar&id=<%= p.getIdProveedor() %>" 
                    class="btn-delete"
                    onclick="return confirm('¿Seguro que desea eliminar este proveedor?')">🗑️ Eliminar</a>
            </td>
        </tr>

        
        <%
            }
        } else {
        %>
            <tr>
                <td colspan="6" style="text-align:center;"><em>No hay clientes registrados</em></td>
            </tr>
        <%
        }
        %>

    </table>

    <br>
    <a href="${pageContext.request.contextPath}/index.jsp">🏠 Inicio</a>

</body>
</html>