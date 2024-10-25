package miproyecto;

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
            System.out.println("4. Salir");
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
                running = false;
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
