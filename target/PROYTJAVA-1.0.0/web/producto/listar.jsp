<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sena.modelo.Producto" %>

<html>
<head>
    <title>Productos</title>
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

    <h2>📦 Lista de Productos</h2>

    <a href="ProductoServlet?accion=nuevo" class="btn-new">
        ➕ Nuevo Producto
    </a>

    <table>
        <tr>
            <th>Proveedor</th>
            <th>IDProducto</th>
            <th>Nombre</th>
            <th>CodSKU</th>
            <th>Precio</th>
            <th>Stock</th>
            <th>Fecha Vencimiento</th>
            <th>Descripcion</th>
            <th>Acciones</th>
        </tr>

        <%
        List<Producto> lista = (List<Producto>) request.getAttribute("productos");

        if(lista != null && !lista.isEmpty()){
            for(Producto p : lista){
        %> 
        <tr>
            <td><%= p.getNombreProveedor() %></td> 
            <td><%= p.getIdProducto() %></td> 
            <td><%= p.getNombreProducto() %></td>     
            <td><%= p.getCodigoSKU() %></td>      
            <td><%= p.getPrecioUnitario() %></td>            
            <td><%= p.getStock() %></td>    
            <td><%= p.getFechaVencimiento() %></td>           
            <td><%= p.getDescripcion() %></td>
            <td>
                <a href="<%= request.getContextPath() %>/web/producto/editar.jsp?id=<%= p.getIdProducto() %>" class="btn-edit">✏️ Editar</a>
                <a href="<%= request.getContextPath() %>/ProductoServlet?accion=eliminar&id=<%= p.getIdProducto() %>" 
                   class="btn-delete"
                   onclick="return confirm('¿Seguro que desea eliminar este producto?')">🗑️ Eliminar</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
            <tr>
                <td colspan="8" style="text-align:center;"><em>No hay Productos registrados</em></td>
            </tr>
        <%
        }
        %>

    </table>

    <a href="<%= request.getContextPath() %>/index.jsp">🏠 Volver</a>

</body>
</html>