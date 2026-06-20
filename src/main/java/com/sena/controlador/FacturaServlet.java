package com.sena.controlador;

import com.sena.dao.FacturaDAO;
import com.sena.modelo.DetalleFactura;
import com.sena.modelo.Factura;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/FacturaServlet")
public class FacturaServlet extends HttpServlet {

    private FacturaDAO facturaDAO;

    @Override
    public void init() {
        facturaDAO = new FacturaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) accion = "listar";

        switch (accion) {

            case "listar":
                request.setAttribute("lista", facturaDAO.consultarTodas());
                request.getRequestDispatcher("/web/factura/facturas.jsp").forward(request, response);
                break; 

            case "nuevo":
                request.getRequestDispatcher("/web/factura/crearFactura.jsp").forward(request, response);
                break;

            default:
                response.sendRedirect("FacturaServlet?accion=listar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("crear".equals(accion)) {

            try {

                Factura factura = new Factura(); 

                factura.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
                factura.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
                factura.setNombreCliente(request.getParameter("nombreCliente"));
                factura.setFechaVenta(new java.util.Date());
                factura.setFormaDePago(request.getParameter("formaPago"));
                factura.setDescuento(new BigDecimal(request.getParameter("descuento")));
                factura.setTotal(new BigDecimal(request.getParameter("total")));
                factura.setObservaciones(request.getParameter("observaciones"));

                // 🔥 DETALLES CORRECTOS
                List<DetalleFactura> detalles = new ArrayList<>();

                String[] productos = request.getParameterValues("producto");
                String[] cantidades = request.getParameterValues("cantidad");
                String[] precios = request.getParameterValues("precio");
                String[] subtotales = request.getParameterValues("subtotal");

                if (productos != null) {
                    for (int i = 0; i < productos.length; i++) {

                        DetalleFactura d = new DetalleFactura();
                        d.setIdProducto(Integer.parseInt(productos[i]));
                        d.setCantidad(Integer.parseInt(cantidades[i]));
                        d.setPrecioUnitario(new BigDecimal(precios[i]));
                        d.setSubtotal(new BigDecimal(subtotales[i]));

                        detalles.add(d);
                    }
                }

                // ✅ AQUÍ sí se asigna correctamente
                factura.setDetalles(detalles);

                // Enviamos ambos (por si tu DAO los usa separados)
                boolean exito = facturaDAO.crearFactura(factura, detalles);

                if (exito) {
                    response.sendRedirect("FacturaServlet?accion=listar");
                } else {
                    response.getWriter().println("Error al crear factura");
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Error: " + e.getMessage());
            }
        }
    }
}