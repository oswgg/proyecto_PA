package proyecto_pa.Servicios;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// Crea y guarda el archivo con los registros creados
// Espera el path que es el nombre del archivo y la lista de datos (Abstracta)
public class CrearJson<T> {
   // Imprimira los registros de una mejor forma a la vista
   Gson gson = new GsonBuilder().setPrettyPrinting().create();

   public boolean convertirJson(String path, ArrayList<T> datos) {
      boolean done = false;

      // Crea el archivo y escribe en él
      try {
         FileWriter writer = new FileWriter(path);
         String json = gson.toJson(datos); // Transforma la lista de registros a un String
         writer.write(json);  // Escribe el String en el archivo
         writer.close();

         done = true;
      }catch (IOException e) {
         System.out.println("Error al crear el archivo");
         return done;
      }


      return done;
   }

}
