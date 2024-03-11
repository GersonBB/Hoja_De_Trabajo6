package uvg.edu.gt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Crear el factory para seleccionar la implementación de Map
        MapFactory factory = selectMapFactory();

        // Crear el Map seleccionado por el usuario
        Map<String, String> cardMap = factory.createMap();

        // Imprimir la ruta absoluta del archivo antes de cargarlo
        String fileName = "C:\\Users\\gerso\\OneDrive\\Escritorio\\Hoja_De_Trabajo6\\src\\main\\java\\uvg\\edu\\gt\\cards_desc.txt";
        String filePath = new File(fileName).getAbsolutePath();
        System.out.println("Ruta absoluta del archivo: " + filePath);

        // Leer el archivo de cartas y cargarlas al Map
        cargarCartasDesdeArchivo(cardMap, fileName);

        Scanner scanner = new Scanner(System.in);

        // Listas para almacenar los tiempos de ejecución
        List<Long> tiemposInsercion = new ArrayList<>();
        List<Long> tiemposBusqueda = new ArrayList<>();

        // Menú de opciones para el usuario
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Agregar una carta a la colección");
            System.out.println("2. Mostrar el tipo de una carta específica");
            System.out.println("3. Mostrar las cartas en la colección");
            System.out.println("4. Mostrar las cartas ordenadas por tipo");
            System.out.println("5. Mostrar todas las cartas existentes");
            System.out.println("6. Mostrar todas las cartas existentes ordenadas por tipo");
            System.out.println("0. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            long inicio;
            long fin;
            long tiempo;

            switch (opcion) {
                case 1:
                    inicio = System.nanoTime();
                    agregarCarta(cardMap, scanner);
                    fin = System.nanoTime();
                    tiempo = fin - inicio;
                    tiemposInsercion.add(tiempo);
                    break;
                case 2:
                    inicio = System.nanoTime();
                    mostrarTipoCarta(cardMap, scanner);
                    fin = System.nanoTime();
                    tiempo = fin - inicio;
                    tiemposBusqueda.add(tiempo);
                    break;
                case 3:
                    mostrarCartasEnColeccion(cardMap);
                    break;
                case 4:
                    mostrarCartasOrdenadasPorTipo(cardMap);
                    break;
                case 5:
                    mostrarTodasLasCartas(cardMap);
                    break;
                case 6:
                    mostrarTodasLasCartasOrdenadasPorTipo(cardMap);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    // Imprimir los tiempos de ejecución
                    imprimirTiempos(tiemposInsercion, tiemposBusqueda);
                    return;
                default:
                    System.out.println("Opción inválida. Por favor seleccione una opción válida.");
            }
        }
    }

    private static void agregarCarta(Map<String, String> cardMap, Scanner scanner) {
        System.out.println("Ingrese el nombre de la carta que desea agregar:");
        String nombreCarta = scanner.nextLine().trim();

        System.out.println("Ingrese el tipo de la carta (monstruo, trampa o hechizo):");
        String tipoCarta = scanner.nextLine().trim();

        // Agregar la carta al Map
        cardMap.put(nombreCarta, tipoCarta);
        System.out.println("Carta agregada correctamente.");
    }

    private static void mostrarTipoCarta(Map<String, String> cardMap, Scanner scanner) {
        System.out.println("Ingrese el nombre de la carta:");
        String nombreCarta = scanner.nextLine().trim();

        String tipoCarta = cardMap.get(nombreCarta);
        if (tipoCarta != null) {
            System.out.println("Tipo de carta para " + nombreCarta + ": " + tipoCarta);
        } else {
            System.out.println("La carta no está en la colección.");
        }
    }

    private static void mostrarCartasEnColeccion(Map<String, String> cardMap) {
        System.out.println("Cartas en la colección:");
        for (Map.Entry<String, String> entry : cardMap.entrySet()) {
            System.out.println("Nombre: " + entry.getKey() + ", Tipo: " + entry.getValue());
        }
    }

    private static void mostrarCartasOrdenadasPorTipo(Map<String, String> cardMap) {
        System.out.println("Cartas en la colección ordenadas por tipo:");
        TreeMap<String, List<String>> cartasPorTipo = new TreeMap<>();
        for (Map.Entry<String, String> entry : cardMap.entrySet()) {
            String nombreCarta = entry.getKey();
            String tipoCarta = entry.getValue();
            cartasPorTipo.computeIfAbsent(tipoCarta, k -> new ArrayList<>()).add(nombreCarta);
        }
        for (Map.Entry<String, List<String>> entry : cartasPorTipo.entrySet()) {
            System.out.println("Tipo: " + entry.getKey());
            for (String nombreCarta : entry.getValue()) {
                System.out.println("- " + nombreCarta);
            }
        }
    }

    private static void mostrarTodasLasCartas(Map<String, String> cardMap) {
        System.out.println("Todas las cartas existentes:");
        for (String nombreCarta : cardMap.keySet()) {
            System.out.println(nombreCarta);
        }
    }

    private static void mostrarTodasLasCartasOrdenadasPorTipo(Map<String, String> cardMap) {
        System.out.println("Todas las cartas existentes ordenadas por tipo:");
        TreeMap<String, List<String>> cartasPorTipo = new TreeMap<>();
        for (Map.Entry<String, String> entry : cardMap.entrySet()) {
            String nombreCarta = entry.getKey();
            String tipoCarta = entry.getValue();
            cartasPorTipo.computeIfAbsent(tipoCarta, k -> new ArrayList<>()).add(nombreCarta);
        }
        for (Map.Entry<String, List<String>> entry : cartasPorTipo.entrySet()) {
            System.out.println("Tipo: " + entry.getKey());
            for (String nombreCarta : entry.getValue()) {
                System.out.println("- " + nombreCarta);
            }
        }
    }

    // Método para imprimir los tiempos de ejecución
    private static void imprimirTiempos(List<Long> tiemposInsercion, List<Long> tiemposBusqueda) {
        System.out.println("Tiempos de inserción:");
        for (int i = 0; i < tiemposInsercion.size(); i++) {
            System.out.println("Operación " + (i + 1) + ": " + tiemposInsercion.get(i) + " nanosegundos");
        }

        System.out.println("\nTiempos de búsqueda:");
        for (int i = 0; i < tiemposBusqueda.size(); i++) {
            System.out.println("Operación " + (i + 1) + ": " + tiemposBusqueda.get(i) + " nanosegundos");
        }
    }

    // Otros métodos...

    private static MapFactory selectMapFactory() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione el tipo de Map que desea utilizar:");
        System.out.println("1. HashMap");
        System.out.println("2. TreeMap");
        System.out.println("3. LinkedHashMap");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        switch (opcion) {
            case 1:
                return new HashMapFactory();
            case 2:
                return new TreeMapFactory();
            case 3:
                return new LinkedHashMapFactory();
            default:
                System.out.println("Opción inválida. Seleccionando HashMap por defecto.");
                return new HashMapFactory();
        }
    }

    private static void cargarCartasDesdeArchivo(Map<String, String> cardMap, String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    String nombreCarta = parts[0].trim();
                    String tipoCarta = parts[1].trim();
                    cardMap.put(nombreCarta, tipoCarta);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
        }
    }
}
