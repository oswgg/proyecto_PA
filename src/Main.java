import Vistas.vPrincipal;

import java.sql.*;

public class Main {

   public static void main(String[] args) {
      String user = "root";
      String pass = "rootOGG040520.gg";
      String url = "jdbc:mysql://localhost:3306/proyecto_integrador?useSSL=false";

      try {
         Connection conn = DriverManager.getConnection(url, user, pass);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM productos");

         System.out.println("Conexion con la base de datos establecida");
         while(rs.next()) {
            System.out.println(rs.getString("nombre"));
         }
      }catch (SQLException e) {
         System.out.println(e);
      }

      vPrincipal ventana = new vPrincipal();
   }

}
