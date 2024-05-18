package Modelos;

import Controladores.ProductoController;
import Servicios.DB;

public class Almacen {
   DB db;
   ProductoController prodController = new ProductoController();

   public boolean entradaProd(Producto nuevo) {
      boolean done = true;
      done = prodController.editar(nuevo.getIdProducto(), nuevo);
      return done;
   }

   public boolean salidaProd(Producto nuevo){
      boolean done = true;
      done = prodController.editar(nuevo.getIdProducto(), nuevo);
      return done;
   }
}
