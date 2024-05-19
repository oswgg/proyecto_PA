package proyecto_pa.Vistas.Componentes.Proveedores;

import proyecto_pa.Controladores.CategoriaController;
import proyecto_pa.Controladores.ProveedorController;
import proyecto_pa.Modelos.Categoria;
import proyecto_pa.Modelos.Proveedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ActionEliminarProveedor extends JPanel implements ActionListener {
   ProveedorController proveedorController = new ProveedorController();
   TableCategorias proveedorModelo = new TableCategorias();
   DefaultTableModel modeloTabla = proveedorModelo.getModelo();
   JTable tableProveedores = proveedorModelo.getTabla();
   JButton btn_eliminar;

   public ActionEliminarProveedor() {
      GridBagLayout gbl_contentPane = new GridBagLayout();
      gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
      gbl_contentPane.rowHeights = new int[]{0, 0, 0, 54, 0, 0};
      gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
      gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
      setLayout(gbl_contentPane);

      JScrollPane jScroll = new JScrollPane(tableProveedores);
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
      ArrayList<Proveedor> proveedores = proveedorController.obtenerDatos();
      int selectedItem = tableProveedores.getSelectedRow();

      if(selectedItem == -1)
         return;


      int id = proveedores.get(selectedItem).getId();

      boolean done = proveedorController.eliminar(id);


      if(done) {
         modeloTabla.removeRow(selectedItem);
      }
   }
}
