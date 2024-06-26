package Modelos;

public class Producto {
   int idProducto;
   String nombreProducto;
   int idProveedor;
   int cantidad;
   int idCategoria;
   int existencia;
   double precio;

   public Producto(int idProducto, String nombreProducto, int idCategoria , int existencia, double precio, int idProveedor) {
      this.idProducto = idProducto;
      this.nombreProducto = nombreProducto;
      this.idCategoria = idCategoria;
      this.existencia = existencia;
      this.precio = precio;
      this.idProveedor = idProveedor;
   }



   public Producto(int idProducto, String nombreProducto, int idCategoria , int cantidad, int existencia, double precio, int idProveedor) {
      this.idProducto = idProducto;
      this.nombreProducto = nombreProducto;
      this.idCategoria = idCategoria;
      this.cantidad = cantidad;
      this.existencia = existencia;
      this.precio = precio;
      this.idProveedor = idProveedor;
   }

   public int getidProveedor() {
      return this.idProveedor;
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
