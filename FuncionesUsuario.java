package dam2.add.p11;

import java.util.ArrayList;
import java.util.Date;

public class FuncionesUsuario {

	public static void nuevoUsuario(Date date, ArrayList<Usuario> listaUsuarios, String user, String pass_new) {
		listaUsuarios.add(new Usuario(user, pass_new, 0)); //Agrega a nuestro ArrayList usuario
		String user_new = user + ":" + pass_new;
		FuncionesArchivo.escribirUsuario(user, pass_new);//Escribe en archivo usuario
		FuncionesArchivo.logNuevo(user, date);//Escribe en log 
	}

	

	public static void extraerUsuarios(ArrayList<String> usuarios, ArrayList<Usuario> listaUsuarios) {
		for (int i = 0; i < usuarios.size(); i++) { // Recorremos ArrayList usuarios
			String s = usuarios.get(i); // Tomamos cada elemento
			String[] a;// Declaramos array de strings
			a = s.split(":"); // Hacemos division
			String usuario = a[0]; // guardamos usuario en pos[0]
			String password = a[1];// guardamos pass en pos[1]
			listaUsuarios.add(new Usuario(usuario, password, 0));//agregamos a listaUsuarios nombre,pass e intentos=0
		}
	}
	

	public static ArrayList<Usuario> listarUsuarios(ArrayList<String> usuarios, ArrayList<Usuario> listaUsuarios) {
		extraerUsuarios(usuarios, listaUsuarios); //Rellena ArrayList listaUsuarios
		return listaUsuarios;

	}
	
	public static boolean comprobarUsuario(String userName, ArrayList<Usuario> listaUsuarios) { //Existe usuario?
		for (int i = 0; i < listaUsuarios.size(); i++) { // recorremos el arraylist
			if (listaUsuarios.get(i).getNombre().equals(userName)) {// Si coincide retorna i (la posicion)
				return true;// comprobar si existe
			}
		}
		return false; 
	}

	public static int posUsuario(String userName, ArrayList<Usuario> listaUsuarios) {
		for (int i = 0; i < listaUsuarios.size(); i++) { // recorremos el arraylist
			if (listaUsuarios.get(i).getNombre().equals(userName)) {
				return i; // retorna i (la posicion)
			}
		}
		return -1; // Si no existe, retorna -1
	}

	public static boolean comprobarLogin(String nombre, String pass, ArrayList<Usuario> listaUsuarios) {
		int sitio = posUsuario(nombre, listaUsuarios);//Tomamos posicion usuario		
		if (listaUsuarios.get(sitio).getNombre().equals(nombre)// Si existe, con el nombre indicado y pass en la posicion
				&& listaUsuarios.get(sitio).getPassword().equals(pass)) { 
			return true;// si nombre es IGUAL a nombre & CONTRASEÑA es IGUAL a CONTRASEÑA >true
		}
		return false;
	}

	public static boolean seguridadPass(String pass, String pass1) {
		if (pass.equals(pass1)) { //Comprobación de seguridad (doble check)
			return true;
		} else {
			return false;
		}
	}
	
	
	public static void establecerBloqueo(ArrayList<String> blocks, ArrayList<Usuario> listaUsuarios) {
		for (int i = 0; i < listaUsuarios.size(); i++) {
			String user_block = listaUsuarios.get(i).getNombre();
			for (String bloqueado : blocks) {
				if (listaUsuarios.get(i).getNombre().equals(bloqueado)) { //Buscamos igualdad de nombre entre blocks y listaUsuarios
					listaUsuarios.get(i).setIntentos(3);
				}
			}
		}
	}

}
