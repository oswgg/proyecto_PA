package Vistas.Componentes.Proveedores;

import Controladores.CategoriaController;
import Controladores.ProveedorController;
import Modelos.Categoria;
import Modelos.Proveedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TableCategorias extends JTable {
   JTable tablaCategorias;
   ProveedorController provController = new ProveedorController();
   DefaultTableModel modeloTabla = new DefaultTableModel();

   public TableCategorias() {
      modeloTabla.addColumn("ID");
      modeloTabla.addColumn("Nombre");

      tablaCategorias = new JTable(modeloTabla);
      tablaCategorias.setPreferredScrollableViewportSize(new Dimension(800, 150)); // Ancho x Alto

      JScrollPane scrollPane = new JScrollPane(tablaCategorias);

      ArrayList<Proveedor> proveedores = provController.obtenerDatos();

      // Llena la tabla con las categorias guardadasa
      if(!proveedores.isEmpty()) {
         proveedores.forEach(prov -> {
            Object[] item = {prov.getId(), prov.getNombre()};

            modeloTabla.addRow(item);
         });

      }

      add(scrollPane);

   }

   public JTable getTabla(){
      return tablaCategorias;
   }

   public void actualizarTabla(Object[] item) {
      modeloTabla.addRow(item);
   }

   public DefaultTableModel getModelo() {
      return modeloTabla;
   }

}
