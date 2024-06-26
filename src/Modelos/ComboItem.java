package Modelos;

// Clase de apoyo para los comboBox
// Guarda el id y el nombre de la categoria para posteriormente mostrarla o recuperarla del comboBox
public class ComboItem {
   private int key;
   private String value;

   public ComboItem(int key, String value)
   {
      this.key = key;
      this.value = value;
   }

   public int getKey()
   {
      return key;
   }

   @Override
   public String toString() {
      return this.value;
   }

   public String getValue()
   {
      return value;
   }
}
