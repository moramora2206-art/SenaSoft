import React, { useState, useEffect } from "react";

function ProductoEdit({ productoSeleccionado, onActualizado }) {

    const [producto, setProducto] = useState(productoSeleccionado);

    useEffect(() => {
        setProducto(productoSeleccionado);
    }, [productoSeleccionado]);

    if (!producto) return null;

    const handleChange = (e) => {
        setProducto({
            ...producto,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        fetch(`http://localhost:8080/api/productos/${producto.id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(producto)
        })
        .then(() => {
            alert("Producto actualizado");
            onActualizado();
        });
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Editar Producto</h2>

            <input name="nombre" value={producto.nombre} onChange={handleChange} />
            <input name="precio" value={producto.precio} onChange={handleChange} />

            <button type="submit">Actualizar</button>
        </form>
    );
}

export default ProductoEdit;