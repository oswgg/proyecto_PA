package Controladores;

import Modelos.Categoria;
import Modelos.Lista;
import Modelos.Producto;
import Servicios.DB;

import javax.swing.text.html.CSS;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaController implements Controller<Categoria> {

   Lista<Categoria> listaCategorias;
   DB db = new DB();
   ArrayList<Categoria> categorias;
   String path = "categorias.json";

   public CategoriaController() {
      this.listaCategorias = new Lista<>(this.path);
      this.categorias = this.obtenerDatos();
   }

   @Override
   public boolean agregar(Categoria toInsert) {
      boolean done = false;
      listaCategorias.insert(toInsert);
      try {
         PreparedStatement pstmt = db.conn.prepareStatement("INSERT INTO categorias(nombre) VALUES(?)");
         pstmt.setString(1, toInsert.getCategoria());
         pstmt.executeUpdate();

         done = true;
      } catch (SQLException e) {
         System.out.println(e);
      }

      return done;
   }

   @Override
   public ArrayList<Categoria> obtenerDatos() {
      ArrayList<Categoria> categorias = new ArrayList<>();

      try {
         ResultSet rs = db.stmt.executeQuery("SELECT * FROM categorias");

         while(rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");

            Categoria item = new Categoria(id, nombre);
            categorias.add(item);
         }

      } catch (SQLException e) {
         System.out.println(e);
      }

      return categorias;
   }


   @Override
   public boolean editar(int id, Categoria updated) {
      boolean done = false;

      int pos = this.encontrarPos(id);
      if(pos != -1)
          listaCategorias.update(pos, updated);

      try {

         PreparedStatement pstmt = db.conn.prepareStatement("UPDATE categorias SET nombre = ? WHERE id = " + id);
         pstmt.setString(1, updated.getCategoria());

         pstmt.executeUpdate();
         done = true;

      }catch (SQLException e){
         System.out.println(e);
      }

      return done;
   }

   // Manda la posicion al metodo delete para que lo elimine del archivo
   @Override
   public boolean eliminar(int id) {
      boolean done = false;

      int pos = this.encontrarPos(id);
      if(pos != -1)
          listaCategorias.delete(pos);

      try {
         db.stmt.execute("DELETE FROM categorias WHERE id = " + id);
         done = true;

      }catch (SQLException e) {
         System.out.println(e);
      }

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
      Categoria categoria = null;

      try {
         ResultSet rs = db.stmt.executeQuery("SELECT * FROM categorias WHERE id = " + id);

         while(rs.next()) {
            String nombre = rs.getString("nombre");

            categoria = new Categoria(id, nombre);
         }

      } catch (SQLException e) {
         System.out.println(e);
      }

      return categoria;
   }

}
