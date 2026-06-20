
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head> 
    <meta charset="UTF-8">
    <title>Registrar Cliente</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        form { max-width: 400px; }
        label, select { display: block; margin: 10px 0 5px; }
        input { width: 100%; padding: 8px; margin-bottom: 15px; }
        button { padding: 10px 20px; background: #28a745; color: white; border: none; cursor: pointer; }
        button:hover { background: #218838; }
        a { color: #007bff; }
    </style>
</head>

<body>

    <h2>➕ Registrar Nuevo Usuario</h2> 

    <form action="<%= request.getContextPath() %>/UsuarioServlet" method="post">
        <input type="hidden" name="accion" value="insertar">

        <label for="usuario">Usuario:</label>
        <input type="text" id="usuario" name="usuario" required>

        <label for="password">Contraseña:</label>
        <input type="password" id="password" name="password" required>

        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required>

        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="nCelular">Celular:</label>
        <input type="text" id="nCelular" name="ncelular" pattern="[0-9]+" required>

        Rol:<br>
        <select name="rol">
        <option>admin</option>
        <option>empleado</option>
        </select><br><br>

        <button type="submit">💾 Guardar</button>

    </form>

</body>
</html>