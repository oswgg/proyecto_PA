package Vistas.Componentes.Categorias;

import Controladores.CategoriaController;
import Modelos.Categoria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ActionEliminarCategoria extends JPanel implements ActionListener {
   CategoriaController categoController = new CategoriaController();
   TableCategorias categoriasModelo = new TableCategorias();
   DefaultTableModel modeloTabla = categoriasModelo.getModelo();
   JTable tablaCategorias = categoriasModelo.getTabla();
   JButton btn_eliminar;

   public ActionEliminarCategoria() {
      GridBagLayout gbl_contentPane = new GridBagLayout();
      gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
      gbl_contentPane.rowHeights = new int[]{0, 0, 0, 54, 0, 0};
      gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
      gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
      setLayout(gbl_contentPane);

      JScrollPane jScroll = new JScrollPane(tablaCategorias);
      GridBagConstraints gbc_jScroll = new GridBagConstraints();
      gbc_jScroll.gridx = 1;
      gbc_jScroll.gridy = 1;
      add(jScroll, gbc_jScroll);

      btn_eliminar = new JButton("Eliminar");
      GridBagConstraints gbc_btn_agregar = new GridBagConstraints();
      gbc_btn_agregar.insets = new Insets(0, 0, 0, 5);
      gbc_btn_agregar.gridx = 1;
      gbc_btn_agregar.gridy = 5;
      add(btn_eliminar, gbc_btn_agregar);

      btn_eliminar.addActionListener(this);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      ArrayList<Categoria> categorias = categoController.obtenerDatos();
      int selectedItem = tablaCategorias.getSelectedRow();

      if(selectedItem == -1)
         return;

      int id = categorias.get(selectedItem).getIdCategoria();

      boolean done = categoController.eliminar(id);


      if(done) {
         modeloTabla.removeRow(selectedItem);
      }
   }
}
