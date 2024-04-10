package Controladores;

import Modelos.Categoria;
import Modelos.Lista;
import Modelos.Venta;

import java.util.ArrayList;

public class VentaController {
   Lista<Venta> listaVentas;
   ArrayList<Venta> ventas;
   String path = "reporteVentas.json";

   public VentaController() {
      this.listaVentas = new Lista<>(this.path);
      this.ventas = this.obtenerDatos();
   }

   public boolean agregar(Venta toInsert) {
      ventas.add(toInsert);
      return listaVentas.insert(toInsert);
   }

   public ArrayList<Venta> obtenerDatos() {
      return listaVentas.toArrayObjetos();
   }

}
