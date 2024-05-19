package proyecto_pa.Controladores;

import proyecto_pa.Modelos.Lista;
import proyecto_pa.Modelos.Proveedor;
import proyecto_pa.Servicios.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProveedorController implements  Controller<Proveedor>{

   DB db = new DB();
   Lista<Proveedor> listaProveedores;
   ArrayList<Proveedor> proveedores;
   String path = "proveedores.json";

   public ProveedorController() {
      this.listaProveedores = new Lista<>(this.path);
      this.proveedores = this.obtenerDatos();
   }

   @Override
   public boolean agregar(Proveedor toInsert) {
      boolean done = false;
      listaProveedores.insert(toInsert);
      try {
         String query = "INSERT INTO proveedores(nombre) VALUES(?)";
         PreparedStatement pstmt = db.conn.prepareStatement(query);
         pstmt.setString(1, toInsert.getNombre());

         pstmt.executeUpdate();
         done = true;
      } catch (SQLException e) {
         System.out.println(e);
      }

      return done;
   }

   @Override
   public ArrayList<Proveedor> obtenerDatos() {
      ArrayList<Proveedor> proveedores = new ArrayList<>();

      try {
         ResultSet rs = db.stmt.executeQuery("SELECT * FROM proveedores");

         while(rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");

            Proveedor item = new Proveedor(id, nombre);
            proveedores.add(item);
         }

      } catch (SQLException e) {
         System.out.println(e);
      }

      return proveedores;
   }

   public ArrayList<Proveedor> getExistentes() {
      ArrayList<Proveedor> proveedores = new ArrayList<>();

      try {
         ResultSet rs = db.stmt.executeQuery("SELECT * FROM proveedores WHERE existencia > 0 ");

         while(rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");

            Proveedor item = new Proveedor(id, nombre);
            proveedores.add(item);
         }

      } catch (SQLException e) {
         System.out.println(e);
      }

      return proveedores;
   }
   @Override
   public boolean editar(int id, Proveedor nuevo) {
      boolean done = false;
      // Encuentra la posicion del objeto que debe modificar para en la clase lista utilizarlo
      int pos = this.encontrarPos(id);
      if(pos != -1)
         listaProveedores.update(pos, nuevo);
      try {
         String query = "UPDATE proveedores SET nombre=?  WHERE id = " + id;
         PreparedStatement pstmt = db.conn.prepareStatement(query);
         pstmt.setString(1, nuevo.getNombre());

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

      int pos = this.encontrarPos(id);
      if(pos != -1)
         done = listaProveedores.delete(pos);

      try {
         db.stmt.execute("DELETE FROM proveedores WHERE id = " + id);
         done = true;

      }catch (SQLException e) {
         System.out.println(e);
      }

      return done;
   }


   public int encontrarPos(int id) {
      int cont = 0;
      for (Proveedor proveedor : proveedores) {
         if(proveedor.getId() == id)
            return cont;
         else
            cont++;
      }

      return -1;
   }

   public Proveedor getById(int id) {
      Proveedor proveedor = null;

      try {
         ResultSet rs = db.stmt.executeQuery("SELECT * FROM productos WHERE id = " + id);

         while(rs.next()) {
            int idProv = rs.getInt("id");
            String nombre = rs.getString("nombre");


            proveedor = new Proveedor(idProv, nombre);
         }

      } catch (SQLException e) {
         System.out.println(e);
      }

      return proveedor;
   }

}
