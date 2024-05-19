package proyecto_pa.Vistas.Componentes.Productos;

import proyecto_pa.Controladores.CategoriaController;
import proyecto_pa.Controladores.ProductoController;
import proyecto_pa.Modelos.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TableProductos extends JTable {

   ProductoController prodController = new ProductoController();
   DefaultTableModel modeloTabla = new DefaultTableModel();
   JTable tablaProductos;
   CategoriaController categoController = new CategoriaController();

   public TableProductos(boolean existentes) {
      modeloTabla.addColumn("ID");
      modeloTabla.addColumn("Nombre");
      modeloTabla.addColumn("Cantidad");
      modeloTabla.addColumn("Categoría");
      modeloTabla.addColumn("Precio");

      tablaProductos = new JTable(modeloTabla);
      tablaProductos.setPreferredScrollableViewportSize(new Dimension(800, 150)); // Ancho x Alto

      ArrayList<Producto> productos = existentes ? prodController.getExistentes()  : prodController.obtenerDatos();

      // Llena la tabla de los productos guardados
      productos.forEach(producto -> {
         String categoria = categoController.getById(producto.getIdCategoria()).getCategoria();
         Object[] item = {producto.getIdProducto(), producto.getNombreProducto(), producto.getExistencia(), categoria, producto.getPrecio()};

         modeloTabla.addRow(item);
      });

   }

   public JTable getTable() {
      return tablaProductos;
   }

   public DefaultTableModel getModelo() {
      return modeloTabla;
   }
}
