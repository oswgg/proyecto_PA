package Vistas.Componentes.Proveedores;

import Controladores.ProveedorController;
import Modelos.Proveedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionAgregarProveedor extends JPanel implements ActionListener {
   ProveedorController proveedorController = new ProveedorController();
   JTextField txt_nombreProveedor;
   TableCategorias proveedoresModelo = new TableCategorias();
   DefaultTableModel modeloTabla = proveedoresModelo.getModelo();
   JTable tablaProveedores = proveedoresModelo.getTabla();
   JButton btn_agregar;
   public ActionAgregarProveedor() {
      GridBagLayout gbl_contentPane = new GridBagLayout();
      gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
      gbl_contentPane.rowHeights = new int[]{0, 0, 0, 54, 0, 0};
      gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
      gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
      setLayout(gbl_contentPane);

      JScrollPane jScroll = new JScrollPane(tablaProveedores);
      GridBagConstraints gbc_jScroll = new GridBagConstraints();
      gbc_jScroll.gridx = 1;
      gbc_jScroll.gridy = 1;
      add(jScroll, gbc_jScroll);

      JLabel lblNewLabel = new JLabel("Nombre de la categoría");
      lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
      GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
      gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
      gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
      gbc_lblNewLabel.gridx = 1;
      gbc_lblNewLabel.gridy = 2;
      add(lblNewLabel, gbc_lblNewLabel);

      txt_nombreProveedor = new JTextField();
      lblNewLabel.setLabelFor(txt_nombreProveedor);
      GridBagConstraints gbc_txt_nombreProducto = new GridBagConstraints();
      gbc_txt_nombreProducto.insets = new Insets(0, 0, 5, 5);
      gbc_txt_nombreProducto.fill = GridBagConstraints.HORIZONTAL;
      gbc_txt_nombreProducto.gridx = 1;
      gbc_txt_nombreProducto.gridy = 3;
      add(txt_nombreProveedor, gbc_txt_nombreProducto);
      txt_nombreProveedor.setColumns(10);

      JPanel panel = new JPanel();
      GridBagConstraints gbc_panel = new GridBagConstraints();
      gbc_panel.anchor = GridBagConstraints.NORTH;
      gbc_panel.insets = new Insets(0, 0, 5, 5);
      gbc_panel.fill = GridBagConstraints.HORIZONTAL;
      gbc_panel.gridx = 1;
      gbc_panel.gridy = 4;
      add(panel, gbc_panel);
      GridBagLayout gbl_panel = new GridBagLayout();
      gbl_panel.columnWidths = new int[]{171, 0, 0};
      gbl_panel.rowHeights = new int[] {30, 30};
      gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
      gbl_panel.rowWeights = new double[]{0.0, 0.0};
      panel.setLayout(gbl_panel);

      btn_agregar = new JButton("Agregar");
      GridBagConstraints gbc_btn_agregar = new GridBagConstraints();
      gbc_btn_agregar.insets = new Insets(0, 0, 0, 5);
      gbc_btn_agregar.gridx = 1;
      gbc_btn_agregar.gridy = 5;
      add(btn_agregar, gbc_btn_agregar);

      btn_agregar.addActionListener(this);
   }

   // Obtiene todos los datos de la categoria desde los inputs de texto
   @Override
   public void actionPerformed(ActionEvent e) {
     try {
        String nombreProveedor = txt_nombreProveedor.getText();
        int idCategoria = (int) (Math.random() * 100);

        if(nombreProveedor.isEmpty())
            throw new Error();


        Proveedor nuevo = new Proveedor(idCategoria, nombreProveedor); // Guarda la categoria

        boolean done = proveedorController.agregar(nuevo);

        // Si se guardo, actualiza la tabla y reinicia el input
        if (done) {
           txt_nombreProveedor.setText("");
           Object[] item = {idCategoria, nombreProveedor};
           // Actualiza campo por campo la fila del producto seleccionado
           modeloTabla.addRow(item);
        }
     } catch (Error err) {
        JOptionPane.showMessageDialog(null, "Por favor ingresa correctamente los datos");

     }

   }
}