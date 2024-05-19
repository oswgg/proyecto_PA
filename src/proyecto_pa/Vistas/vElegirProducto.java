package proyecto_pa.Vistas;

import proyecto_pa.Controladores.ProductoController;
import proyecto_pa.Modelos.Producto;
import proyecto_pa.Servicios.CrearJson;
import proyecto_pa.Servicios.LeerJson;
import proyecto_pa.Vistas.Componentes.Productos.TableProductos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class vElegirProducto extends JFrame {
   TableProductos modeloProductos = new TableProductos(true);
   JTable tablaProds = modeloProductos.getTable();
   DefaultTableModel modeloTabla = modeloProductos.getModelo();
   ProductoController prodController = new ProductoController();
   JSpinner spinnerCant;
   public vElegirProducto() {
      setTitle("Elige un producto");
      setSize(600, 400);
      setLocation(850, 200);
      setVisible(false);

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
      GridBagConstraints bgc_bagregar = new GridBagConstraints();
      bgc_bagregar.fill = GridBagConstraints.BOTH;
      bgc_bagregar.gridx = 1;
      bgc_bagregar.gridy = 0;
      panel.add(btnAgregar, bgc_bagregar);

      btnAgregar.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            obtenerProducto();
         }
      });


   }

   public void obtenerProducto() {
      ArrayList<Producto> prods = prodController.obtenerDatos();
      int selectedIndex = tablaProds.getSelectedRow();

      if(selectedIndex == -1)
         return;

      Producto selectedProd = prods.get(selectedIndex);
      selectedProd.setCantidad((Integer) spinnerCant.getValue());

      LeerJson<Producto> fromJson = new LeerJson<>();
      CrearJson<Producto> toJson = new CrearJson<>();
      ArrayList<Producto> prodsSeleccionados = fromJson.getDatos("venta.json");

      // Si encuentra el prod que ya está seleccionado solo modifica la cantidad de productos que habra por vender
      boolean found = false;
      for(Producto prod : prodsSeleccionados) {
         if(selectedProd.getIdProducto() == prod.getIdProducto()) {
            found = true;
            int cant = prod.getCantidad() + selectedProd.getCantidad();
            prod.setCantidad(cant);

            if(selectedProd.getCantidad() < 0 ){
               if(cant <= 0) {
                  prodsSeleccionados.remove(prod);
               }
            }

            break;
         }

      }

      // sino añade el producto a la lista
      if(!found)
         prodsSeleccionados.add(selectedProd);

      toJson.convertirJson("venta.json", prodsSeleccionados);
      spinnerCant.setValue(0);
      this.setVisible(false);
   }
}
