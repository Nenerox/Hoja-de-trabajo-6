import java.util.Map;
import java.util.Scanner;

public class Main {
    Factory factory = new Factory();
    Scanner scanner = new Scanner(System.in);

    System.out.println("Que tipo de implementacion de mapa desea: \n 1. HashMap \n 2. TreeMap \n 3. LinkedHashMap");
    int tipo = scanner.nextInt();

    Map<String, Producto> m = factory.crearMapa(tipo);   
}
