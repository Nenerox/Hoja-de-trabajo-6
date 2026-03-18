import java.util.Scanner;

/**
 * Clase principal del programa. 
 * <p>
 * Se encarga de interactuar con el usuario, permitir la selección del tipo de mapa 
 * a utilizar, cargar los productos desde un archivo de texto y ofrecer un menú para 
 * gestionar el inventario.
 * </p>
 *
 * <p>
 * A través de esta clase se coordinan las operaciones principales del sistema:
 * </p>
 * <ul>
 *     <li>Seleccionar la implementación de {@code Map}.</li>
 *     <li>Cargar productos mediante {@link TxtManager}.</li>
 *     <li>Crear un {@link Inventario}.</li>
 *     <li>Realizar operaciones como agregar productos, mostrar categorías, etc.</li>
 * </ul>
 */
public class Main {

    /**
     * Método de entrada del programa.
     * <p>
     * Despliega un menú interactivo que permite al usuario gestionar un inventario
     * de productos utilizando diferentes implementaciones de mapas.
     * </p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Selección de mapa
        System.out.println("Seleccione tipo de mapa:");
        System.out.println("1. HashMap");
        System.out.println("2. TreeMap");
        System.out.println("3. LinkedHashMap");

        int tipoMapa = scanner.nextInt();
        scanner.nextLine();

        TxtManager txt = new TxtManager();
        txt.cargarProductos("Hoja-de-trabajo-6/ListadoProducto.txt");

        Inventario inv = new Inventario(
            txt.getPorNombre(),
            txt.getPorCategoria(),
            tipoMapa
        );

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Mostrar colección");
            System.out.println("3. Mostrar categorías");
            System.out.println("4. Mostrar productos por categoría");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    System.out.print("Nombre del producto: ");
                    String nombre = scanner.nextLine();
                    inv.agregarProducto(nombre);
                    break;

                case 2:
                    inv.mostrarColeccion();
                    break;

                case 3:
                    inv.mostrarCategorias();
                    break;

                case 4:
                    System.out.print("Categoría: ");
                    String categoria = scanner.nextLine();
                    inv.mostrarPorCategoria(categoria);
                    break;

                case 5:
                    System.out.println("Hasta pronto");
                    return;

                default:
                    System.out.println("Opción inválida");
            }
        }
    }
}