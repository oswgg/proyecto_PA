package Vistas.Componentes;

import Controladores.ProductoController;
import Modelos.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TablaVentas extends JTable {

   ArrayList<Producto> productos = new ArrayList<>();

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
      productos.forEach(producto -> {
         Object[] item = {producto.getIdProducto(), producto.getNombreProducto(), producto.getPrecio(), producto.getCantidad(), producto.getExistencia()};

         modeloTabla.addRow(item);
      });

   }

   public JTable getTable() {
      return tablaProductos;
   }

   public DefaultTableModel getModelo() {
      return modeloTabla;
   }

   public void agregarProducto() {

   }
}
