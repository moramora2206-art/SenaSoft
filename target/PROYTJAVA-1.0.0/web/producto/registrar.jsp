
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sena.modelo.Proveedor" %>

<html>
<head>
    <title>Registrar Producto</title>
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

    <h2>➕ Registrar Producto</h2>

    <form action="${pageContext.request.contextPath}/ProductoServlet" method="post">
        <input type="hidden" name="accion" value="insertar">

        <label for="idProveedor">Proveedor:</label>
        <select id="idProveedor" name="idProveedor" required>
            <option value="">Seleccione...</option>
            <%
                List<com.sena.modelo.Proveedor> proveedores =
                    (List<com.sena.modelo.Proveedor>) request.getAttribute("proveedores");

                if(proveedores != null){
                    for(com.sena.modelo.Proveedor pr : proveedores){
            %>
                <option value="<%= pr.getIdProveedor() %>">
                    <%= pr.getNombreProveedor() %>
                </option>
            <%
                    }
                }
            %>
        </select>

        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required>

        <label for="codigoSku">Codigo SKU:</label>
        <input type="text" id="codigoSku" name="codigoSku" required>

        <label for="precio">Precio:</label>
        <input type="number" id="precio" name="precio" pattern="[0-9]+" step="0.01" required>

        <label for="stock">Stock:</label>
        <input type="text" id="stock" name="stock" pattern="[0-9]+" required>

        <label for="fechaVencimiento">Fecha Vencimiento:</label>
        <input type="date" id="fechaVencimiento" name="fechaVencimiento" required>

        <label for="descripcion">Descripcion:</label>
        <input type="text" id="descripcion" name="descripcion" required>

        <button type="submit">💾 Guardar Producto</button>
    </form>

</body>
</html>