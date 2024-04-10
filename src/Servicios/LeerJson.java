package Servicios;

import Modelos.Categoria;
import Modelos.Producto;
import Modelos.Venta;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

// Devuelve un arrayList del tipo que espera la llamada a ésta función con todos los registros del archivo seleccionado
public class LeerJson<T> {

   public ArrayList<T> getDatos(String path)  {
      Gson gson = new Gson();
      ArrayList<T> listaDatos = new ArrayList<>();

      try {
         FileReader fr = new FileReader(path);

         // Selecciona el archivo y el tipo de Clase que debe devolver
         switch (path){
            case "productos.json":
            case "venta.json":
               ArrayList<Producto> productos = gson.fromJson(fr, new TypeToken<List<Producto>>(){}.getType());
               listaDatos = (ArrayList<T>) productos;
               break;

            case "categorias.json":
               ArrayList<Categoria> categorias = gson.fromJson(fr, new TypeToken<List<Categoria>>(){}.getType());
               listaDatos = (ArrayList<T>) categorias;
               break;

            case "reporteVentas.json":
               ArrayList<Venta> ventas = gson.fromJson(fr, new TypeToken<List<Venta>>(){}.getType());
               listaDatos = (ArrayList<T>) ventas;
               break;
         }
      } catch (FileNotFoundException e){
         System.out.println("Archivo no encontrado");
         return new ArrayList<>();
      }

      // Si no encuentra ningun registro devuelve un arrayLista vacio para evitar NullPointeException
      if(listaDatos == null)
         listaDatos = new ArrayList<>();

      return listaDatos;
   }

}
