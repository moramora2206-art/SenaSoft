import React, { useEffect, useState } from "react";

function ProductoList({ onEditar }) {

    const [productos, setProductos] = useState([]);

    // Obtener productos del backend
    const obtenerProductos = () => {
        fetch("http://localhost:8080/api/productos")
            .then(res => res.json())
            .then(data => setProductos(data))
            .catch(error => console.error(error));
    };

    useEffect(() => {
        obtenerProductos();
    }, []);

    // Eliminar producto
    const eliminarProducto = (id) => {
        fetch(`http://localhost:8080/api/productos/${id}`, {
            method: "DELETE"
        })
        .then(() => obtenerProductos());
    };

    return (
        <div>
            <h2>Lista de Productos</h2>

            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Acciones</th>
                    </tr>
                </thead>

                <tbody>
                    {productos.map(p => (
                        <tr key={p.id}>
                            <td>{p.id}</td>
                            <td>{p.nombre}</td>
                            <td>{p.precio}</td>
                            <td>
                                <button onClick={() => onEditar(p)}>Editar</button>
                                <button onClick={() => eliminarProducto(p.id)}>Eliminar</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default ProductoList;