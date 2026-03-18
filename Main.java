import java.util.List;
import java.util.Scanner;

/**
 * Gestiona la interacción con el usuario mediante un menú en consola
 * que permite consultar y manipular un inventario de productos.
 * </p>
 *
 * <p>El usuario puede:</p>
 * <ul>
 *     <li>Agregar productos según su categoría.</li>
 *     <li>Consultar la categoría de un producto.</li>
 *     <li>Ver su colección personal de productos.</li>
 *     <li>Ver la colección ordenada por categoría.</li>
 *     <li>Ver todo el inventario original.</li>
 *     <li>Ver el inventario ordenado por categoría.</li>
 * </ul>
 *
 * <p>
 * El programa también permite elegir la implementación de {@link java.util.Map}
 * que se usará para almacenar la colección del usuario.
 * </p>
 */
public class Main {

    /**
     * Método de entrada del programa.
     * <p>
     * Solicita al usuario elegir un tipo de mapa, carga los productos desde un archivo,
     * crea el inventario y muestra un menú interactivo para realizar distintas operaciones.
     * </p>
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione tipo de mapa:");
        System.out.println("1. HashMap");
        System.out.println("2. TreeMap");
        System.out.println("3. LinkedHashMap");

        int tipoMapa = scanner.nextInt();
        scanner.nextLine();

        if (tipoMapa < 1 || tipoMapa > 3) {
            System.out.println("Tipo inválido, se usará HashMap por defecto.");
            tipoMapa = 1;
        }

        TxtManager txt = new TxtManager();
        txt.cargarProductos("ListadoProducto.txt");

        Inventario inv = new Inventario(
                txt.getPorNombre(),
                txt.getPorCategoria(),
                tipoMapa
        );

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Agregar producto por categoría");
            System.out.println("2. Mostrar categoría de un producto");
            System.out.println("3. Mostrar colección del usuario");
            System.out.println("4. Mostrar colección ordenada por categoría");
            System.out.println("5. Mostrar todo el inventario");
            System.out.println("6. Mostrar inventario ordenado por categoría");
            System.out.println("7. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    System.out.print("Ingrese categoría: ");
                    String categoria = scanner.nextLine();

                    List<Producto> lista = txt.getPorCategoria().get(inv.stripDiacritics(categoria));

                    if (lista == null) {
                        System.out.println("Categoría no existe.");
                        break;
                    }

                    System.out.println("Productos disponibles:");
                    for (Producto p : lista) {
                        System.out.println("- " + p.getNombre());
                    }

                    System.out.print("Ingrese producto: ");
                    String nombre = scanner.nextLine();

                    Producto producto = txt.getPorNombre().get(inv.stripDiacritics(nombre));

                    if (producto == null || !inv.stripDiacritics(producto.getCategoria()).equalsIgnoreCase(inv.stripDiacritics(categoria))) {
                        System.out.println("Producto inválido para esa categoría.");
                        break;
                    }

                    inv.agregarProducto(nombre);
                    System.out.println("Producto agregado.");
                    break;

                case 2:
                    System.out.print("Ingrese nombre del producto: ");
                    nombre = scanner.nextLine();
                    inv.mostrarCategoriaDeProducto(nombre);
                    break;

                case 3:
                    inv.mostrarColeccion();
                    break;

                case 4:
                    inv.mostrarColeccionOrdenadaPorCategoria();
                    break;

                case 5:
                    inv.mostrarTodo();
                    break;

                case 6:
                    inv.mostrarTodoOrdenadoPorCategoria();
                    break;

                case 7:
                    System.out.println("Hasta pronto");
                    return;

                default:
                    System.out.println("Opción inválida");
            }
        }
    }
}