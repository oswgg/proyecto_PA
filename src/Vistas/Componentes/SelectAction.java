package Vistas.Componentes;

import Vistas.Componentes.Categorias.ActionAgregarCategoria;
import Vistas.Componentes.Categorias.ActionEliminarCategoria;
import Vistas.Componentes.Productos.ActionAgregarProducto;
import Vistas.Componentes.Categorias.ActionEditarCategoria;
import Vistas.Componentes.Productos.ActionEditarProducto;
import Vistas.Componentes.Productos.ActionEliminarProducto;
import Vistas.Componentes.Proveedores.ActionAgregarProveedor;
import Vistas.Componentes.Proveedores.ActionEditarProveedor;
import Vistas.Componentes.Proveedores.ActionEliminarProveedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

// Seleccion de la accion a realizar Crear, Editar, Eliminar
public class SelectAction extends JPanel {
   public SelectAction(JPanel jpanel, String vista) {
      GridBagLayout gbl_contentPane = new GridBagLayout();
      gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
      gbl_contentPane.rowHeights = new int[]{0, 0, 0, 54, 0, 0};
      gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
      gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
      setLayout(gbl_contentPane);

      ButtonGroup rads_group = new ButtonGroup();
      JRadioButton rad_agregar = new JRadioButton("Agregar Nuevo");
      JRadioButton rad_editar = new JRadioButton("Editar");
      JRadioButton rad_eliminar = new JRadioButton("Eliminar");

      rad_agregar.setSelected(true);

      add(rad_agregar);
      add(rad_editar);
      add(rad_eliminar);

      rads_group.add(rad_agregar);
      rads_group.add(rad_eliminar);
      rads_group.add(rad_editar);

      /* ----------------- Event Listeners -------------------
         En todos las acciones elimna el panel añadido de la accion
         anterior y añade el de la accion actual.                  */


      // Agregar / Crear
      rad_agregar.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            jpanel.remove(1);

            ActionAgregarProducto agregarProducto = new ActionAgregarProducto();
            ActionAgregarCategoria agregarCategoria = new ActionAgregarCategoria();
            ActionAgregarProveedor agregarProv = new ActionAgregarProveedor();

            GridBagConstraints gbl_agregar = new GridBagConstraints();
            gbl_agregar.gridx = 1;
            gbl_agregar.gridy = 2;

            if(Objects.equals(vista, "productos"))
               jpanel.add(agregarProducto, gbl_agregar);

            else if(Objects.equals(vista, "categorias"))
               jpanel.add(agregarCategoria, gbl_agregar);

            else if(Objects.equals(vista, "proveedores"))
               jpanel.add(agregarProv, gbl_agregar);

            jpanel.revalidate();
            jpanel.repaint();
         }
      });


      // Editar
      rad_editar.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            jpanel.remove(1);
            ActionEditarProducto editarProducto= new ActionEditarProducto();
            ActionEditarCategoria editarCategoria = new ActionEditarCategoria();
            ActionEditarProveedor editarProv = new ActionEditarProveedor();

            GridBagConstraints gbl_editar = new GridBagConstraints();
            gbl_editar.gridx = 1;
            gbl_editar.gridy = 1;

            if(Objects.equals(vista, "productos"))
               jpanel.add(editarProducto, gbl_editar);

            else if(Objects.equals(vista, "categorias"))
               jpanel.add(editarCategoria, gbl_editar);

            else if(Objects.equals(vista, "proveedores"))
               jpanel.add(editarProv, gbl_editar);

            jpanel.revalidate();
            jpanel.repaint();
         }
      });

      // Eliminar
      rad_eliminar.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            jpanel.remove(1);

            ActionEliminarProducto eliminarProducto = new ActionEliminarProducto();
            ActionEliminarCategoria eliminarCategoria = new ActionEliminarCategoria();
            ActionEliminarProveedor eliminarProv = new ActionEliminarProveedor();

            GridBagConstraints gbl_eliminar = new GridBagConstraints();
            gbl_eliminar.gridx = 1;
            gbl_eliminar.gridy = 1;


            if(Objects.equals(vista, "productos"))
               jpanel.add(eliminarProducto, gbl_eliminar);

            else if(Objects.equals(vista, "categorias"))
               jpanel.add(eliminarCategoria, gbl_eliminar);

            else if(Objects.equals(vista, "proveedores"))
               jpanel.add(eliminarProv, gbl_eliminar);

            jpanel.revalidate();
            jpanel.repaint();
         }
      });

   }
}
