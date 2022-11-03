package dam2.add.p11;

import java.util.ArrayList;
import java.util.Scanner;

public class FuncionesAdmin {

	public static boolean comprobarAdmin(String userName) { // comprobar nombre admin
		if (userName.equals("admin")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean comprobarAdminPass() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce contraseña: ");
		String pass_a = sc.next();
		if (pass_a.equals("admin")) { // Comprobar pass admin
			System.out.println("Introduce contraseña otra vez: ");
			String pass_b = sc.next();
			if (pass_b.equals(pass_a)) { // doble check
				System.out.println("HOLA admin");
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static void intentosTodos0(ArrayList<Usuario> listaUsuarios) {
		for (int i = 0; i < listaUsuarios.size(); i++) {
			listaUsuarios.get(i).setIntentos(0);//TODOS intentos a 0
		}
	}

	public static String listaBloqueo(ArrayList<String> blocks) {
		String listablock = null;
		for (int i = 0; i < blocks.size(); i++) {
			listablock = blocks.get(i).toString();
		}
		return listablock;
	}

	public static void imprimirUsuariosIntentos(ArrayList<Usuario> listaUsuarios) { //Imprime pos usuario bloqueado
		for (int i = 0; i < listaUsuarios.size(); i++) {
			if (listaUsuarios.get(i).getIntentos() == 3) {
				System.out.print("BLOQUEADO ->");
				System.out.println(" POSICION-> " + i + " - " + listaUsuarios.get(i).getNombre()); 
			} 
		}
	}
	
	public static int posBloqueo(String userName, ArrayList<String> blocks) {
		for (int i = 0; i < blocks.size(); i++) { // recorremos el arraylist
			if (blocks.get(i).toString().equals(userName)) {
				return i; // retorna i (la posicion)
			}
		}
		return -1; // Si no existe, retorna -1
	}
	
	public static void menuAdmin() {
		System.out.println("1-Desbloquear usuarios");
		System.out.println("2-Desbloquear TODOS usuarios");
		System.out.println("0-Atras");
	}
}
