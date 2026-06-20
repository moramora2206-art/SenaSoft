<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Proveedor</title>
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

    <h2>🏢 Registrar Nuevo Proveedor</h2>
     
    <form action="${pageContext.request.contextPath}/ProveedorServlet" method="POST">
        
        <input type="hidden" name="accion" value="insertar">

        <label for="nombreProveedor">Nombre del Proveedor:</label>
        <input type="text" id="nombreProveedor" name="nombreProveedor" required> 

        <label for="nit">NIT:</label> 
        <input type="text" id="nit" name="nit" pattern="[0-9]+" required>

        <label for="nombreContacto">Nombre de Contacto:</label>
        <input type="text" id="nombreContacto" name="nombreContacto" required>

        <label for="nCelular">Celular:</label>
        <input type="text" id="nCelular" name="nCelular" pattern="[0-9]+" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email"  required>

        <button type="submit">💾 Guardar Proveedor</button>

    </form>

    <br>

    <a href="${pageContext.request.contextPath}/ProveedorServlet">← Volver a la lista</a>
    <br>
    <a href="${pageContext.request.contextPath}/index.jsp">🏠 Inicio</a>

</body>
</html>