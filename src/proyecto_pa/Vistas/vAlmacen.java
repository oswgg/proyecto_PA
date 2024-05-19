package proyecto_pa.Vistas;

import proyecto_pa.Controladores.CategoriaController;
import proyecto_pa.Controladores.ProductoController;
import proyecto_pa.Controladores.VentaController;
import proyecto_pa.Modelos.Almacen;
import proyecto_pa.Modelos.ComboItem;
import proyecto_pa.Modelos.Producto;
import proyecto_pa.Modelos.Venta;
import proyecto_pa.Vistas.Componentes.Almacen.TableAlmacen;
import proyecto_pa.Vistas.Componentes.Productos.TableProductos;
import proyecto_pa.Vistas.Componentes.Tickets.TableTickets;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class vAlmacen extends JPanel {

   ProductoController prodController = new ProductoController();
   CategoriaController categoController = new CategoriaController();
   TableProductos productosModelo = new TableProductos(false);
   DefaultTableModel modeloTabla = productosModelo.getModelo();
   JTable tablaProductos = productosModelo.getTable();
   VentaController ventaController = new VentaController();
   JButton btn_agregar, btn_quitar;
   JTextField cantidad_text;
   JLabel lblCantidad;

   public vAlmacen() {

      GridBagLayout gbl_contentPane = new GridBagLayout();
      gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
      gbl_contentPane.rowHeights = new int[]{0, 0, 0, 54, 0, 0};
      gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
      gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
      setLayout(gbl_contentPane);

      JScrollPane jScroll = new JScrollPane(tablaProductos);
      GridBagConstraints gbc_jScroll = new GridBagConstraints();
      gbc_jScroll.gridx = 1;
      gbc_jScroll.gridy = 1;
      add(jScroll, gbc_jScroll);

      JLabel lblCantidad = new JLabel("Cantidad");
      lblCantidad.setLabelFor(cantidad_text);
      GridBagConstraints gbc_lblCantidad = new GridBagConstraints();
      gbc_lblCantidad.gridx = 1;
      gbc_lblCantidad.gridy = 3;
      add(lblCantidad, gbc_lblCantidad);


      cantidad_text = new JTextField();
      cantidad_text.setColumns(10);
      GridBagConstraints gbc_cantidad = new GridBagConstraints();
      gbc_cantidad.gridx = 1;
      gbc_cantidad.gridy = 4;
      add(cantidad_text, gbc_cantidad);

      btn_agregar = new JButton("Agregar");
      GridBagConstraints gbc_btn_agregar = new GridBagConstraints();
      gbc_btn_agregar.insets = new Insets(0, 0, 0, 5);
      gbc_btn_agregar.gridx = 1;
      gbc_btn_agregar.gridy = 5;
      add(btn_agregar, gbc_btn_agregar);

      btn_quitar = new JButton("Retirar productos");
      GridBagConstraints gbc_btn_quitar = new GridBagConstraints();
      gbc_btn_quitar.insets = new Insets(0, 0, 0, 5);
      gbc_btn_quitar.gridx = 1;
      gbc_btn_quitar.gridy = 6;
      add(btn_quitar, gbc_btn_quitar);


      btn_agregar.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            agregarProds();
         }
      });

      btn_quitar.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            quitarProd();
         }
      });
   }

   public void agregarProds() {
      // Obtengo todos los productos para posteriormente recuperar el producto seleccionado
      try {
         ArrayList<Producto> productos = prodController.obtenerDatos();
         int selectedItem = tablaProductos.getSelectedRow(); // Obtiene el indice de la tabla del indice seleccionado

         if(selectedItem == -1)
            return;

         Producto selectedProduct = productos.get(selectedItem);  // Obtiene el producto en el indice seleccionado deberia de ser el mismo en la lista de productos

         int cantAIngresar = Integer.parseInt(cantidad_text.getText());

         // Obtencion de datos
         int id = selectedProduct.getIdProducto();
         String nombre = selectedProduct.getNombreProducto();
         int cantidad = selectedProduct.getExistencia() + cantAIngresar;
         double precio = selectedProduct.getPrecio();
         int idProv = selectedProduct.getidProveedor();

         int idCategoria = selectedProduct.getIdCategoria();

         // Crea el nuevo producto con los datos modificados
         Producto nuevo = new Producto(id, nombre, idCategoria, cantidad , precio, idProv);
         Almacen almacen = new Almacen();
         boolean done = almacen.entradaProd(nuevo);

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

   public void quitarProd() {
      // Obtengo todos los productos para posteriormente recuperar el producto seleccionado
      try {
         ArrayList<Producto> productos = prodController.obtenerDatos();
         int selectedItem = tablaProductos.getSelectedRow(); // Obtiene el indice de la tabla del indice seleccionado

         if(selectedItem == -1)
            return;

         Producto selectedProduct = productos.get(selectedItem);  // Obtiene el producto en el indice seleccionado deberia de ser el mismo en la lista de productos

         int cantAIngresar = Integer.parseInt(cantidad_text.getText());

         // Obtencion de datos
         int id = selectedProduct.getIdProducto();
         String nombre = selectedProduct.getNombreProducto();
         int cantidad = selectedProduct.getExistencia() - cantAIngresar;
         double precio = selectedProduct.getPrecio();
         int idProv = selectedProduct.getidProveedor();
         int idCategoria = selectedProduct.getIdCategoria();

         // Crea el nuevo producto con los datos modificados
         Producto nuevo = new Producto(id, nombre, idCategoria, cantidad , precio, idProv);
         Almacen almacen = new Almacen();
         boolean done = almacen.entradaProd(nuevo);

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


