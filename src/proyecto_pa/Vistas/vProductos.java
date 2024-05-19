package proyecto_pa.Vistas;
import proyecto_pa.Vistas.Componentes.Productos.ActionAgregarProducto;
import proyecto_pa.Vistas.Componentes.SelectAction;

import javax.swing.*;
import java.awt.*;

public class vProductos extends JPanel {
   ActionAgregarProducto agregarProducto;
   public vProductos(){

      GridBagLayout gbl_contentPane = new GridBagLayout();
      gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
      gbl_contentPane.rowHeights = new int[]{0, 0, 0, 54, 0, 0};
      gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
      gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
      setLayout(gbl_contentPane);


      SelectAction rads_buttons = new SelectAction(this, "productos");
      GridBagConstraints gbl_rads = new GridBagConstraints();
      gbl_rads.gridx = 1;
      gbl_rads.gridy = 0;


      agregarProducto = new ActionAgregarProducto();
      GridBagConstraints gbl_agregar = new GridBagConstraints();
      gbl_agregar.gridx = 1;
      gbl_agregar.gridy = 1;

      add(rads_buttons, gbl_rads);
      add(agregarProducto, gbl_agregar);

   }

}

