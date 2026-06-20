<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head> 
    <meta charset="UTF-8">
    <title>Registrar Cliente</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        form { max-width: 400px; }
        label { display: block; margin: 10px 0 5px; }
        input { width: 100%; padding: 8px; margin-bottom: 15px; }
        button { padding: 10px 20px; background: #28a745; color: white; border: none; cursor: pointer; }
        button:hover { background: #218838; }
        a { color: #007bff; }
    </style>
</head>
<body>
    <h2>➕ Registrar Nuevo Cliente</h2>
    
    <form action="${pageContext.request.contextPath}/ClienteServlet" method="POST">
        <input type="hidden" name="accion" value="insertar">
        
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required>

        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" required>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        
        <label for="cedula">Cédula:</label>
        <input type="text" id="cedula" name="cedula"  pattern="[0-9]+" title="Solo se permiten números" required>
        
        <label for="nCelular">Celular:</label>
        <input type="text" id="nCelular" name="nCelular" pattern="[0-9]+" required>

        <label for="direccion">Dirección:</label>
        <input type="text" id="direccion" name="direccion" required>
        
        <button type="submit">💾 Guardar Cliente</button>
    </form>
    
    <br>
    <a href="${pageContext.request.contextPath}/ClienteServlet">← Volver a la lista</a>
    <br>
    <a href="${pageContext.request.contextPath}/index.jsp">🏠 Inicio</a>
</body>
</html>