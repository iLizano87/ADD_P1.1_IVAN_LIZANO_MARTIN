package dam2.add.p11;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MainP11 {

	public static void main(String[] args) {
		Date date = new Date();
		File archivo_original = FuncionesArchivo.rutaArchivo();// Archivo acceso
		File archivo_block = FuncionesArchivo.rutaBlock();// Archivo bloqueos

		if (FuncionesArchivo.compruebaArchivo(archivo_original)) {// Comprobamos archivo
			System.out.println("Archivo OK");
			System.out.println(date);
		} else {
			System.err.println("NO SE CARGO ARCHIVO ORIGINAL");// Si no se cargase archivo original
			System.exit(0);// Sistema se cierra
		}
		ArrayList<String> blocks = new ArrayList<String>();// ArrayList bloqueos (persistencia datos)
		ArrayList<String> usuarios = new ArrayList<String>(); // ArrayList para importar usuarios archivo TXT
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();// ArrayList donde guardaremos
																	// usuarios/pass/intentos
		FuncionesArchivo.extraerLineas(archivo_original, usuarios);

		FuncionesUsuario.extraerUsuarios(usuarios, listaUsuarios);

		FuncionesArchivo.extraerBlocks(archivo_block, blocks);  //extrae del fichero bloqueo a arraylist blocks
		FuncionesUsuario.establecerBloqueo(blocks, listaUsuarios); //crea igualdad entre nombre blocks y nombre listaUsuarios->setIntentos(3)

		Scanner sc = new Scanner(System.in);
		int opcion;
		boolean salir = false;
		Scanner eleccion = new Scanner(System.in);
		do {
			pintaMenu();
			opcion = eleccion.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("Inserta nombre usuario:");
				String user = sc.next();
				if (FuncionesAdmin.comprobarAdmin(user)) { // Comprueba si es admin
					if (FuncionesAdmin.comprobarAdminPass()) { // Comprueba pass admin
						FuncionesArchivo.logLoginOk(user, date); // Log OK
						int opcion1;
						boolean salir1 = false;
						Scanner eleccion1 = new Scanner(System.in);
						do {
							FuncionesAdmin.menuAdmin();
							opcion1 = eleccion1.nextInt();
							switch (opcion1) {
							case 1:
								if (!blocks.isEmpty()) {
									FuncionesAdmin.imprimirUsuariosIntentos(listaUsuarios); // Lista intentos usuarios
									Scanner scblock = new Scanner(System.in);
									System.out.println("Posicion a desbloquear: "); // seleccion de posicion
									int desbloquea_lista = scblock.nextInt();
									if (listaUsuarios.get(desbloquea_lista).getIntentos() == 0) { // Si intentos 0 -
																									// vuelve atras
										System.out.println("Inserta opción valida");
										break;
									} else {
										listaUsuarios.get(desbloquea_lista).setIntentos(0); // Intentos a 0
										String usuario_desblock = listaUsuarios.get(desbloquea_lista).getNombre();
										int desbloquea_blocks = FuncionesAdmin.posBloqueo(usuario_desblock, blocks);
										blocks.remove(desbloquea_blocks); // se elimina de arraylist de bloqueos
										FuncionesArchivo.fileWriterBlock(archivo_block, blocks); // se escribe todo el
																									// arraylist de
																									// bloqueos en
																									// archivo block
										FuncionesArchivo.logDesbloqueo(usuario_desblock, date); // Log desbloqueo
										System.out.println("Usuario " + listaUsuarios.get(desbloquea_lista).getNombre()
												+ " desbloqueado");
										break;
									}
								} else {
									System.out.println("No hay bloqueos");
									break;
								}
							case 2:
								if (!blocks.isEmpty()) {// Si arrayList blocks no esta vacio
									blocks.clear();// limpia arraylist blocks
									FuncionesArchivo.fileWriterBlock(archivo_block, blocks); // Se escribe
																								// contenido(vacio)arrayList
																								// blocks
									FuncionesAdmin.intentosTodos0(listaUsuarios); // Todos setIntentos(0)
									FuncionesArchivo.logDesbloqueo("DESBLOQUEO TOTAL", date);// Log desbloqueo
									System.out.println("Todos usuarios desbloqueados!");
									break;
								} else {
									System.out.println("No hay bloqueos");
									break;
								}
							case 0:
								salir1 = true;
							default:
							}
						} while (!salir1);

						break;
					} else {
						System.err.println("Error de contraseña");
						FuncionesArchivo.logError(user, date); // Se escribe en log
						break;
					}
				} else {
					FuncionesUsuario.comprobarUsuario(user, listaUsuarios);
					int posicion = FuncionesUsuario.posUsuario(user, listaUsuarios);
					if (posicion == -1) { // Si la posicion devuelve -1 porque no existe
						System.out.println("Nuevo registro - Usuario: " + user); // REGISTRO USUARIO
						System.out.println("Introduce contraseña:");
						Scanner sc1 = new Scanner(System.in);
						String pass_a = sc1.next();
						String pass_b;
						do {
							System.out.println("Introduce contraseña de nuevo:");
							pass_b = sc1.next();
						} while (!pass_a.equals(pass_b)); // mientras no se cumpla doble check se repite

						if (pass_a.equals(pass_b)) {
							listaUsuarios.add(new Usuario(user, pass_a, 0));
							System.out.println("Nuevo Usuario: " + user);
							FuncionesUsuario.nuevoUsuario(date, listaUsuarios, user, pass_a);// Agregamos nuevoUsuario
						}
						break;
					} else if (listaUsuarios.get(posicion).getIntentos() == 3) { // Si tiene 3 intentos
						System.err.println("Usuario bloqueado"); // Bloqueado
						FuncionesArchivo.logBloqueo(user, date); // log BLOQUEO
						blocks.add(user);// agregamos a ArrayList el Bloqueo
						break;
					} else {
						if (FuncionesUsuario.comprobarUsuario(user, listaUsuarios)) {
							String pass_a;
							String pass_b;
							do {
								System.out.println("Inserta contraseña:");
								pass_a = sc.next();
								if (pass_a.equals(listaUsuarios.get(posicion).getPassword())) {
									do {
										System.out.println("Inserta contraseña otra vez:");
										pass_b = sc.next();
										if (pass_b.equals(pass_a)) {
											System.out.println("Hola " + user);
											listaUsuarios.get(posicion).setIntentos(0);// Si acierta, intentos a 0
											FuncionesArchivo.logLoginOk(user, date);
										} else {
											System.err.println("ERROR - No coinciden contraseñas");
											listaUsuarios.get(posicion).intentos++;
											FuncionesArchivo.logError(user, date);
											if (listaUsuarios.get(posicion).getIntentos() == 3) {
												break;
											}
										}
									} while (!pass_a.equals(pass_b));// mientras no se cumpla doble check se repite
								} else {
									System.err.println("ERROR - Contraseña erronea");
									listaUsuarios.get(posicion).intentos++;
									FuncionesArchivo.logError(user, date);
								}
								if (listaUsuarios.get(posicion).getIntentos() == 3) {
									System.err.println("Usuario Bloqueado");
									FuncionesArchivo.logBloqueo(user, date);// Log Bloqueo
									String bloqueo = user;
									FuncionesArchivo.escribirBloqueoUsuario(bloqueo); // escribe bloqueo en block.txt
									blocks.add(user); // agrega a arrayList bloqueo
									break;
								}
							} while (!FuncionesUsuario.seguridadPass(pass_a,
									listaUsuarios.get(posicion).getPassword()));
							{
								break;
							}
						}
					}
				}
				break;
			case 2:
				System.exit(0);
			default:
				break;
			}
		} while (!salir);

	}

	public static void pintaMenu() {
		System.out.println("menu");
		System.out.println("1-Identificarse");
		System.out.println("2-Salir");
	}

}
