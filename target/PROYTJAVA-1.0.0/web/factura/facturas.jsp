<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sena.modelo.Factura" %>

<html>

<head>
    <title>Facturas</title>
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
    <h2>📋 Lista de Facturas</h2>

    <a href="FacturaServlet?accion=nuevo" class="btn-new">➕ Nueva Factura</a> 

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Cliente</th>
            <th>Fecha</th>
            <th>Total</th>
        </tr>

    <%
        List<Factura> lista = (List<Factura>) request.getAttribute("lista");

        for (Factura f : lista) {
    %>
    <tr>
        <td><%= f.getIdFactura() %></td>
        <td><%= f.getNombreCliente() %></td>
        <td><%= f.getFechaVenta() %></td>
        <td><%= f.getTotal() %></td>
    </tr>
    <% } %>

    </table>
    
    <a href="<%= request.getContextPath() %>/index.jsp">🏠 Volver</a>

</body>
</html>