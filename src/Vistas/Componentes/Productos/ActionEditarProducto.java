package Vistas.Componentes.Productos;

import Controladores.CategoriaController;
import Controladores.ProductoController;
import Controladores.ProveedorController;
import Modelos.Categoria;
import Modelos.ComboItem;
import Modelos.Producto;
import Modelos.Proveedor;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ActionEditarProducto extends JPanel implements ActionListener{
   TableProductos productoModelo = new TableProductos(false);
   DefaultTableModel modeloTabla = productoModelo.getModelo();
   JTable tablaProductos = productoModelo.getTable();
   ProductoController prodController = new ProductoController();
   CategoriaController categoController = new CategoriaController();
   ProveedorController provController = new ProveedorController();
   JTextField txt_nombre, txt_cantidad, txt_precio;
   JComboBox<ComboItem> combo = new JComboBox<>();
   JComboBox<ComboItem> cB_prov = new JComboBox<>();

   JButton confirmar;

   public ActionEditarProducto() {
      GridBagLayout gbc = new GridBagLayout();
      gbc.columnWidths = new int[]{0, 0, 0, 0};
      gbc.rowHeights = new int[]{0, 0, 0, 54, 0, 0};
      gbc.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
      gbc.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
      setLayout(gbc);

      JScrollPane jScroll = new JScrollPane(tablaProductos);
      GridBagConstraints gbc_jScroll = new GridBagConstraints();
      gbc_jScroll.gridx = 1;
      gbc_jScroll.gridy = 1;
      tablaProductos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
         @Override
         public void valueChanged(ListSelectionEvent e) {
            if(!e.getValueIsAdjusting())
               mostrarDatos();
         }
      });

      add(jScroll, gbc_jScroll);

      JPanel panel = new JPanel();
      GridBagConstraints gbc_panel = new GridBagConstraints();
      gbc_panel.gridy = 3;
      gbc_panel.gridx = 1;
      panel.setLayout(gbc);
      add(panel, gbc_panel);


      JLabel lblNombre = new JLabel("Nombre del producto");
      lblNombre.setLabelFor(txt_nombre);
      GridBagConstraints gbc_lblNombre = new GridBagConstraints();
      gbc_lblNombre.gridx = 1;
      gbc_lblNombre.gridy = 1;
      panel.add(lblNombre, gbc_lblNombre);

      txt_nombre = new JTextField();
      GridBagConstraints gbc_txtNombre = new GridBagConstraints();
      gbc_txtNombre.gridx = 1;
      gbc_txtNombre.gridy = 2;
      panel.add(txt_nombre, gbc_txtNombre);
      txt_nombre.setColumns(10);

      JLabel lblCantidad = new JLabel("Cantidad");
      lblNombre.setLabelFor(txt_cantidad);
      GridBagConstraints gbc_lblCantidad = new GridBagConstraints();
      gbc_lblCantidad.gridx = 1;
      gbc_lblCantidad.gridy = 3;
      panel.add(lblCantidad, gbc_lblCantidad);

      txt_cantidad = new JTextField();
      txt_cantidad.setColumns(10);
      GridBagConstraints gbc_txtCantidad = new GridBagConstraints();
      gbc_txtCantidad.gridx = 1;
      gbc_txtCantidad.gridy = 4;
      panel.add(txt_cantidad, gbc_txtCantidad);

      JLabel lblPrecio = new JLabel("Precio");
      lblNombre.setLabelFor(txt_cantidad);
      GridBagConstraints gbc_lblPrecio = new GridBagConstraints();
      gbc_lblPrecio.gridx = 2;
      gbc_lblPrecio.gridy = 3;
      panel.add(lblPrecio, gbc_lblPrecio);

      txt_precio = new JTextField();
      txt_precio.setColumns(10);
      GridBagConstraints gbc_txt_precio = new GridBagConstraints();
      gbc_txt_precio.gridx = 2;
      gbc_txt_precio.gridy = 4;

      panel.add(txt_precio, gbc_txt_precio);

      GridBagConstraints gbc_combo = new GridBagConstraints();
      gbc_combo.gridx = 3;
      gbc_combo.gridy = 4;

      panel.add(combo, gbc_combo);

      GridBagConstraints gbc_cb_prov = new GridBagConstraints();
      gbc_cb_prov.gridx = 4;
      gbc_cb_prov.gridy = 4;
      panel.add(cB_prov, gbc_cb_prov);

      // Llena el comboBox de categorias
      ArrayList<Categoria> categorias = categoController.obtenerDatos();
      categorias.forEach((categoria -> {
         ComboItem categoriaCombo = new ComboItem(categoria.getIdCategoria(), categoria.getCategoria());

         combo.addItem(categoriaCombo);
      }));

      ArrayList<Proveedor> proveedores = provController.obtenerDatos();
      proveedores.forEach((prov -> {
         ComboItem provCombo = new ComboItem(prov.getId(), prov.getNombre());
         System.out.println(cB_prov);
         cB_prov.addItem(provCombo);
      }));

      confirmar = new JButton("Actualizar");
      GridBagConstraints gbc_boton = new GridBagConstraints();
      gbc_boton.gridx = 2;
      gbc_boton.gridy = 5;
      panel.add(confirmar, gbc_boton);

      confirmar.addActionListener(this);
   }


   // Obtiene todos los datos del producto a modificar y los muestra en los cuadros de texto
   private void mostrarDatos() {
      ArrayList<Producto> productos = prodController.obtenerDatos();
      int selectedItem = tablaProductos.getSelectedRow();
      Producto selectedProduct = productos.get(selectedItem);


      String nombre = selectedProduct.getNombreProducto();
      String cantidad = String.valueOf(selectedProduct.getExistencia());
      String precio = String.valueOf(selectedProduct.getPrecio());
      int idCategoria = selectedProduct.getIdCategoria();

      int posCategoria = categoController.encontrarPos(idCategoria);

      txt_nombre.setText(nombre);
      txt_cantidad.setText(cantidad);
      txt_precio.setText(precio);
      combo.setSelectedIndex(posCategoria);

   }

   // actualiza el objeto que se modifico
   @Override
   public void actionPerformed(ActionEvent e) {
      // Obtengo todos los productos para posteriormente recuperar el producto seleccionado
      try {
         ArrayList<Producto> productos = prodController.obtenerDatos();
         int selectedItem = tablaProductos.getSelectedRow(); // Obtiene el indice de la tabla del indice seleccionado

         if(selectedItem == -1)
            return;

         Producto selectedProduct = productos.get(selectedItem);  // Obtiene el producto en el indice seleccionado deberia de ser el mismo en la lista de productos

         // Obtencion de datos
         int id = selectedProduct.getIdProducto();
         String nombre = txt_nombre.getText();
         int cantidad = Integer.parseInt(txt_cantidad.getText());
         double precio = Double.parseDouble(txt_precio.getText());

         Object selectedCombo = combo.getSelectedItem();
         int idCategoria = ((ComboItem) selectedCombo).getKey();

         Object selectedProv = combo.getSelectedItem();
         int idProv = ((ComboItem) selectedProv).getKey();

         if(nombre.isEmpty())
            throw new Error();

         // Crea el nuevo producto con los datos modificados
         Producto nuevo = new Producto(id, nombre, idCategoria, cantidad, precio, idProv);
         boolean done = prodController.editar(nuevo.getIdProducto(), nuevo);

         // Si se hizo correctamente la modificacion actualiza la tabla
         if(done) {
            String categoriaString = categoController.getById(idCategoria).getCategoria();
            Object[] item = {id, nombre, cantidad, categoriaString, precio};
            // Actualiza campo por campo la fila del producto seleccionado
            for(int i = 0; i < item.length; i++) {
               tablaProductos.setValueAt(item[i], selectedItem, i);
            }
         }
      } catch (NumberFormatException err) {
         JOptionPane.showMessageDialog(null, "Por favor ingresa correctamente los datos");
      } catch (Error err) {
         JOptionPane.showMessageDialog(null, "Por favor ingresa correctamente los datos");

      }

   }

}
