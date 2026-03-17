import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TxtManager txt = new TxtManager();
        txt.cargarProductos("ListadoProducto.txt");

        Inventario sistema = new Inventario(txt.getPorNombre());

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Agregar producto");
            System.out.println("2. Mostrar colección");
            System.out.println("3. Salir");
            int opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del producto: ");
                    String nombre = sc.nextLine();
                    sistema.agregarProducto(nombre);
                    break;

                case 2:
                    sistema.mostrarColeccion();
                    break;

                case 3:
                    System.out.println("Vuelve pronto");
                    return;

                default:
                    System.out.println("Opción inválida");
            }
        }
    }
}