package miproyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	//ARRAY donde se van a guardar los libros
	private static ArrayList<Book> library = new ArrayList<>();
	
	public static void main(String[] args) {
		//Defino el menú para acceder a las opciones del programa
		//SCANNER se utiliza para que el usuario pueda realizar inputs para que el programa los detecte y almacene dentro de una variable
		Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while(running) {
        	System.out.println("\n--- Gestor de Biblioteca ---");
        	System.out.println("1. Añadir libro");
            System.out.println("2. Listar libros");
            System.out.println("3. Eliminar libro");
            //A la hora de MODIFICAR un ARCHIVO, tenemos que sobreescribir el archivo ya existente.
            System.out.println("\n--- Archivo ---");
            //Validar que exista un archivo, de lo contrario preguntar si se desea crear.
            System.out.println("4. Gestionar Archivo");
            System.out.println("\n--- Base de Datos ---");
            System.out.println("5. BD (más tarde)");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            
            switch (option) {
            case 1:
                addBook(scanner);
                break;
            case 2:
                listBooks();
                break;
            case 3:
                removeBook(scanner);
                break;
            case 4:
                submenuArchivo(scanner);
                break;
            case 6:
                running = false;
                break;
            default:
                System.out.println("Opción no válida.");
        }

            
        }
        
	}
	
	private static void submenuArchivo(Scanner scanner){
		//Le indico a Java que quiero manipular el siguiente archivo, ya sea para verificar su existencia o sobreescribirlo/borrarlo
		File file = new File("booklist_save.txt");
		//Verifico si existe, de lo contrario pregunto al usuario si quiere crearlo
		if(!file.exists()) {
			System.out.println("\n No encontramos un archivo guardado, quieres crearlo? (1. Yes / 2. No");
			System.out.print("Elige una opción: ");
			int confirm = scanner.nextInt();
			if(confirm == 1) {
				try {
			        if (file.createNewFile()) {
			            System.out.println("Archivo creado exitosamente.");
			        }
			    } catch (IOException e) {
			        System.out.println("Ocurrió un error al crear el archivo.");
			        e.printStackTrace();
			    }
			}else return;
		}
		
		//Continúo con el manejo de archivos
		boolean managingFiles = true;
		while(managingFiles) {
			System.out.println("\n--- Gestor de Archivo ---");
	        System.out.println("1. Guardar libros en archivo");
	        System.out.println("2. Leer libros desde archivo");
	        System.out.println("3. Eliminar archivo (!!!)");
	        System.out.println("4. Volver al menú principal");
	        System.out.print("Elige una opción: ");
	        int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
            case 1:
                saveOnFile();
                break;
            case 2:
                readFile(file);
                break;
            case 3:
                deleteFile(file, scanner);
                break;
            case 4:
                managingFiles = false;
                break;
            default:
                System.out.println("Opción no válida.");
            }
		}
	}
	
	private static void saveOnFile() {
		try {
			FileWriter writer = new FileWriter("booklist_save.txt");

			for(Book book : library) {
				writer.write(book.toString() + "\n");
			}
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void readFile(File file) {
		if (file.exists()) {
			try {
				BufferedReader reader = new BufferedReader (new FileReader("booklist_save.txt"));
				String line;
				while((line = reader.readLine()) != null) {
					System.out.println(line);
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("El archivo no existe.");
		}
	}
	
	private static void deleteFile(File file, Scanner scanner) {
		if(file.exists()) {
			System.out.print("¿Estás seguro de que deseas eliminar el archivo? (1. Sí / 2. No): ");
			System.out.print("Elige una opción: ");
			int confirm = scanner.nextInt();
			if (confirm == 1) {
	            if (file.delete()) {
	                System.out.println("El archivo ha sido eliminado.");
	            } else {
	                System.out.println("Error al eliminar el archivo.");
	            }
	        } else {
	            System.out.println("Operación cancelada.");
	            return;
	        }
		}else {
	        System.out.println("El archivo no existe.");
	    }
	}
	
	private static void addBook(Scanner scanner) {
		
		System.out.println("\n--- Añadir Libro ---");
        System.out.print("Título: ");
        String title = scanner.nextLine();
        
        //Acá valido que no exista un libro con el mismo nombre
        if(!library.isEmpty()) {
        	for(int i = 0; i < library.size(); i++) {
        		if(library.get(i).getTitle().equalsIgnoreCase(title)) {
        			System.out.println("El Título del Libro ya se encuentra Registrado. ");
        			return;
        		}
        	}
        }
        
        System.out.println("Autor: ");
        String author = scanner.nextLine();
        
        System.out.print("Año de publicación: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Categoría: ");
        int category = scanner.nextInt();
        scanner.nextLine();

        Book newBook = new Book(title, author, year, category);
        library.add(newBook);
        System.out.println("Libro añadido con éxito.");
        
	}
	
	private static void listBooks() {
		System.out.println("\n--- Lista de Libros ---");
        if (library.isEmpty()) {
            System.out.println("La biblioteca está vacía.");
        } else {
            for (Book book : library) {
                System.out.println(book);
            }
        }
	}
	
	private static void removeBook(Scanner scanner) {
		System.out.println("\n--- Eliminar Libro ---");
        System.out.print("Introduce el título del libro que deseas eliminar: ");
        String title = scanner.nextLine();

        for (int i = 0; i < library.size(); i++) {
            if (library.get(i).getTitle().equalsIgnoreCase(title)) {
                library.remove(i);
                System.out.println("Libro eliminado.");
                return;
            }
        }

        System.out.println("Libro no encontrado.");
	}

}
