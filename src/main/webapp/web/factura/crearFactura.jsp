<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Facturas</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        form { max-width: 900px; }
        label, select { display: block; margin: 10px 0 5px; }
        input[type="text"], input[type="number"] { 
            width: 100%; 
            padding: 8px; 
            margin-bottom: 15px; 
            box-sizing: border-box; 
        }
        button { 
            padding: 10px 20px; 
            background: #28a745; 
            color: white; 
            border: none; 
            cursor: pointer; 
            margin: 5px 0; 
        }
        button:hover { background: #218838; }
        .btn-eliminar {
            background: #dc3545;
            padding: 5px 15px;
        }
        .btn-eliminar:hover { background: #c82333; }
        
        table { 
            width: 100%; 
            border-collapse: collapse; 
            margin-top: 20px;
            background: white; 
        }
        table th, table td { 
            border: 1px solid #ddd; 
            padding: 12px; 
            text-align: left; 
        }
        table thead { 
            background: #333; 
            color: white; 
        }
        table tbody tr:hover {
            background-color: #f5f5f5;
            background: white;
        }
    </style>
</head>
<body>
    <h2>➕ Crear Factura</h2>

    <form action="${pageContext.request.contextPath}/FacturaServlet" method="post">
        
        <input type="hidden" name="accion" value="crear">

        <label for="idUsuario">Usuario ID:</label>
        <input type="text" id="idUsuario" name="idUsuario" required>

        <label for="idCliente">Cliente ID:</label>
        <input type="text" id="idCliente" name="idCliente" required>

        <label>Forma de Pago:</label>
        <select name="formaPago">
            <option>EFECTIVO</option>
            <option>TARJETA</option>
        </select>

        <label for="descuento">Descuento:</label>
        <input type="text" id="descuento" name="descuento" required>

        <label for="observaciones">Observaciones:</label>
        <input type="text" id="observaciones" name="observaciones" required>
        
        <h3>📋 Agregar Productos</h3>

        <input type="number" id="idProducto" placeholder="ID Producto">
        <button type="button" onclick="buscarProducto()">Buscar</button>

        <input type="text" id="nombre" placeholder="Nombre" readonly>
        <input type="number" id="precio" placeholder="Precio" readonly>

        <input type="number" id="cantidad" placeholder="Cantidad">

        <button type="button" onclick="agregarProducto()">➕ Agregar</button>

        <hr>

        <table id="tabla">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>Subtotal</th>
                    <th>Acción</th>
                </tr>
            </thead>
            <tbody id="cuerpoTabla"></tbody>
        </table>

        <h3>Total: $<span id="totalVista">0.00</span></h3>
        <input type="hidden" name="total" id="total">
        
        <!-- Inputs hidden para enviar los productos al servlet -->
        <div id="productosContainer"></div>

        <button type="submit">💾 Guardar Factura</button>

    </form>

    <script>
        let total = 0;
        let productos = [];

        function buscarProducto() {
            let id = document.getElementById("idProducto").value;

            fetch("${pageContext.request.contextPath}/ProductoServlet?accion=buscar&id=" + id)
                .then(res => res.json())
                .then(data => {
                    if (data.nombre) {
                        document.getElementById("nombre").value = data.nombre;
                        document.getElementById("precio").value = data.precio;
                    } else {
                        alert("Producto no encontrado");
                    }
                })
                .catch(error => {
                    console.error("Error:", error);
                    alert("Error al buscar el producto");
                });
        }

        function agregarProducto() {
            let id = document.getElementById("idProducto").value;
            let nombre = document.getElementById("nombre").value;
            let precio = parseFloat(document.getElementById("precio").value);
            let cantidad = parseInt(document.getElementById("cantidad").value);

            if (!id || !nombre || isNaN(precio) || isNaN(cantidad) || cantidad <= 0) {
                alert("Primero busque el producto y complete la cantidad correctamente");
                return;
            }

            let subtotal = precio * cantidad;
            
            productos.push({
                id: id,
                nombre: nombre,
                precio: precio,
                cantidad: cantidad,
                subtotal: subtotal
            });
            
            total += subtotal;
            actualizarTabla();
            actualizarTotal();
            actualizarInputsHidden();  // 👈 Esto es clave
            limpiarCampos();
        }

        function eliminarProducto(index) {
            total -= productos[index].subtotal;
            productos.splice(index, 1);
            actualizarTabla();
            actualizarTotal();
            actualizarInputsHidden();
        }

        function actualizarTabla() {
            let tbody = document.getElementById("cuerpoTabla");
            tbody.innerHTML = "";
            
            productos.forEach((prod, index) => {
                let fila = `
                    <tr>
                        <td>${prod.id}</td>
                        <td>${prod.nombre}</td>
                        <td>$${prod.precio.toFixed(2)}</td>
                        <td>${prod.cantidad}</td>
                        <td>$${prod.subtotal.toFixed(2)}</td>
                        <td><button type="button" class="btn-eliminar" onclick="eliminarProducto(${index})">Eliminar</button></td>
                    </tr>
                `;
                tbody.insertAdjacentHTML("beforeend", fila);
            });
        }

        function actualizarTotal() {
            document.getElementById("totalVista").innerText = total.toFixed(2);
            document.getElementById("total").value = total.toFixed(2);
        }

        // 🔥 IMPORTANTE: Los nombres deben coincidir con lo que espera FacturaServlet
        function actualizarInputsHidden() {
            let container = document.getElementById("productosContainer");
            container.innerHTML = "";
            
            productos.forEach((prod, index) => {
                container.innerHTML += `
                    <input type="hidden" name="producto[]" value="${prod.id}">
                    <input type="hidden" name="precio[]" value="${prod.precio}">
                    <input type="hidden" name="cantidad[]" value="${prod.cantidad}">
                    <input type="hidden" name="subtotal[]" value="${prod.subtotal}">
                `;
            });
        }

        function limpiarCampos() {  // ✅ Función cerrada correctamente
            document.getElementById("idProducto").value = "";
            document.getElementById("nombre").value = "";
            document.getElementById("precio").value = "";
            document.getElementById("cantidad").value = "";
        }
    </script>

</body>
</html>