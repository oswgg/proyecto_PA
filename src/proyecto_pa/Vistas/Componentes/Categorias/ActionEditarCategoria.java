package proyecto_pa.Vistas.Componentes.Categorias;

import proyecto_pa.Controladores.CategoriaController;
import proyecto_pa.Modelos.Categoria;
import proyecto_pa.Modelos.ComboItem;
import proyecto_pa.Modelos.Producto;
import proyecto_pa.Vistas.Componentes.Categorias.TableCategorias;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ActionEditarCategoria extends JPanel implements ActionListener {
   CategoriaController categoController = new CategoriaController();
   JTable tablaCategorias = new TableCategorias().getTabla();
   JTextField txt_nombre = new JTextField();
   JButton confirmar;
   public ActionEditarCategoria() {

      GridBagLayout gbc = new GridBagLayout();
      gbc.columnWidths = new int[]{0, 0, 0, 0};
      gbc.rowHeights = new int[]{0, 0, 0, 54, 0, 0};
      gbc.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
      gbc.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
      setLayout(gbc);

      JScrollPane jScroll = new JScrollPane(tablaCategorias);
      GridBagConstraints gbc_jScroll = new GridBagConstraints();
      gbc_jScroll.gridx = 1;
      gbc_jScroll.gridy = 1;
      add(jScroll, gbc_jScroll);

      tablaCategorias.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
         @Override
         public void valueChanged(ListSelectionEvent e) {
            mostrarDatos();
         }
      });

      JPanel panel = new JPanel();
      GridBagConstraints gbc_panel = new GridBagConstraints();
      gbc_panel.gridy = 3;
      gbc_panel.gridx = 1;
      panel.setLayout(gbc);
      add(panel, gbc_panel);


      JLabel lblNombre = new JLabel("Nombre de la categoria");
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

      confirmar = new JButton("Actualizar");
      GridBagConstraints gbc_boton = new GridBagConstraints();
      gbc_boton.gridx = 1;
      gbc_boton.gridy = 3;
      panel.add(confirmar, gbc_boton);

      confirmar.addActionListener(this);

   }

   @Override
   public void actionPerformed(ActionEvent e) {
     try {
        // Obtengo todos los productos para posteriormente recuperar el producto seleccionado
        ArrayList<Categoria> categorias = categoController.obtenerDatos();
        int selectedItem = tablaCategorias.getSelectedRow(); // Obtiene el indice de la tabla del indice seleccionado

        if(selectedItem == -1)
           return;

        Categoria selectedProduct = categorias.get(selectedItem);  // Obtiene el producto en el indice seleccionado deberia de ser el mismo en la lista de productos

        // Obtencion de datos
        int id = selectedProduct.getIdCategoria();
        String nombre = txt_nombre.getText();

        if(nombre.isEmpty())
            throw new Error();

        // Crea el nuevo producto con los datos modificados
        Categoria nuevo = new Categoria(id, nombre);
        boolean done = categoController.editar(id, nuevo);

        // Si se hizo correctamente la modificacion actualiza la tabla
        if(done) {
           txt_nombre.setText("");
           String categoriaString = categoController.getById(id).getCategoria();
           Object[] item = {id, categoriaString};
           // Actualiza campo por campo la fila del producto seleccionado
           for(int i = 0; i < item.length; i++) {
              tablaCategorias.setValueAt(item[i], selectedItem, i);
           }
        }
     } catch (Error err) {
        JOptionPane.showMessageDialog(null, "Por favor ingresa todos los datos necesarios, con sus tipos de datos correspondientes");
     }

   }

   public void mostrarDatos() {
      ArrayList<Categoria> categorias = categoController.obtenerDatos();
      int selectedItem = tablaCategorias.getSelectedRow();

      if(selectedItem == -1)
         return;

      Categoria selectedCatego = categorias.get(selectedItem);


      String nombre = selectedCatego.getCategoria();

      txt_nombre.setText(nombre);
   }
}
