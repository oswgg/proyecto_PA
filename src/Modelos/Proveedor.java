package Modelos;

public class Proveedor {
   String nombre;
   int id;

   public String getNombre() {
      return nombre;
   }

   public void setNombre(int id, String nombre) {
      this.nombre = nombre;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public Proveedor(int id, String nombre) {
      this.id = id;
      this.nombre = nombre;
   }
}
