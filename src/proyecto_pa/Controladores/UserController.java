package proyecto_pa.Controladores;

import proyecto_pa.Modelos.User;
import proyecto_pa.Servicios.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class UserController implements Controller<User>{
	
	DB db = new DB();
	
	public User validarUser(String username, String password) { 
		User userAuth = null;
		
		try {
			 String query = "SELECT * FROM users WHERE nombre = '" + username + "'";
			 System.out.println(query);
	         ResultSet rs = db.stmt.executeQuery(query);
         
	        if (!rs.isBeforeFirst() ) {    
	        	throw new Error(); 
	        } 
	         rs.next();
	       
	         
	        int id = rs.getInt("id");
            String name = rs.getString("nombre");
            String pass= rs.getString("password");
            String rol = rs.getString("rol");
            
			 System.out.println(password + " " + pass);
            
            if(password.equals(pass)) {
            	userAuth = new User(id, name, rol);
            } else {
   	         	JOptionPane.showMessageDialog(null, "La credenciales no coinciden");

            }
	         
		}catch (SQLException e) { 
			e.printStackTrace();
		}catch(Error e) {
	         JOptionPane.showMessageDialog(null, "La credenciales no coinciden");

		}
		
		return userAuth;
	}

	@Override
	public boolean agregar(User toInsert) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<User> obtenerDatos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public User obtenerDatos(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean editar(int id, User nuevo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
