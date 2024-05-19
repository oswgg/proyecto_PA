package proyecto_pa.Modelos;

public class User {
	int id;
	String nombre;
	String rol;
	private String password;
	
	public User() {}
	
	public User(String nombre,String rol, String password) { 
		this.nombre = nombre;
		this.rol = rol;
		this.password = password;
	}
	
	public User(int id, String nombre, String rol) {
		this.id = id;
		this.nombre = nombre;
		this.rol = rol;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
