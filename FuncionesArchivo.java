package dam2.add.p11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FuncionesArchivo {

	public static File rutaArchivo() {
		File archivo_original = new File("src" + File.separator + "dam2" + File.separator + "add" + File.separator
				+ "p11" + File.separator + "acceso.txt"); // RUTA ORIGINAL
		return archivo_original;
	}
	
	public static File rutaBlock() {
		File rutaBlock = new File("src" + File.separator + "dam2" + File.separator + "add" + File.separator
				+ "p11" + File.separator + "block.txt"); // RUTA ORIGINAL
		return rutaBlock;
	}

	public static boolean compruebaArchivo(File f) {
		if (f.exists()) { // Si existe, dejará continuar
			return true;
		} else {
			System.out.println("Error al cargar archivo");
			System.exit(0);// Si no cerramos programa
			return false;
		}
	}

	public static void extraerLineas(File archivo_original, ArrayList<String> usuarios) {
		FileReader fr = null;
		try {
			fr = new FileReader(archivo_original);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		String linea;
		try {
			while ((linea = br.readLine()) != null) {
				usuarios.add(linea); // Cada linea leída diferente a null agrega un String
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void extraerBlocks(File block, ArrayList<String> blocks) {
		FileReader fr = null;
		try {
			fr = new FileReader(block);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		String linea;
		try {
			while ((linea = br.readLine()) != null) {
				blocks.add(linea); // Cada linea leída diferente a null agrega un String
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArrayList<String> leerArchivo(File f) {
		List<String> lista = new ArrayList<>(); //Creamos lista - ArrayList
		try (FileReader fr = new FileReader(f); BufferedReader br = new BufferedReader(fr)) {
			String linea;
			while ((linea = br.readLine()) != null) {
				lista.add(linea); // Cada linea leída agrega un String
			}
			String[] arr = new String[lista.size()]; // Copiar el contenido de la lista a un Array de Strings,
			for (int i = 0; i < lista.size(); i++) {
				arr[i] = lista.get(i);
			}
			return (ArrayList<String>) lista;// Devuelve ArrayList de Strings
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void escribirUsuario(String user, String pass) {
		FileWriter fw = null;
		try {
			fw = new FileWriter("src" + File.separator + "dam2" + File.separator + "add" + File.separator + "p11"
					+ File.separator + "acceso.txt", true); // Flag true para escribir a continuación
			fw.write("\n");
			fw.write(user + ":" + pass);//Escribe usuario nuevo en el formato original
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void escribirBloqueoUsuario(String userBlock) {
		FileWriter fw = null;
		try {
			fw = new FileWriter("src" + File.separator + "dam2" + File.separator + "add" + File.separator + "p11"
					+ File.separator + "block.txt", true); // Flag true para escribir a continuación			
			fw.write(userBlock+"\n");//Escribe usuario nuevo en el formato original
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void logNuevo(String user, Date date) {
		FileWriter fw = null;
		try {
			fw = new FileWriter("src" + File.separator + "dam2" + File.separator + "add" + File.separator + "p11"
					+ File.separator + "login.log", true); // Flag true para escribir a continuación
			fw.write("\n");
			fw.write("Nuevo Usuario: " + user + " -- " + date);//Escribe que usuario nuevo se ha registrado y cuando
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void logError(String user, Date date) {
		FileWriter fw = null;
		try {
			fw = new FileWriter("src" + File.separator + "dam2" + File.separator + "add" + File.separator + "p11"
					+ File.separator + "login.log", true); // Flag true para escribir a continuación
			fw.write("\n");
			fw.write("ERROR login: " + user + " -- " + date);//Escribe que usuario ha introduciod error y cuando
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void logDesbloqueo(String user, Date date) {
		FileWriter fw = null;
		try {
			fw = new FileWriter("src" + File.separator + "dam2" + File.separator + "add" + File.separator + "p11"
					+ File.separator + "login.log", true); // Flag true para escribir a continuación
			fw.write("\n");
			fw.write("DESBLOQUEO login: " + user + " -- " + date);//Escribe que usuario ha introduciod error y cuando
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void logLoginOk(String user, Date date) {
		FileWriter fw = null;
		try {
			fw = new FileWriter("src" + File.separator + "dam2" + File.separator + "add" + File.separator + "p11"
					+ File.separator + "login.log", true); // Flag true para escribir a continuación
			fw.write("\n");
			fw.write("Login OK: " + user + " -- " + date);//Escribe que usuario ha sido logeado y cuando
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void logBloqueo(String user, Date date) {
		FileWriter fw = null;
		try {
			fw = new FileWriter("src" + File.separator + "dam2" + File.separator + "add" + File.separator + "p11"
					+ File.separator + "login.log", true); // Flag true para escribir a continuación
			fw.write("\n");
			fw.write("BLOQUEO login: " + user + " -- " + date); //Escribe que usuario ha sido bloqueado y cuando
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void fileWriterBlock(File archivo_block, ArrayList<String> blocks) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(archivo_block, false);
			for (int i = 0; i < blocks.size(); i++) {
				String userbloqueado = blocks.get(i).toString();
				fw.write(userbloqueado + "\n");
			}
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
