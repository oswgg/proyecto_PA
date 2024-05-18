package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class vPrincipal extends JFrame implements ActionListener {
   vProductos vistaProductos = new vProductos();
   vCategorias vistaCategorias = new vCategorias();
   vPuntoVenta vistaPtoVenta = new vPuntoVenta();
   vTickets vistaTickets = new vTickets();
   vAlmacen vistaAlmacen = new vAlmacen();
   vProveedores vistaProveedores = new vProveedores();

   JMenuItem productos, categorias, tickets, puntoVenta, salidaBtn, almacenBtn, proveedores;
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
      tickets = new JMenuItem("Tickets");
      salidaBtn = new JMenuItem("Salida");
      almacenBtn = new JMenuItem("Almacen");
      proveedores = new JMenuItem("Proveedores");

      miBarra.add(ptoVenta);
      miBarra.add(almacen);
      miBarra.add(reportes);
      miBarra.add(configuracion);
      miBarra.add(salida);

      puntoVenta.addActionListener(this);
      productos.addActionListener(this);
      tickets.addActionListener(this);
      categorias.addActionListener(this);
      salidaBtn.addActionListener(this);
      almacenBtn.addActionListener(this);
      proveedores.addActionListener(this);


      ptoVenta.add(puntoVenta);
      reportes.add(tickets);
      configuracion.add(productos);
      configuracion.add(categorias);
      configuracion.add(proveedores);
      salida.add(salidaBtn);
      almacen.add(almacenBtn);

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
      remove(vistaTickets);
      remove(vistaCategorias);
      remove(vistaPtoVenta);
      remove(vistaAlmacen);
      remove(vistaProveedores);

      if(e.getSource() == puntoVenta) {
         vistaPtoVenta = new vPuntoVenta();
         vistaPtoVenta.setLocation(0, 0);
         add(vistaPtoVenta);
      } else if(e.getSource() == tickets) {
          vistaTickets = new vTickets();
          vistaTickets.setLocation(0, 0);
          add(vistaTickets);
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
      } else if(e.getSource() == almacenBtn) {
         vistaAlmacen = new vAlmacen();
         vistaAlmacen.setLocation(0, 0);
         add(vistaAlmacen);
      } else if(e.getSource() == proveedores) {
         vistaProveedores = new vProveedores();
         vistaProveedores.setLocation(0, 0);
         add(vistaProveedores);
      }

      revalidate();
      repaint();
   }


}

