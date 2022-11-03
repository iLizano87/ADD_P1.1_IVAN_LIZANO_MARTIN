package dam2.add.p11;

public class Usuario {

	String nombre;
	String password;
	int intentos;

	public Usuario() {

	}
	public Usuario(String nombre) {
		this.nombre = nombre;
	}
	public Usuario(String nombre, String password) {
		this.nombre = nombre;
		this.password = password;
	}

	public Usuario(String nombre, String password, int intentos) {
		this.nombre = nombre;
		this.password = password;
		this.intentos = intentos;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public int getIntentos() {
		return this.intentos;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}

}
