<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.sena.dao.*" %>
<%@ page import="com.sena.modelo.*" %>
<%@ page import="java.util.List" %>

<%
    // Verificar sesión
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inicio - Sistema de Facturación</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', Arial, sans-serif; background: #f5f5f5; }
        .header { background: linear-gradient(135deg, #39a900, #2d8a00); color: white; padding: 20px; display: flex; justify-content: space-between; align-items: center; }
        .header h1 { font-size: 24px; }
        .nav { background: #333; padding: 10px; text-align: center; }
        .nav a { color: white; text-decoration: none; padding: 10px 20px; display: inline-block; }
        .nav a:hover { background: #39a900; border-radius: 4px; }
        .container { max-width: 1200px; margin: 20px auto; padding: 20px; }
        .card { background: white; border-radius: 8px; padding: 20px; margin-bottom: 20px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        .card h2 { color: #39a900; border-bottom: 2px solid #39a900; padding-bottom: 10px; margin-bottom: 15px; }
        .stats { display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 20px; }
        .stat-card { background: linear-gradient(135deg, #39a900, #2d8a00); color: white; padding: 20px; border-radius: 8px; text-align: center; }
        .stat-card h3 { font-size: 36px; margin-bottom: 5px; }
        .btn { padding: 8px 15px; border: none; border-radius: 4px; cursor: pointer; text-decoration: none; display: inline-block; }
        .btn-primary { background: #39a900; color: white; }
        .btn-danger { background: #dc3545; color: white; }
    </style>
</head>
<body>
    <div class="header">
        <h1>🏪 Sistema de Facturación - SENA</h1>
        <div>
            <span>Bienvenido, <%= session.getAttribute("usuarioNombre") %></span>
            <a href="logout" class="btn btn-danger" style="margin-left: 10px;">Cerrar Sesión</a>
        </div>
    </div>
    
    <div class="nav">
        <a href="index.jsp">📊 Inicio</a>
        <a href="clientes.jsp">👥 Clientes</a>
        <a href="productos.jsp">📦 Productos</a>
        <a href="facturas.jsp">📄 Facturas</a>
        <a href="nueva_factura.jsp">🛒 Nueva Factura</a>
    </div>
    
    <div class="container">
        <div class="stats">
            <%
                ClienteDAO clienteDAO = new ClienteDAO();
                ProductoDAO productoDAO = new ProductoDAO();
                FacturaDAO facturaDAO = new FacturaDAO();
                
                int totalClientes = clienteDAO.consultarTodos().size();
                int totalProductos = productoDAO.consultarTodos().size();
                int totalFacturas = facturaDAO.consultarTodas().size();
            %>
            <div class="stat-card">
                <h3><%= totalClientes %></h3>
                <p>Clientes</p>
            </div>
            <div class="stat-card">
                <h3><%= totalProductos %></h3>
                <p>Productos</p>
            </div>
            <div class="stat-card">
                <h3><%= totalFacturas %></h3>
                <p>Facturas</p>
            </div>
        </div>
        
        <div class="card">
            <h2>📄 Últimas Facturas</h2>
            <table style="width: 100%; border-collapse: collapse; margin-top: 15px;">
                <tr style="background: #39a900; color: white;">
                    <th style="padding: 10px; text-align: left;">ID</th>
                    <th style="padding: 10px; text-align: left;">Cliente</th>
                    <th style="padding: 10px; text-align: left;">Fecha</th>
                    <th style="padding: 10px; text-align: left;">Total</th>
                </tr>
                <%
                    List<Factura> facturas = facturaDAO.consultarTodas();
                    int contador = 0;
                    for (Factura f : facturas) {
                        if (contador >= 5) break;
                        contador++;
                %>
                <tr style="border-bottom: 1px solid #ddd;">
                    <td style="padding: 10px;"><%= f.getIdFactura() %></td>
                    <td style="padding: 10px;"><%= f.getNombreCliente() %></td>
                    <td style="padding: 10px;"><%= f.getFechaVenta() %></td>
                    <td style="padding: 10px;">$ <%= f.getTotal() %></td>
                </tr>
                <% } %>
            </table>
        </div>
    </div>
    
    <%
        clienteDAO.cerrar();
        productoDAO.cerrar();
        facturaDAO.cerrar();
    %>
</body>
</html>