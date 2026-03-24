package items;

import personajes.Personaje;

public abstract class Item {
    protected String nombre;
    protected int cantidad;

    public Item(String nombre, int cantidad){
        if(nombre == null || nombre.isEmpty()) throw new IllegalArgumentException("El item debe de tener un nombre");
        if(cantidad <0) throw new IllegalArgumentException("La cantidad no puede ser negativa");
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public abstract void usar();
    
    public abstract void equiparEn(Personaje p);
       
    @Override
    public String toString() {
        return "Item{" +
                "nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}
