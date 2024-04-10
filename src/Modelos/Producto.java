package Modelos;

public class Producto {
   int idProducto;
   String nombreProducto;
   int cantidad;
   int idCategoria;
   int existencia;
   double precio;

   public Producto(int idProducto, String nombreProducto, int idCategoria , int cantidad, double precio) {
      this.idProducto = idProducto;
      this.nombreProducto = nombreProducto;
      this.idCategoria = idCategoria;
      this.existencia = cantidad;
      this.precio = precio;
   }

   public int getIdProducto() {
      return idProducto;
   }

   public String getNombreProducto() {
      return nombreProducto;
   }

   public int getCantidad() {
      return this.cantidad;
   }

   public int getIdCategoria() {
      return this.idCategoria;
   }

   public double getPrecio() {
      return this.precio;
   }

   public int getExistencia() {
      return this.existencia;
   }

   public void setCantidad(int cant) { this.cantidad = cant;}
}
