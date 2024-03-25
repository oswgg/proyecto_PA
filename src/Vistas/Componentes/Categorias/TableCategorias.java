package Vistas.Componentes.Categorias;

import Controladores.CategoriaController;
import Modelos.Categoria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TableCategorias extends JTable {
   JTable tablaCategorias;
   CategoriaController categoriasController = new CategoriaController();
   DefaultTableModel modeloTabla = new DefaultTableModel();

   public TableCategorias() {
      modeloTabla.addColumn("ID");
      modeloTabla.addColumn("Nombre");

      tablaCategorias = new JTable(modeloTabla);
      tablaCategorias.setPreferredScrollableViewportSize(new Dimension(800, 150)); // Ancho x Alto

      JScrollPane scrollPane = new JScrollPane(tablaCategorias);

      ArrayList<Categoria> categorias = categoriasController.obtenerDatos();

      // Llena la tabla con las categorias guardadasa
      if(!categorias.isEmpty()) {
         categorias.forEach(producto -> {
            Object[] item = {producto.getIdCategoria(), producto.getCategoria()};

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
