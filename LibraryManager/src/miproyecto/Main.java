package miproyecto;

import java.io.File;
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
			int create = scanner.nextInt();
			if(create == 1) {
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
	        System.out.println("1. Crear archivo (si no existe)");
	        System.out.println("2. Guardar libros en archivo");
	        System.out.println("3. Leer libros desde archivo");
	        System.out.println("4. Eliminar archivo (!!!)");
	        System.out.println("5. Volver al menú principal");
	        int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
            case 1:
                // Llama a la función para crear el archivo
                break;
            case 2:
                // Llama a la función para guardar libros en archivo
                break;
            case 3:
                // Llama a la función para leer y mostrar libros del archivo
                break;
            case 4:
                // Llama a la función para eliminar el archivo
                break;
            case 5:
                managingFiles = false;
                break;
            default:
                System.out.println("Opción no válida.");
            }
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
