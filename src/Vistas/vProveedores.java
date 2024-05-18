package Vistas;
import Vistas.Componentes.Proveedores.ActionAgregarProveedor;
import Vistas.Componentes.SelectAction;

import javax.swing.*;
import java.awt.*;

public class vProveedores extends JPanel{

   ActionAgregarProveedor agregarProveedor;
   public vProveedores() {

      GridBagLayout gbl_contentPane = new GridBagLayout();
      gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
      gbl_contentPane.rowHeights = new int[]{0, 0, 0, 54, 0, 0};
      gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
      gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
      setLayout(gbl_contentPane);


      SelectAction rads_buttons = new SelectAction(this, "proveedores");
      GridBagConstraints gbl_rads = new GridBagConstraints();
      gbl_rads.gridx = 1;
      gbl_rads.gridy = 0;

      agregarProveedor = new ActionAgregarProveedor();
      GridBagConstraints gbl_agregar = new GridBagConstraints();
      gbl_agregar.gridx = 1;
      gbl_agregar.gridy = 1;

      add(rads_buttons, gbl_rads);
      add(agregarProveedor, gbl_agregar);

      setVisible(true);
   }

}
