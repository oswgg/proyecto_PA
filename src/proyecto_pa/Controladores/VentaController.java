package proyecto_pa.Controladores;

import proyecto_pa.Modelos.Lista;
import proyecto_pa.Modelos.Producto;
import proyecto_pa.Modelos.Venta;
import proyecto_pa.Servicios.DB;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


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

               try {
            	   String jasperReporthPath = "C:/Users/oswal/JaspersoftWorkspace/MyReports/Blank_A4_2.jasper";
                   
            	   InputStream reportInputStream = new FileInputStream(new File(jasperReporthPath));
                   JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportInputStream);
            	   
            	   String jsonFilePath = "C:/Users/oswal/eclipse-workspace/proyecto_pa/venta.json";
                   
                   InputStream jsonInputStream = new FileInputStream(new File(jsonFilePath));
                   JsonDataSource jsonDataSource = new JsonDataSource(jsonInputStream);
                   
                   Map<String, Object> parameters = new HashMap<>();
                   
                   JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jsonDataSource);
                   JasperViewer jasperViewer = new JasperViewer(jasperPrint);
                   jasperViewer.setVisible(true);
                   Date date = new Date();
                   DateFormat hourdateFormat = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss");
                   
                   String historial = hourdateFormat.format(date);
                   String outputFilePath = "C:/Users/oswal/eclipse-workspace/proyecto_pa/reportes/reporte_" + historial + ".pdf";
                   JasperExportManager.exportReportToPdfFile(jasperPrint, outputFilePath);

                   System.out.println("Reporte generado exitosamente en: " + outputFilePath);


                   
               }catch(Exception e) {
            	   e.printStackTrace();
               }
             
            }catch (SQLException e){
               System.out.println(e);
               throw new Error();
            }

            // Crea el nuevo producto con los datos modificados
            //Producto nuevo = new Producto(id, nombre, idCategoria, existencia , precio);
            //prodController.editar(nuevo.getIdProducto(), nuevo);
         });
      }catch (SQLException err) {
         System.out.println(err);
         return false;
      }catch (Error e){
    	  e.printStackTrace();
    	  return false;
      }
      return done;

   }

   public ArrayList<Venta> obtenerDatos() {
      return listaVentas.toArrayObjetos();
   }

}
