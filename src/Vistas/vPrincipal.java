package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class vPrincipal extends JFrame implements ActionListener {
   vProductos vistaProductos = new vProductos();
   vCategorias vistaCategorias = new vCategorias();
   vPuntoVenta vistaPtoVenta = new vPuntoVenta();

   JMenuItem productos, categorias, puntoVenta, salidaBtn;
   public vPrincipal() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JMenuBar miBarra = new JMenuBar();

      JMenu ptoVenta = new JMenu("Punto de Venta");
      JMenu almacen = new JMenu("Almacen");
      JMenu reportes = new JMenu("Reportes");
      JMenu configuracion = new JMenu("Configuracion");
      JMenu salida = new JMenu("Salida");

      puntoVenta = new JMenuItem("Punto de venta");
      productos = new JMenuItem("Productos");
      categorias = new JMenuItem("Categorias");

      salidaBtn = new JMenuItem("Salida");

      miBarra.add(ptoVenta);
      miBarra.add(almacen);
      miBarra.add(reportes);
      miBarra.add(configuracion);
      miBarra.add(salida);

      puntoVenta.addActionListener(this);
      productos.addActionListener(this);
      categorias.addActionListener(this);
      salidaBtn.addActionListener(this);

      ptoVenta.add(puntoVenta);

      configuracion.add(productos);
      configuracion.add(categorias);
      salida.add(salidaBtn);


      setJMenuBar(miBarra);

      initialize();
   }

   private void initialize() {
      setBounds(100, 100, 900, 600);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      vistaPtoVenta = new vPuntoVenta();
      vistaPtoVenta.setLocation(0, 0);
      this.add(vistaPtoVenta);

      setVisible(true);


   }

   // Cuando se selecciona otro menuItem borra el panel de la vista anterior y muestra el nuevo
   @Override
   public void actionPerformed(ActionEvent e) {
      remove(vistaProductos);
      remove(vistaCategorias);
      remove(vistaPtoVenta);

      if(e.getSource() == puntoVenta) {
         vistaPtoVenta = new vPuntoVenta();
         vistaPtoVenta.setLocation(0, 0);
         add(vistaPtoVenta);
      } else if(e.getSource() == productos){
         vistaProductos = new vProductos();
         vistaProductos.setLocation(0, 0);
         add(vistaProductos);
      } else if(e.getSource() == categorias) {
         vistaCategorias = new vCategorias();
         vistaCategorias.setLocation(0, 0);
         add(vistaCategorias);
      } else if (e.getSource() == salidaBtn){
         dispose();
         System.exit(0);
      }

      revalidate();
      repaint();
   }


}

