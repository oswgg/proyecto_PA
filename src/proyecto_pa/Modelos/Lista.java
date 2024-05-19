package proyecto_pa.Modelos;


import proyecto_pa.Servicios.CrearJson;
import proyecto_pa.Servicios.LeerJson;
import java.util.ArrayList;

public class Lista<T> {
   public ArrayList<T> lista;
   LeerJson<T> leerJson = new LeerJson<>();
   CrearJson<T> crearJson = new CrearJson<>();
   String path;

   	public Lista() {}
   
   public Lista(String path) {
      this.path = path;
      this.lista = leerJson.getDatos(this.path);
   }

   // Guardar un nuevo registro
   public boolean insert(T toInsert) {
      boolean done;

      // Obtiene todos los datos en un arrayList y agrega el nuevo registro
      this.lista = leerJson.getDatos(this.path); // Lo obtiene del archivo que se haya especificado a la hora de crear el objeto de lista en el Controller
      this.lista.add(toInsert);

      // Guarda el archivo con la nueva lista modificada
      done = crearJson.convertirJson(this.path, this.lista);

      return done;
   }

   // Elimina la el registro en la posicion indicada
   public boolean delete(int pos) {
      boolean done;

      this.lista.remove(pos);

      done = crearJson.convertirJson(this.path, this.lista);

      return done;
   }

   public boolean update(int pos, T nuevo) {
      boolean done = false;

      // Busca la posicion que debe modificar
      for (int i = 0; i < this.lista.size(); i++) {
         if(i == pos) {
            this.lista.add(pos, nuevo); // Agrega el objeto modificado en la posicion en la que se debe ingresar
            this.lista.remove(pos + 1); // Elimina el objeto anterior que fue desplazado por el modificado
            done = crearJson.convertirJson(this.path, this.lista); // Guarda los cambios con la lista actualizad
         }
      }

      return done;
   }


   // Devuelve en un arrayList los registros obtenidos del archivo especificado
   public ArrayList<T> toArrayObjetos() {
      return this.lista;
   }

   public Object getAt(int pos) {
      return this.lista.get(pos);
   }

   public Object getLast(int pos) {
      return this.lista.get(this.tam() - 1);
   }

   public boolean isEmpty() {
      return this.lista.isEmpty();
   }

   public int tam() {
      return this.lista.size();
   }
}

