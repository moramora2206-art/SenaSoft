<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head> 
    <meta charset="UTF-8">
    <title>Sistema de Facturación - SENA</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 40px 20px;
        }
        .header {
            text-align: center;
            color: white;
            margin-bottom: 40px;
        }
        .header h1 {
            font-size: 2.5em;
            margin-bottom: 10px;
        }
        .header p {
            font-size: 1.2em;
            opacity: 0.9;
        }
        .menu {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
        }
        .card {
            background: white;
            border-radius: 10px;
            padding: 30px;
            text-align: center;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            transition: transform 0.3s;
        }
        .card:hover {
            transform: translateY(-5px);
        }
        .card h3 {
            color: #333;
            margin-bottom: 15px;
        }
        .card p {
            color: #666;
            margin-bottom: 20px;
        }
        .card a {
            display: inline-block;
            padding: 10px 25px;
            background: #667eea;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background 0.3s;
        }
        .card a:hover {
            background: #764ba2;
        }
        .icon {
            font-size: 3em;
            margin-bottom: 15px;
        }
        .footer {
            text-align: center;
            color: white;
            margin-top: 40px;
            opacity: 0.8;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>🏪 SISTEMA DE FACTURACIÓN</h1>
            <p>SENA - Tecnólogo en Análisis y Desarrollo de Software</p>
        </div>
        
        <div class="menu">
            <!-- Clientes -->
            <div class="card">
                <div class="icon">👥</div>
                <h3>Clientes</h3>
                <p>Gestionar clientes del sistema</p>
                <a href="ClienteServlet">Acceder</a>
            </div>
            
            <!-- Productos -->
            <div class="card">
                <div class="icon">📦</div>
                <h3>Productos</h3>
                <p>Administrar productos y precios</p>
                <a href="ProductoServlet">Acceder</a>
            </div>
            
            <!-- Facturas -->
            <div class="card">
                <div class="icon">📄</div>
                <h3>Facturas</h3>
                <p>Crear y consultar facturas</p>
                <a href="FacturaServlet">Acceder</a>
            </div>
            
            <!-- Usuarios -->
            <div class="card">
                <div class="icon">🔐</div>
                <h3>Usuarios</h3>
                <p>Administrar usuarios del sistema</p>
                <a href="UsuarioServlet">Acceder</a>
            </div>
            
            <!-- Proveedores -->
            <div class="card">
                <div class="icon">🚚</div>
                <h3>Proveedores</h3>
                <p>Gestionar proveedores</p>
                <a href="ProveedorServlet">Acceder</a>
            </div>
            
        </div>
        
        <div class="footer">
            <p>&copy; 2026 SENA - Todos los derechos reservados</p>
        </div>
    </div>
</body>
</html>