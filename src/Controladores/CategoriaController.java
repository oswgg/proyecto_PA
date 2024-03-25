package Controladores;

import Modelos.Categoria;
import Modelos.Lista;
import Modelos.Producto;

import java.util.ArrayList;

public class CategoriaController implements Controller<Categoria> {

   Lista<Categoria> listaCategorias;
   ArrayList<Categoria> categorias;
   String path = "categorias.json";

   public CategoriaController() {
      this.listaCategorias = new Lista<>(this.path);
      this.categorias = this.obtenerDatos();
   }

   @Override
   public boolean agregar(Categoria toInsert) {
      categorias.add(toInsert);
      return listaCategorias.insert(toInsert);
   }

   @Override
   public ArrayList<Categoria> obtenerDatos() {
      return listaCategorias.toArrayObjetos();
   }

   @Override
   public boolean editar(int id, Categoria nuevo) {
      boolean done = false;
      // Encuentra la posicion del objeto que debe modificar para en la clase lista utilizarlo
      int pos = this.encontrarPos(id);

      // Se asegura de que el producto exista
      if(pos != -1)
         done = listaCategorias.update(pos, nuevo);

      return done;
   }

   // Manda la posicion al metodo delete para que lo elimine del archivo
   @Override
   public boolean eliminar(int id) {
      boolean done = false;

      int pos = this.encontrarPos(id);

      if(pos != -1)
         done = listaCategorias.delete(pos);

      return done;
   }

   public int encontrarPos(int id) {
      int cont = 0;
      for (Categoria categoria : categorias) {
         if(categoria.getIdCategoria() == id)
            return cont;
         else
            cont++;
      }

      return -1;
   }

   public Categoria getById(int id) {
      for (Categoria categoria : categorias) {
         if(categoria.getIdCategoria() == id)
            return categoria;
      }

      return null;
   }

}
