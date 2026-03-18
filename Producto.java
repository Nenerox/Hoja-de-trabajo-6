import java.text.Normalizer;
import java.text.Normalizer.Form;

/**
 * Representa un producto con un nombre y una categoría.
 * <p>
 * Esta clase implementa {@link Comparable} para permitir su comparación primero por nombre y luego por 
 * categoría. También redefine
 * {@code equals}, {@code hashCode} y {@code toString}
 * </p>
 */
public class Producto implements Comparable<Producto> {

    private String nombre;
    private String categoria;

    /**
     * Construye un nuevo producto con su nombre y categoría.
     *
     * @param nombre    nombre del producto
     * @param categoria categoría a la que pertenece el producto
     */
    public Producto(String nombre, String categoria)
    {
        this.nombre = nombre;
        this.categoria = categoria;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return el nombre del producto
     */
    public String getNombre()
    {
        return this.nombre;
    }

    /**
     * Obtiene la categoría del producto.
     *
     * @return la categoría del producto
     */
    public String getCategoria()
    {
        return this.categoria;
    }

    /**
     * Determina si este producto es igual a otro objeto.
     * <p>
     * Dos productos se consideran iguales si tienen el mismo nombre
     * y pertenecen a la misma categoría.
     * </p>
     *
     * @param objeto objeto con el cual comparar
     * @return {@code true} si ambos representan el mismo producto,
     *         {@code false} en caso contrario
     */
    @Override
    public boolean equals(Object objeto)
    {
        if(this == objeto)
        {
            return true;
        }
        if(objeto == null || getClass() != objeto.getClass())
        {
            return false;
        }
        Producto otro = (Producto) objeto;
        return (stripDiacritics(nombre.strip())).equalsIgnoreCase(stripDiacritics(otro.nombre.strip())) && (stripDiacritics(categoria.strip())).equalsIgnoreCase(stripDiacritics(otro.categoria.strip()));
    }
    
    /**
     * Transofrma el string ingresado a una version NFD donde se eliminan los acentos para una mejor comparacion
     * 
     * @param s String orignial
     * @return Version del string normalizada sin acentos
     */
    private String stripDiacritics(String s) {
        String normalized = Normalizer.normalize(s, Form.NFD);
        String normal = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return normal.toLowerCase().trim();
    }

    /**
     * Calcula un código hash basado en el nombre y categoría del producto.
     *
     * @return valor hash del producto
     */
    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(nombre, categoria);
    }

    /**
     * Compara este producto con otro para fines de ordenamiento.
     * <p>
     * La comparación se realiza primero por nombre y, si éstos coinciden,
     * por categoría.
     * </p>
     *
     * @param otro el producto con el cual comparar
     * @return un valor negativo, cero o positivo según este producto sea menor, igual o mayor al otro
     */
    @Override
    public int compareTo(Producto otro)
    {
        int comparar = (stripDiacritics(this.nombre.strip())).compareTo(stripDiacritics(otro.nombre.strip()));
        if(comparar != 0)
        {
            return comparar;
        }
        return (stripDiacritics(this.categoria.strip())).compareTo(stripDiacritics(otro.categoria.strip()));
    }

    /**
     * Devuelve una representación textual del producto.
     *
     * @return una cadena en el formato: {@code categoria - nombre}
     */
    @Override
    public String toString()
    {
        return categoria + " - " + nombre;
    }
}