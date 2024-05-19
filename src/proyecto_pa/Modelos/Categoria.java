package proyecto_pa.Modelos;

public class Categoria {
   public int idCategoria;
   public String categoria;
   
   public Categoria() {}

   public Categoria(int idCategoria, String categoria) {
      this.idCategoria = idCategoria;
      this.categoria = categoria;
   }

   public int getIdCategoria() {
      return idCategoria;
   }

   public void setIdCategoria(int idCategoria) {
      this.idCategoria = idCategoria;
   }

   public String getCategoria() {
      return categoria;
   }

   public void setCategoria(String categoria) {
      this.categoria = categoria;
   }

   public String toTxtFile() {
      return this.getIdCategoria() + "," + this.getCategoria() + ",";
   }
}