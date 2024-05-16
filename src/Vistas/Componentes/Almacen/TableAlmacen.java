package Vistas.Componentes.Almacen;

import Controladores.CategoriaController;
import Controladores.ProductoController;
import Controladores.VentaController;
import Modelos.Producto;
import Modelos.Venta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TableAlmacen {

   ProductoController productoController = new ProductoController();
   CategoriaController categoController = new CategoriaController();
   DefaultTableModel modeloTabla = new DefaultTableModel();
   JTable tablaTickets;

   public TableAlmacen() {
      modeloTabla.addColumn("ID");
      modeloTabla.addColumn("Total");
      modeloTabla.addColumn("Productos");

      tablaTickets = new JTable(modeloTabla);
      tablaTickets.setPreferredScrollableViewportSize(new Dimension(800, 150)); // Ancho x Alto

      ArrayList<Producto> productos = productoController.obtenerDatos();

      // Llena la tabla de los productos guardados
      productos.forEach(venta -> {
         StringBuilder productosField = new StringBuilder();

         productos.forEach(producto -> {
            String categoria = categoController.getById(producto.getIdCategoria()).getCategoria();
            Object[] item = {producto.getIdProducto(), producto.getNombreProducto(), producto.getExistencia(), categoria, producto.getPrecio()};

            modeloTabla.addRow(item);
         });

      });

   }

   public JTable getTabla() {
      return tablaTickets;
   }

   public DefaultTableModel getModelo() {
      return modeloTabla;
   }
}
