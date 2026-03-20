<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login - Sistema de Facturación</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', Arial, sans-serif; background: linear-gradient(135deg, #39a900, #2d8a00); height: 100vh; display: flex; justify-content: center; align-items: center; }
        .login-container { background: white; padding: 40px; border-radius: 10px; box-shadow: 0 10px 25px rgba(0,0,0,0.2); width: 100%; max-width: 400px; }
        .login-header { text-align: center; margin-bottom: 30px; }
        .login-header h1 { color: #39a900; font-size: 24px; margin-bottom: 10px; }
        .form-group { margin-bottom: 20px; }
        .form-group label { display: block; margin-bottom: 8px; font-weight: bold; color: #333; }
        .form-group input { width: 100%; padding: 12px; border: 1px solid #ddd; border-radius: 5px; font-size: 14px; }
        .form-group input:focus { outline: none; border-color: #39a900; box-shadow: 0 0 5px rgba(57, 169, 0, 0.3); }
        .btn-login { width: 100%; padding: 12px; background: #39a900; color: white; border: none; border-radius: 5px; font-size: 16px; font-weight: bold; cursor: pointer; }
        .btn-login:hover { background: #2d8a00; }
        .alert { padding: 12px; border-radius: 5px; margin-bottom: 20px; text-align: center; }
        .alert-danger { background: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }
        .footer { text-align: center; margin-top: 20px; color: #666; font-size: 12px; }
    </style>
</head>
<body>
    <div class="login-container">
        <div class="login-header">
            <h1>🏪 Sistema de Facturación</h1>
            <p>SENA - Tecnólogo en ADS</p>
        </div>
        
        <%
            String mensaje = (String) request.getAttribute("mensaje");
            String tipoMensaje = (String) request.getAttribute("tipoMensaje");
            if (mensaje != null) {
        %>
        <div class="alert alert-<%= tipoMensaje %>"><%= mensaje %></div>
        <% } %>
        
        <form action="login" method="POST">
            <div class="form-group">
                <label for="usuario">👤 Usuario</label>
                <input type="text" id="usuario" name="usuario" placeholder="Ingrese su usuario" required autofocus>
            </div>
            <div class="form-group">
                <label for="contrasena">🔒 Contraseña</label>
                <input type="password" id="contrasena" name="contrasena" placeholder="Ingrese su contraseña" required>
            </div>
            <button type="submit" class="btn-login">🔐 Iniciar Sesión</button>
        </form>
        
        <div class="footer">
            <p>© 2026 SENA - Fase Ejecución</p>
            <p>Actividad AA2-EV02</p>
        </div>
    </div>
</body>
</html>