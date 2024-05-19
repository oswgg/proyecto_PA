package proyecto_pa.Vistas.Componentes.Tickets;

import proyecto_pa.Controladores.VentaController;
import proyecto_pa.Modelos.Producto;
import proyecto_pa.Modelos.Venta;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TableTickets {

   VentaController ventaController = new VentaController();
   DefaultTableModel modeloTabla = new DefaultTableModel();
   JTable tablaTickets;

   public TableTickets() {
      modeloTabla.addColumn("ID");
      modeloTabla.addColumn("Total");
      modeloTabla.addColumn("Productos");

      tablaTickets = new JTable(modeloTabla);
      tablaTickets.setPreferredScrollableViewportSize(new Dimension(800, 150)); // Ancho x Alto

      ArrayList<Venta> ventas = ventaController.obtenerDatos();

      // Llena la tabla de los productos guardados
      ventas.forEach(venta -> {
         StringBuilder productosField = new StringBuilder();

         ArrayList<Producto> productos = venta.getProductosVendidos();
         for(Producto item : productos)
            productosField.append(item.getNombreProducto()).append("-> ").append(item.getCantidad()).append(" ");

         Object[] item = {venta.getId(), venta.getTotal(), productosField};
         modeloTabla.addRow(item);
      });

   }

   public JTable getTabla() {
      return tablaTickets;
   }

   public DefaultTableModel getModelo() {
      return modeloTabla;
   }
}
