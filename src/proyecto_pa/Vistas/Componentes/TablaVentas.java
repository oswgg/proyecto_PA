package proyecto_pa.Vistas.Componentes;

import proyecto_pa.Modelos.Producto;
import proyecto_pa.Servicios.LeerJson;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TablaVentas extends JTable {
   DefaultTableModel modeloTabla = new DefaultTableModel();
   JTable tablaProductos;

   public TablaVentas() {
      modeloTabla.addColumn("ID");
      modeloTabla.addColumn("Nombre");
      modeloTabla.addColumn("Precio");
      modeloTabla.addColumn("Cantidad");
      modeloTabla.addColumn("Existencia");

      tablaProductos = new JTable(modeloTabla);
      tablaProductos.setPreferredScrollableViewportSize(new Dimension(800, 150)); // Ancho x Alto

      // Llena la tabla de los productos guardados
      this.refreshData();
   }

   public JTable getTable() {
      return tablaProductos;
   }

   public DefaultTableModel getModelo() {
      return modeloTabla;
   }

   public void refreshData() {
      modeloTabla.setRowCount(0);
      ArrayList<Producto> productos = new LeerJson<Producto>().getDatos("venta.json");

      productos.forEach(producto -> {
         Object[] item = {producto.getIdProducto(), producto.getNombreProducto(), producto.getPrecio(), producto.getCantidad(), producto.getExistencia()};

         modeloTabla.addRow(item);
      });

   }
}
