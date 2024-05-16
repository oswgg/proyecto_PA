package Vistas;

import Controladores.ProductoController;
import Vistas.Componentes.TablaVentas;

import Controladores.VentaController;
import Modelos.Producto;
import Modelos.Venta;
import Servicios.CrearJson;
import Servicios.LeerJson;
import Vistas.Componentes.TablaVentas;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class vPuntoVenta extends JPanel {
   ProductoController prodController = new ProductoController();
   JLabel lblTotal;
   JButton btnCobrar, btnAgregar, btnQuitarProd;
   VentaController ventaController = new VentaController();
   CrearJson<Producto> toJson = new CrearJson<>();
   LeerJson<Producto> fromJson = new LeerJson<>();
   TablaVentas ptoVentaModelo = new TablaVentas();
   DefaultTableModel modeloTabla = ptoVentaModelo.getModelo();
   JTable tablaVentas = ptoVentaModelo.getTable();
   vElegirProducto elegirProducto = new vElegirProducto();

   public vPuntoVenta () {
      GridBagLayout gbl_layout = new GridBagLayout();
      gbl_layout.columnWidths = new int[]{50, 0, 50};
      gbl_layout.rowHeights = new int[]{0, 0, 0};
      gbl_layout.columnWeights = new double[]{0.0, 1.0, 0.0};
      gbl_layout.rowWeights = new double[]{0.0, 1.0, 0.0};
      setLayout(gbl_layout);

      JPanel panel = new JPanel();
      GridBagConstraints gbc_panel = new GridBagConstraints();
      gbc_panel.insets = new Insets(0, 0, 5, 5);
      gbc_panel.fill = GridBagConstraints.BOTH;
      gbc_panel.gridx = 1;
      gbc_panel.gridy = 1;
      this.add(panel, gbc_panel);

      GridBagLayout gbl_panel = new GridBagLayout();
      gbl_panel.columnWidths = new int[]{205};
      gbl_panel.rowHeights = new int[]{0, 250, 0};
      gbl_panel.columnWeights = new double[]{1.0};
      gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0 };
      panel.setLayout(gbl_panel);

      JLabel lblNewLabel = new JLabel("Oswgg Ventas");
      lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 28));
      GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
      gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
      gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
      gbc_lblNewLabel.gridx = 0;
      gbc_lblNewLabel.gridy = 0;
      panel.add(lblNewLabel, gbc_lblNewLabel);
      panel.setVisible(true);

      JScrollPane jScroll = new JScrollPane(tablaVentas);
      GridBagConstraints gbc_scroll = new GridBagConstraints();
      gbc_panel.insets = new Insets(0, 0, 5, 5);
      gbc_scroll.fill = GridBagConstraints.BOTH;
      gbc_scroll.gridx = 0;
      gbc_scroll.gridy = 1;
      panel.add(jScroll, gbc_scroll);

      JPanel panel2 = new JPanel();
      GridBagConstraints gbc_panel2 = new GridBagConstraints();
      gbc_panel2.insets = new Insets(0, 0, 5, 5);
      gbc_panel2.fill = GridBagConstraints.BOTH;
      gbc_panel2.anchor = GridBagConstraints.EAST;
      gbc_panel2.gridx = 0;
      gbc_panel2.gridy = 3;
      panel.add(panel2, gbc_panel2);

      GridBagLayout gbl_panel2 = new GridBagLayout();
      gbl_panel2.columnWidths = new int[]{0, 0, 0};
      gbl_panel2.rowHeights = new int[]{0};
      gbl_panel2.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
      gbl_panel2.rowWeights = new double[]{1.0};
      panel2.setLayout(gbl_panel2);

      btnAgregar = new JButton("Agregar Producto");
      btnAgregar.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 24));
      GridBagConstraints gbc_btnAgregar = new GridBagConstraints();
      gbc_btnAgregar.fill = GridBagConstraints.VERTICAL;
      gbc_btnAgregar.insets = new Insets(0, 0, 0, 5);
      gbc_btnAgregar.gridx = 2;
      gbc_btnAgregar.gridy = 0;


      panel2.add(btnAgregar, gbc_btnAgregar);

      btnCobrar = new JButton("Cobrar");
      btnCobrar.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 24));
      GridBagConstraints gbc_btnCobrar = new GridBagConstraints();
      gbc_btnCobrar.fill = GridBagConstraints.VERTICAL;
      gbc_btnCobrar.insets = new Insets(0, 0, 0, 5);
      gbc_btnCobrar.gridx = 3;
      gbc_btnCobrar.gridy = 0;
      panel2.add(btnCobrar, gbc_btnCobrar);

      btnQuitarProd = new JButton("Eliminar");
      btnQuitarProd.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 24));
      GridBagConstraints gbc_btnQuitarProd = new GridBagConstraints();
      gbc_btnQuitarProd.fill = GridBagConstraints.VERTICAL;
      gbc_btnQuitarProd.insets = new Insets(0, 0, 0, 5);
      gbc_btnQuitarProd.gridx = 1;
      gbc_btnQuitarProd.gridy = 0;
      panel2.add(btnQuitarProd, gbc_btnQuitarProd);

      lblTotal = new JLabel("$ 0.00");
      lblTotal.setForeground(new Color(32, 62, 210));
      lblTotal.setFont(new Font("Lucida Grande", Font.BOLD, 45));
      GridBagConstraints gbc_lblTotal = new GridBagConstraints();
      gbc_lblTotal.fill = GridBagConstraints.VERTICAL;
      gbc_lblTotal.gridx = 4;
      gbc_lblTotal.gridy = 0;
      panel2.add(lblTotal, gbc_lblTotal);


      btnAgregar.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            handleAgregar();
         }
      });

      btnCobrar.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            handleCobrar();
         }
      });

      btnQuitarProd.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            eliminarProd();
         }
      });

      elegirProducto.addWindowListener(new WindowAdapter() {

         //TODO:  Falta que la tabla se actualice al momento de cerrar la venta de elegir producto
         @Override
         public void windowDeactivated(WindowEvent e) {
            refreshData();
         }
      });
      this.refreshData();
   }


   public void handleAgregar() {
      elegirProducto.setVisible(true);
   }

   public void refreshData() {
      ptoVentaModelo.refreshData();

      ArrayList<Producto> productos = fromJson.getDatos("venta.json");

      double total = 0;
      for(Producto prod : productos) {
         total += prod.getPrecio() * prod.getCantidad();
      }

      lblTotal.setText("$" + total);
   }

   public void handleCobrar() {
      int idVenta = (int) (Math.random() * 100);
      ArrayList<Producto> productosVendidos = fromJson.getDatos("venta.json");

      if(productosVendidos.isEmpty())
         return;

      String totalSplit = lblTotal.getText().split("\\$")[1];
      double total = Double.parseDouble(totalSplit);

      Venta nuevaVenta = new Venta(idVenta, productosVendidos, total);

      boolean done1 = ventaController.registrarVenta(productosVendidos, total);
      if(done1) {
         ventaController.agregar(nuevaVenta);
         productosVendidos.clear();
         boolean done2 = toJson.convertirJson("venta.json", productosVendidos);

         if(done2)
            JOptionPane.showMessageDialog(null, "Venta realizada con éxito, ahora se encuentra en el reporte de ventas");
      }
      this.refreshData();
   }

   public void eliminarProd() {
      ArrayList<Producto> prods = prodController.obtenerDatos();
      int selectedIndex = tablaVentas.getSelectedRow();

      if(selectedIndex == -1)
         return;

      Producto selectedProd = prods.get(selectedIndex);

      LeerJson<Producto> fromJson = new LeerJson<>();
      CrearJson<Producto> toJson = new CrearJson<>();
      ArrayList<Producto> prodsSeleccionados = fromJson.getDatos("venta.json");

      for(Producto prod : prodsSeleccionados) {
         if(selectedProd.getIdProducto() == prod.getIdProducto()) {
                  prodsSeleccionados.remove(prod);
            break;
         }
      }

      toJson.convertirJson("venta.json", prodsSeleccionados);
      this.refreshData();
   }

}
