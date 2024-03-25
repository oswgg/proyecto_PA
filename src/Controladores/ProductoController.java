package Controladores;

import Modelos.Producto;
import Servicios.DB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoController implements Controller<Producto>{
   DB db = new DB();

   @Override
   public boolean agregar(Producto toInsert) {
      boolean done = false;
      try {
         String query = "INSERT INTO productos(nombre, idCategoria, precio, existencia) VALUES(?, ?, ?, ?)";
         PreparedStatement pstmt = db.conn.prepareStatement(query);
         pstmt.setString(1, toInsert.getNombreProducto());
         pstmt.setInt(2, toInsert.getIdCategoria());
         pstmt.setDouble(3, toInsert.getPrecio());
         pstmt.setInt(4, toInsert.getExistencia());

         pstmt.executeUpdate();
         done = true;
      } catch (SQLException e) {
         System.out.println(e);
      }

      return done;
   }

   @Override
   public ArrayList<Producto> obtenerDatos() {
      ArrayList<Producto> productos = new ArrayList<>();

      try {
         ResultSet rs = db.stmt.executeQuery("SELECT * FROM productos");

         while(rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            int idCategoria = rs.getInt("idCategoria");
            int existencia = rs.getInt("existencia");
            double precio = rs.getDouble("precio");

            Producto item = new Producto(id, nombre, idCategoria, existencia, precio);
            productos.add(item);
         }

      } catch (SQLException e) {
         System.out.println(e);
      }

      return productos;
   }

   @Override
   public boolean editar(int id, Producto nuevo) {
      boolean done = false;

      try {
         String query = "UPDATE productos SET nombre=?, idCategoria=?, precio=?, existencia=? WHERE id = " + id;
         PreparedStatement pstmt = db.conn.prepareStatement(query);
         pstmt.setString(1, nuevo.getNombreProducto());
         pstmt.setInt(2, nuevo.getIdCategoria());
         pstmt.setDouble(3, nuevo.getPrecio());
         pstmt.setInt(4, nuevo.getExistencia());

         pstmt.executeUpdate();
         done = true;

      }catch (SQLException e){
         System.out.println(e);
      }

      return done;
   }

   @Override
   public boolean eliminar(int id) {
      boolean done = false;

      try {
         db.stmt.execute("DELETE FROM productos WHERE id = " + id);
         done = true;

      }catch (SQLException e) {
         System.out.println(e);
      }

      return done;
   }


   public Producto getById(int id) {
      Producto producto = null; 

      try {
         ResultSet rs = db.stmt.executeQuery("SELECT * FROM productos WHERE id = " + id);

         while(rs.next()) {
            String nombre = rs.getString("nombreProducto");
            int idCategoria = rs.getInt("idCategoria");
            int existencia = rs.getInt("existencia");
            double precio = rs.getDouble("precio");

            producto = new Producto(id, nombre, idCategoria, existencia, precio);
         }

      } catch (SQLException e) {
         System.out.println(e);
      }

      return producto;
   }

}
