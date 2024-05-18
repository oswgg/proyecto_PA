package Vistas.Componentes.Productos;

import Controladores.ProductoController;
import Modelos.Categoria;
import Modelos.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ActionEliminarProducto extends JPanel implements ActionListener {
   ProductoController productosController = new ProductoController();
   TableProductos productosModelo = new TableProductos(false);
   DefaultTableModel modeloTabla = productosModelo.getModelo();
   JTable tablaProductos = productosModelo.getTable();
   JButton btnConfirmar = new JButton("Confirmar");
   public ActionEliminarProducto() {
      GridBagLayout gbl_contentPane = new GridBagLayout();
      gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
      gbl_contentPane.rowHeights = new int[]{0, 0, 0, 54, 0, 0};
      gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
      gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
      setLayout(gbl_contentPane);


      JScrollPane jScroll = new JScrollPane(tablaProductos);
      add(jScroll);

      GridBagConstraints gbc_buton = new GridBagConstraints();
      gbc_buton.gridy = 2;

      btnConfirmar.addActionListener(this);
      add(btnConfirmar, gbc_buton);

   }


   @Override
   public void actionPerformed(ActionEvent e) {

      ArrayList<Producto> productos = productosController.obtenerDatos();
      int selectedItem = tablaProductos.getSelectedRow();

      if(selectedItem == -1)
         return;

      int id = productos.get(selectedItem).getIdProducto();

      boolean done = productosController.eliminar(id);

      if(done) {
         modeloTabla.removeRow(selectedItem);
      }
   }
}
