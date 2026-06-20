<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.sena.modelo.Producto" %>
<%@ page import="com.sena.dao.ProductoDAO" %>
<%@ page import="com.sena.dao.ProveedorDAO" %>
<%@ page import="com.sena.modelo.Proveedor" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Producto</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        form { max-width: 400px; }
        label { display: block; margin: 10px 0 5px; }
        input, select { width: 100%; padding: 8px; margin-bottom: 15px; }
        button { padding: 10px 20px; background: #28a745; color: white; border: none; cursor: pointer; }
        button:hover { background: #218838; }
        a { color: #007bff; }
    </style>
</head>
<body>

    <h2>✏️ Editar Producto</h2>

    <%
    String idStr = request.getParameter("id");
    Producto producto = null;
    List<Proveedor> proveedores = null;

    if(idStr != null){
        try {
            int id = Integer.parseInt(idStr);

            ProductoDAO dao = new ProductoDAO();
            producto = dao.consultarPorId(id);

            ProveedorDAO proveedorDAO = new ProveedorDAO();
            proveedores = proveedorDAO.listar();

        } catch(Exception e){
            out.println("<p style='color:red'>Error: " + e.getMessage() + "</p>");
        }
    }

    if(producto != null){
    %>

    <form action="${pageContext.request.contextPath}/ProductoServlet" method="POST">

        <input type="hidden" name="accion" value="actualizar">
        <input type="hidden" name="id" value="<%= producto.getIdProducto() %>">

        <label for="idProveedor">Proveedor:</label>
        <select id="idProveedor" name="idProveedor" required>
            <option value="">Seleccione...</option>
            <%
            if(proveedores != null){
                for(Proveedor pr : proveedores){
                    String selected = (pr.getIdProveedor() == producto.getIdProveedor()) ? "selected" : "";
            %>
                <option value="<%= pr.getIdProveedor() %>" <%= selected %>>
                    <%= pr.getNombreProveedor() %>
                </option>
            <%
                }
            }
            %>
        </select>

        <label>Nombre:</label>
        <input type="text" name="nombre" value="<%= producto.getNombreProducto() %>" required>

        <label>Codigo SKU:</label>
        <input type="text" name="codigoSku" value="<%= producto.getCodigoSKU() %>" required>

        <label>Precio:</label>
        <input type="number" step="0.01" name="precio" value="<%= producto.getPrecioUnitario() %>" required>

        <label>Stock:</label>
        <input type="number" name="stock" value="<%= producto.getStock() %>" required>

        <label>Fecha Vencimiento:</label>
        <input type="date" name="fechaVencimiento"
            value="<%= producto.getFechaVencimiento() != null ? producto.getFechaVencimiento().toString() : "" %>" required>

        <label>Descripción:</label>
        <input type="text" name="descripcion" value="<%= producto.getDescripcion() %>" required>

        <button type="submit">💾 Actualizar Producto</button>

    </form>

    <%
    } else {
        out.println("<p style='color:red'>Producto no encontrado</p>");
    }
    %>

    <br>
    <a href="${pageContext.request.contextPath}/ProductoServlet">← Volver a la lista</a>
    <br>
    <a href="${pageContext.request.contextPath}/index.jsp">🏠 Inicio</a>

</body>
</html>