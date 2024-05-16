package Vistas.Componentes.Productos;

import Controladores.CategoriaController;
import Controladores.ProductoController;
import Modelos.Categoria;
import Modelos.ComboItem;
import Modelos.Producto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ActionAgregarProducto extends JPanel implements ActionListener {
   ProductoController prodController = new ProductoController();
   JTextField txt_nombreProducto, txt_cantidad, txt_precio;
   JComboBox<ComboItem> cB_categoria;
   JButton btn_agregar;
   ArrayList<Categoria> categorias = new CategoriaController().obtenerDatos();

   public ActionAgregarProducto() {
      GridBagLayout gbl_contentPane = new GridBagLayout();
      gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
      gbl_contentPane.rowHeights = new int[]{0, 0, 0, 54, 0, 0};
      gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
      gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
      setLayout(gbl_contentPane);

      JLabel lblNewLabel = new JLabel("Nombre del producto");
      lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
      GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
      gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
      gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
      gbc_lblNewLabel.gridx = 1;
      gbc_lblNewLabel.gridy = 1;
      add(lblNewLabel, gbc_lblNewLabel);

      txt_nombreProducto = new JTextField();
      lblNewLabel.setLabelFor(txt_nombreProducto);
      GridBagConstraints gbc_txt_nombreProducto = new GridBagConstraints();
      gbc_txt_nombreProducto.insets = new Insets(0, 0, 5, 5);
      gbc_txt_nombreProducto.fill = GridBagConstraints.HORIZONTAL;
      gbc_txt_nombreProducto.gridx = 1;
      gbc_txt_nombreProducto.gridy = 2;
      add(txt_nombreProducto, gbc_txt_nombreProducto);
      txt_nombreProducto.setColumns(10);

      JPanel panel = new JPanel();
      GridBagConstraints gbc_panel = new GridBagConstraints();
      gbc_panel.anchor = GridBagConstraints.NORTH;
      gbc_panel.insets = new Insets(0, 0, 5, 5);
      gbc_panel.fill = GridBagConstraints.HORIZONTAL;
      gbc_panel.gridx = 1;
      gbc_panel.gridy = 3;
      add(panel, gbc_panel);
      GridBagLayout gbl_panel = new GridBagLayout();
      gbl_panel.columnWidths = new int[]{171, 0, 0};
      gbl_panel.rowHeights = new int[] {30, 30};
      gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
      gbl_panel.rowWeights = new double[]{0.0, 0.0};
      panel.setLayout(gbl_panel);

      JLabel lblNewLabel_1 = new JLabel("Cantidad");
      lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
      GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
      gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
      gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
      gbc_lblNewLabel_1.gridx = 0;
      gbc_lblNewLabel_1.gridy = 0;
      panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

      txt_precio = new JTextField();
      GridBagConstraints gbc_precio = new GridBagConstraints();
      gbc_precio.gridx = 1;
      gbc_precio.gridy = 1;
      panel.add(txt_precio, gbc_precio);
      txt_precio.setColumns(10);

      JLabel lbl_precio = new JLabel("Precio");
      lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
      GridBagConstraints gbc_lblprecio = new GridBagConstraints();
      gbc_lblprecio.anchor = GridBagConstraints.WEST;
      gbc_lblprecio.insets = new Insets(0, 0, 5, 5);
      gbc_lblprecio.gridx = 1;
      gbc_lblprecio.gridy = 0;
      panel.add(lbl_precio, gbc_lblprecio);


      JLabel lblCategoria = new JLabel("Categoria");
      GridBagConstraints gbc_lblCategoria = new GridBagConstraints();
      gbc_lblCategoria.insets = new Insets(0, 0, 5, 0);
      gbc_lblCategoria.gridx = 2;
      gbc_lblCategoria.gridy = 0;
      panel.add(lblCategoria, gbc_lblCategoria);

      txt_cantidad = new JTextField();
      GridBagConstraints gbc_textField = new GridBagConstraints();
      gbc_textField.fill = GridBagConstraints.HORIZONTAL;
      gbc_textField.insets = new Insets(0, 0, 5, 5);
      gbc_textField.gridx = 0;
      gbc_textField.gridy = 1;
      panel.add(txt_cantidad, gbc_textField);
      txt_cantidad.setColumns(10);

      cB_categoria = new JComboBox<>();
      GridBagConstraints gbc_cB_categoria = new GridBagConstraints();
      gbc_cB_categoria.insets = new Insets(0, 0, 5, 0);
      gbc_cB_categoria.fill = GridBagConstraints.HORIZONTAL;
      gbc_cB_categoria.gridx = 2;
      gbc_cB_categoria.gridy = 1;
      categorias.forEach((categoria -> {
         ComboItem categoriaCombo = new ComboItem(categoria.getIdCategoria(), categoria.getCategoria());

         cB_categoria.addItem(categoriaCombo);
      }));
      panel.add(cB_categoria, gbc_cB_categoria);

      btn_agregar = new JButton("Agregar");
      GridBagConstraints gbc_btn_agregar = new GridBagConstraints();
      gbc_btn_agregar.insets = new Insets(0, 0, 0, 5);
      gbc_btn_agregar.gridx = 1;
      gbc_btn_agregar.gridy = 4;
      add(btn_agregar, gbc_btn_agregar);

      btn_agregar.addActionListener(this);
   }

   // Guardar producto
   @Override
   public void actionPerformed(ActionEvent e) {
      // Obtiene todos los datos del producto de los cuadros de texto
      try {
         String nombreProducto = txt_nombreProducto.getText();
         int cantProducto = Integer.parseInt(txt_cantidad.getText());
         double precioProducto = Double.parseDouble(txt_precio.getText());

         // Obtiene el id de la categoria mediante el ComboItem
         Object selectedItem = cB_categoria.getSelectedItem();
         int idCategoriaProducto = ((ComboItem) selectedItem).getKey();

         int idProducto = (int) (Math.random() * 100);

         if(nombreProducto.isEmpty())
            throw new Error();
         // Guarda el producto
         Producto nuevo = new Producto(idProducto, nombreProducto, idCategoriaProducto, cantProducto, precioProducto);
         boolean done = prodController.agregar(nuevo);

         // Si se guarda los campos se vacian
         if (done) {
            txt_nombreProducto.setText("");
            txt_cantidad.setText("");
            txt_precio.setText("");
            cB_categoria.setSelectedIndex(0);
         }

      } catch (NumberFormatException err) {
         JOptionPane.showMessageDialog(null, "Por favor ingresa correctamente los datos");
      } catch (Error err) {
         JOptionPane.showMessageDialog(null, "Por favor ingresa correctamente los datos");

      }

   }
}