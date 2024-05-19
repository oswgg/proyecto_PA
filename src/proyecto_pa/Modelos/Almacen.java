package proyecto_pa.Modelos;

import proyecto_pa.Controladores.ProductoController;
import proyecto_pa.Servicios.DB;

public class Almacen {
   DB db;
   ProductoController prodController = new ProductoController();
   
   public Almacen() {}

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
