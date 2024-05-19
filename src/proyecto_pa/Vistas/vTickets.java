package proyecto_pa.Vistas;

import proyecto_pa.Controladores.CategoriaController;
import proyecto_pa.Controladores.VentaController;
import proyecto_pa.Modelos.Categoria;
import proyecto_pa.Modelos.Venta;
import proyecto_pa.Vistas.Componentes.Tickets.TableTickets;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class vTickets extends JPanel {




   CategoriaController categoController = new CategoriaController();
   TableTickets ticketsModelo = new TableTickets();
   DefaultTableModel modeloTabla = ticketsModelo.getModelo();
   JTable tablaTickets = ticketsModelo.getTabla();
   VentaController ventaController = new VentaController();
   JButton btn_confirmar;

   public vTickets() {

      GridBagLayout gbl_contentPane = new GridBagLayout();
      gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
      gbl_contentPane.rowHeights = new int[]{0, 0, 0, 54, 0, 0};
      gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
      gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
      setLayout(gbl_contentPane);

      JScrollPane jScroll = new JScrollPane(tablaTickets);
      GridBagConstraints gbc_jScroll = new GridBagConstraints();
      gbc_jScroll.gridx = 1;
      gbc_jScroll.gridy = 1;
      add(jScroll, gbc_jScroll);

      btn_confirmar = new JButton("Ver más...");
      GridBagConstraints gbc_btn_agregar = new GridBagConstraints();
      gbc_btn_agregar.insets = new Insets(0, 0, 0, 5);
      gbc_btn_agregar.gridx = 1;
      gbc_btn_agregar.gridy = 5;
      add(btn_confirmar, gbc_btn_agregar);

      btn_confirmar.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            mostrarTicket();
         }
      });
   }

   public void mostrarTicket() {
      ArrayList<Venta> tickets =  ventaController.obtenerDatos();
      int selectedItem = tablaTickets.getSelectedRow();

      if(selectedItem == -1) {
          JOptionPane.showMessageDialog(null, "Por favor selecciona un ticket del que quieras obtener mayor información");
         return;
      }

      int id = tickets.get(selectedItem).getId();

      System.out.println(id);
   }
}
