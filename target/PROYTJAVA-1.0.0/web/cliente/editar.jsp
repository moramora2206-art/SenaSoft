<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.sena.modelo.Cliente" %>
<%@ page import="com.sena.dao.ClienteDAO" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Cliente</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        form { max-width: 400px; }
        label { display: block; margin: 10px 0 5px; }
        input { width: 100%; padding: 8px; margin-bottom: 15px; }
        button { padding: 10px 20px; background: #007bff; color: white; border: none; cursor: pointer; }
        button:hover { background: #0056b3; }
        a { color: #007bff; }
    </style>
</head>
<body>
    <h2>✏️ Editar Cliente</h2>
    
    <%
    // Obtener el ID del cliente a editar
    String idStr = request.getParameter("id");
    Cliente cliente = null;
    
    if(idStr != null){
        try {
            int id = Integer.parseInt(idStr);
            ClienteDAO dao = new ClienteDAO();
            cliente = dao.consultarPorId(id);
        } catch(Exception e){
            out.println("<p style='color:red'>Error: " + e.getMessage() + "</p>");
        }
    }
    
    if(cliente != null){
    %>
    
    <form action="${pageContext.request.contextPath}/ClienteServlet" method="POST">
        <input type="hidden" name="accion" value="actualizar">
        <input type="hidden" name="id" value="<%= cliente.getIdCliente() %>">
        
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="<%= cliente.getNombre() %>" required>

        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" value="<%= cliente.getApellido() %>" required>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="<%= cliente.getEmail() %>" required>
        
        <label for="cedula">Cédula:</label>
        <input type="text" id="cedula" name="cedula" value="<%= cliente.getCedula() %>" required>
        
        <label for="nCelular">Celular:</label>
        <input type="text" id="nCelular" name="nCelular" value="<%= cliente.getNCelular() %>" required>

        <label for="direccion">Direccion:</label>
        <input type="text" id="direccion" name="direccion" value="<%= cliente.getDireccion() %>" required>

        <button type="submit">💾 Actualizar Cliente</button>
    </form>
    
    <%
    } else {
        out.println("<p style='color:red'>Cliente no encontrado</p>");
    }
    %> 
    
    <br>
    <a href="${pageContext.request.contextPath}/ClienteServlet">← Volver a la lista</a>
    <br>
    <a href="${pageContext.request.contextPath}/index.jsp">🏠 Inicio</a>
</body>
</html>