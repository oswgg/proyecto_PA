package Controladores;

import Modelos.Categoria;
import Modelos.Lista;
import Modelos.Producto;
import Modelos.Venta;
import Servicios.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VentaController {
   Lista<Venta> listaVentas;
   ArrayList<Venta> ventas;
   String path = "reporteVentas.json";
   DB db = new DB();

   public VentaController() {
      this.listaVentas = new Lista<>(this.path);
      this.ventas = this.obtenerDatos();
   }

   public boolean agregar(Venta toInsert) {
      ventas.add(toInsert);
      return listaVentas.insert(toInsert);
   }

   public boolean registrarVenta(ArrayList<Producto> productosVendidos, double total) {
      ProductoController prodController = new ProductoController();
      boolean done = true;
      try {
         PreparedStatement pstmt = db.conn.prepareStatement("INSERT INTO ventas(total, cant_vendidos) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
         pstmt.setString(1, String.valueOf(total));
         pstmt.setString(2, String.valueOf(productosVendidos.size()));
         pstmt.executeUpdate();
         ResultSet rs = pstmt.getGeneratedKeys();
         rs.next();
         int idOfVenta = rs.getInt(1);

         productosVendidos.forEach(productoVendido -> {
            int cantVender = productoVendido.getCantidad();

            // Obtencion de datos
            int id = productoVendido.getIdProducto();

            Producto prodBeforeVender = prodController.getById(id);

            String nombre = prodBeforeVender.getNombreProducto();
            double precio = prodBeforeVender.getPrecio();

            int idCategoria = prodBeforeVender.getIdCategoria();

            try{
               PreparedStatement pstmt2 = db.conn.prepareStatement("INSERT INTO ventas_prods(id_producto, nombre, idCategoria, precio, cantidad, id_venta) VALUES(?, ?, ?, ? ,? ,?)");
               pstmt2.setString(1, String.valueOf(id));
               pstmt2.setString(2, nombre);
               pstmt2.setString(3, String.valueOf(idCategoria));
               pstmt2.setString(4, String.valueOf(precio));
               pstmt2.setString(5, String.valueOf(cantVender));
               pstmt2.setString(6, String.valueOf(idOfVenta));
               pstmt2.executeUpdate();
            }catch (SQLException e){
               System.out.println(e);
               throw new Error();
            }

            // Crea el nuevo producto con los datos modificados
            //Producto nuevo = new Producto(id, nombre, idCategoria, existencia , precio);
            //prodController.editar(nuevo.getIdProducto(), nuevo);
         });
      }catch (SQLException err) {
         System.out.println("error de aqui" + err);
         return false;
      }catch (Error e){
         return false;
      }
      return done;

   }

   public ArrayList<Venta> obtenerDatos() {
      return listaVentas.toArrayObjetos();
   }

}
