package Controladores;

import java.util.ArrayList;

public interface Controller<T> {

   public boolean agregar(T toInsert);

   public ArrayList<T> obtenerDatos();

   boolean editar(int id, T nuevo);

   public boolean eliminar(int id);
}
