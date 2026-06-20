import React, { useState } from "react";
import ProductoForm from "../components/ProductoForm";
import ProductoList from "../components/ProductoList";
import ProductoEdit from "../components/ProductoEdit";

function Productos() {

    const [productoSeleccionado, setProductoSeleccionado] = useState(null);
    const [recargar, setRecargar] = useState(false);

    const refrescar = () => {
        setRecargar(!recargar);
        setProductoSeleccionado(null);
    };

    return (
        <div>
            <h1>Módulo de Productos</h1>

            <ProductoForm onGuardado={refrescar} />

            <ProductoEdit
                productoSeleccionado={productoSeleccionado}
                onActualizado={refrescar}
            />

            <ProductoList
                key={recargar}
                onEditar={setProductoSeleccionado}
            />
        </div>
    );
}

export default Productos;