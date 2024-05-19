package proyecto_pa.Modelos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Venta {
   int id;
   ArrayList<Producto> productosVendidos;
   double total;
   
   public Venta () {}

   public Venta(int id, ArrayList<Producto> productosVendidos, double total) {
      this.id = id;
      this.productosVendidos = productosVendidos;
      this.total = total;
   }

   public int getId() {
      return id;
   }

   public ArrayList<Producto> getProductosVendidos() {
      return productosVendidos;
   }

   public double getTotal() {
      return total;
   }



}
