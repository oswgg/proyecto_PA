package Controladores;

import Modelos.Categoria;
import Modelos.Lista;
import Modelos.Producto;

import java.util.ArrayList;

public class ProductoController implements Controller<Producto>{

   Lista<Producto> listaProductos;
   ArrayList<Producto> productos;
   String path = "productos.json";

   public ProductoController() {
      this.listaProductos = new Lista<>(this.path);
      this.productos = this.obtenerDatos();
   }

   @Override
   public boolean agregar(Producto toInsert) {
      return listaProductos.insert(toInsert);
   }

   @Override
   public ArrayList<Producto> obtenerDatos() {
      return listaProductos.toArrayObjetos();
   }

   @Override
   public boolean editar(int id, Producto nuevo) {
      boolean done = false;
      // Encuentra la posicion del objeto que debe modificar para en la clase lista utilizarlo
      int pos = this.encontrarPos(id);

      // Se asegura de que el producto exista
      if(pos != -1)
         done = listaProductos.update(pos, nuevo);

      return done;
   }

   @Override
   public boolean eliminar(int id) {
      boolean done = false;

      int pos = this.encontrarPos(id);

      if(pos != -1)
         done = listaProductos.delete(pos);

      return done;
   }

   public int encontrarPos(int id) {
      int cont = 0;
      for (Producto producto : productos) {
         if(producto.getIdProducto() == id)
            return cont;
         else
            cont++;
      }

      return -1;
   }

   public Producto getById(int id) {
      for (Producto producto : productos) {
         if(producto.getIdProducto() == id)
            return producto;
      }

      return null;
   }

}
