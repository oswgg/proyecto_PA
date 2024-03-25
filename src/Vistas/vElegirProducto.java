package Vistas;

import Vistas.Componentes.Productos.TableProductos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class vElegirProducto extends JFrame {
   TableProductos modeloProductos = new TableProductos();
   JTable tablaProds = modeloProductos.getTable();
   DefaultTableModel modeloTabla = modeloProductos.getModelo();
   JSpinner spinnerCant;
   public vElegirProducto() {
      setTitle("Ejemplo de ActionListener");
      setSize(600, 400);
      setLocation(850, 200);
      setVisible(true);

      GridBagLayout gbl_layout = new GridBagLayout();
      gbl_layout.columnWidths = new int[]{50, 0, 50};
      gbl_layout.rowHeights = new int[]{0, 0, 0};
      gbl_layout.columnWeights = new double[]{0.0, 1.0, 0.0};
      gbl_layout.rowWeights = new double[]{0.0, 1.0, 0.0};
      setLayout(gbl_layout);

      JScrollPane jScroll = new JScrollPane(tablaProds);
      GridBagConstraints bgc_scroll = new GridBagConstraints();
      bgc_scroll.fill = GridBagConstraints.BOTH;
      bgc_scroll.gridx = 1;
      bgc_scroll.gridy = 1;
      add(jScroll, bgc_scroll);

      JPanel panel = new JPanel();
      GridBagLayout gbl_panel = new GridBagLayout();
      gbl_panel.columnWidths = new int[]{0, 0};
      gbl_panel.rowHeights = new int[]{0};
      gbl_panel.columnWeights = new double[]{1.0, 1.0};
      gbl_panel.rowWeights = new double[]{1.0};
      panel.setLayout(gbl_panel);

      GridBagConstraints gbc_panel = new GridBagConstraints();
      gbc_panel.fill = GridBagConstraints.BOTH;
      gbc_panel.gridx = 1;
      gbc_panel.gridy = 2;
      add(panel, gbc_panel);

      spinnerCant = new JSpinner();
      GridBagConstraints bgc_spinner = new GridBagConstraints();
      bgc_spinner.fill = GridBagConstraints.BOTH;
      bgc_spinner.gridx = 0;
      bgc_spinner.gridy = 0;
      panel.add(spinnerCant, bgc_spinner);

      JButton btnAgregar = new JButton("Agregar");

   }
}
