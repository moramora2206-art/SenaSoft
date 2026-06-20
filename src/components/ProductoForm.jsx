import React, { useState } from "react";

function ProductoForm({ onGuardado }) {

    const [producto, setProducto] = useState({
        nombre: "",
        precio: ""
    });

    const handleChange = (e) => {
        setProducto({
            ...producto,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        fetch("http://localhost:8080/api/productos", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(producto)
        })
        .then(() => {
            alert("Producto guardado");
            onGuardado(); // refresca lista
        });
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Registrar Producto</h2>

            <input name="nombre" placeholder="Nombre" onChange={handleChange} />
            <input name="precio" type="number" placeholder="Precio" onChange={handleChange} />

            <button type="submit">Guardar</button>
        </form>
    );
}

export default ProductoForm;