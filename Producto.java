public class Producto implements Comparable<Producto>{
    private String nombre;
    private String categoria;
    
    public Producto(String nombre, String categoria)
    {
        this.nombre = nombre;
        this.categoria = categoria;
    }
    public String getNombre()
    {
        return this.nombre;
    }

    public String getCategoria()
    {
        return this.categoria;
    }
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
        return nombre.equals(otro.nombre)&&categoria.equals(otro.categoria);
    }
    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(nombre, categoria);
    }
    @Override
    public int compareTo(Producto otro)
    {
        int comparar = this.nombre.compareTo(otro.nombre);
        if(comparar != 0)
        {
            return comparar;
        }
        return this.categoria.compareTo(otro.categoria);
    }
    @Override
    public String toString()
    {
        return categoria + " - " + nombre;
    }
}