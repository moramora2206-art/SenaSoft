package com.sena.controlador;

import com.sena.dao.ProductoDAO;
import com.sena.dao.ProveedorDAO;
import com.sena.modelo.Producto;
import com.sena.modelo.Proveedor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.math.BigDecimal;
import java.io.IOException;
import java.util.List;

@WebServlet({"/ProductoServlet", "/api/productos"})
public class ProductoServlet extends HttpServlet {

    ProductoDAO dao = new ProductoDAO();
    ProveedorDAO proveedorDAO = new ProveedorDAO();

    // 🔹 CORS
    private void setCorsHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }

    // ==========================
    // 🔹 GET (LISTAR / BUSCAR)
    // ==========================
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        setCorsHeaders(response);

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {

            case "buscar":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Producto p = dao.consultarPorId(id);

                    response.setContentType("application/json;charset=UTF-8");

                    if (p != null) {
                        String json = "{"
                                + "\"id\":" + p.getIdProducto() + ","
                                + "\"nombre\":\"" + p.getNombreProducto() + "\","
                                + "\"precio\":" + p.getPrecioUnitario()
                                + "}";

                        response.getWriter().write(json);
                    } else {
                        response.getWriter().write("{}");
                    }

                } catch (Exception e) {
                    response.getWriter().write("{}");
                }
                break;

            case "nuevo":
                List<Proveedor> proveedores = proveedorDAO.listar();
                request.setAttribute("proveedores", proveedores);
                request.getRequestDispatcher("web/producto/registrar.jsp").forward(request, response);
                break;

            case "eliminar":
                int id = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(id);
                response.sendRedirect("ProductoServlet");
                break;

            default: // 🔥 LISTAR PARA JSP Y REACT

                List<Producto> lista = dao.listar();

                String accept = request.getHeader("Accept");

                if (accept != null && accept.contains("application/json")) {

                    response.setContentType("application/json;charset=UTF-8");

                    StringBuilder json = new StringBuilder("[");
                    for (int i = 0; i < lista.size(); i++) {
                        Producto p = lista.get(i);

                        json.append("{")
                                .append("\"id\":").append(p.getIdProducto()).append(",")
                                .append("\"nombre\":\"").append(p.getNombreProducto()).append("\",")
                                .append("\"precio\":").append(p.getPrecioUnitario())
                                .append("}");

                        if (i < lista.size() - 1) json.append(",");
                    }
                    json.append("]");

                    response.getWriter().write(json.toString());

                } else {
                    request.setAttribute("productos", lista);
                    request.getRequestDispatcher("web/producto/listar.jsp").forward(request, response);
                }
                break;
        }
    }

    // ==========================
    // 🔹 POST (INSERTAR)
    // ==========================
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        setCorsHeaders(response);

        // 🔥 SI VIENE DESDE REACT (JSON)
        if (request.getContentType() != null && request.getContentType().contains("application/json")) {

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = request.getReader().readLine()) != null) {
                sb.append(line);
            }

            String json = sb.toString();

            String nombre = json.split("\"nombre\":\"")[1].split("\"")[0];
            String precioStr = json.split("\"precio\":")[1].split("}")[0];

            Producto p = new Producto();
            p.setNombreProducto(nombre);
            p.setPrecioUnitario(new BigDecimal(precioStr));

            dao.insertar(p);

            response.setContentType("application/json");
            response.getWriter().write("{\"mensaje\":\"ok\"}");
            return;
        }

        // 🔹 SI VIENE DESDE JSP
        String accion = request.getParameter("accion");

        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
        String codigoSku = request.getParameter("codigoSku");
        String nombre = request.getParameter("nombre");
        int stock = Integer.parseInt(request.getParameter("stock"));
        String descripcion = request.getParameter("descripcion");
        BigDecimal precio = new BigDecimal(request.getParameter("precio"));
        java.sql.Date fechaVencimiento = java.sql.Date.valueOf(request.getParameter("fechaVencimiento"));

        Producto p = new Producto();
        p.setIdProveedor(idProveedor);
        p.setCodigoSKU(codigoSku);
        p.setNombreProducto(nombre);
        p.setStock(stock);
        p.setPrecioUnitario(precio);
        p.setDescripcion(descripcion);
        p.setFechaVencimiento(fechaVencimiento);

        if ("insertar".equals(accion)) {
            dao.insertar(p);
        } else if ("actualizar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            p.setIdProducto(id);
            dao.actualizar(p);
        }

        response.sendRedirect("ProductoServlet");
    }

    // ==========================
    // 🔹 PUT (ACTUALIZAR REACT)
    // ==========================
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        setCorsHeaders(response);

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = request.getReader().readLine()) != null) {
            sb.append(line);
        }

        String json = sb.toString();

        int id = Integer.parseInt(json.split("\"id\":")[1].split(",")[0]);
        String nombre = json.split("\"nombre\":\"")[1].split("\"")[0];
        String precioStr = json.split("\"precio\":")[1].split("}")[0];

        Producto p = new Producto();
        p.setIdProducto(id);
        p.setNombreProducto(nombre);
        p.setPrecioUnitario(new BigDecimal(precioStr));

        dao.actualizar(p);

        response.getWriter().write("{\"mensaje\":\"actualizado\"}");
    }

    // ==========================
    // 🔹 DELETE (ELIMINAR REACT)
    // ==========================
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        setCorsHeaders(response);

        int id = Integer.parseInt(request.getParameter("id"));
        dao.eliminar(id);

        response.getWriter().write("{\"mensaje\":\"eliminado\"}");
    }
}