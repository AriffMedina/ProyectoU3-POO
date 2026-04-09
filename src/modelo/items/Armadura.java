package items;

import interfaces.Durable;
import personajes.Personaje;

public class Armadura extends Item implements Durable {
    private int defensa;
    private int durabilidad;
    private final int durabilidadMaxima;

    public Armadura(String nombre, int cantidad, int defensa, int durabilidad) {
        super(nombre, cantidad);
        if (defensa < 0)
            throw new IllegalArgumentException("La defensa no puede ser negativa");
        if (durabilidad < 0)
            throw new IllegalArgumentException("La durabilidad no puede ser negativa");
        this.defensa = defensa;
        this.durabilidad = durabilidad;
        this.durabilidadMaxima = durabilidad;
    }

    @Override
    public void usar() {
        if (getCantidad() <= 0)
            throw new IllegalStateException("No tienes " + nombre + " para usar.");
        if (estaRota()) {
            System.out.println("La armadura " + nombre + " está rota y no puede ser usada.");
            return;
        }
        reducirDurabilidad(1);
        System.out.println(
                "Usaste la armadura " + nombre + ". Defensa: " + defensa + ". Durabilidad restante: " + durabilidad);
    }

    public int getDefensa() {
        return defensa;
    }

    public int getDurabilidad() {
        return durabilidad;
    }

    @Override
    public void reducirDurabilidad(int cantidad) {
        if (cantidad < 0)
            throw new IllegalArgumentException("La cantidad a reducir no puede ser negativa");
        durabilidad -= cantidad;
        if (durabilidad < 0)
            durabilidad = 0;
    }

    @Override
    public void reparar(int cantidad) {
        if (cantidad < 0)
            throw new IllegalArgumentException("La cantidad a reparar no puede ser negativa");
        durabilidad += cantidad;
        if (durabilidad > durabilidadMaxima)
            durabilidad = durabilidadMaxima;
    }

    @Override
    public boolean estaRota() {
        return durabilidad <= 0;
    }

    @Override
    public void equiparEn(Personaje p) {
        p.setArmadura(this);
        System.out.println("> Armadura equipada: " + nombre);
    }

    @Override
    public String toCSV() {
        return "armadura," + super.toCSV() + "," + defensa + "," + durabilidad;
    }

    public static Armadura fromCSV(String linea) {
        String[] partes = linea.split(",");
        if (partes.length < 6) {
            throw new IllegalArgumentException("Línea inválida para armadura: " + linea);
        }
        try {
            return new Armadura(partes[2], Integer.parseInt(partes[3]), Integer.parseInt(partes[4]),
                    Integer.parseInt(partes[5]));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Formato de número incorrecto en: " + linea, e);
        }
    }

    @Override
    public String toString() {
        return "|Armadura{" +
                "\n|- nombre='" + nombre + '\'' +
                "\n|- cantidad=" + cantidad +
                "\n|- defensa=" + defensa +
                "\n|- durabilidad=" + durabilidad +
                "\n|- durabilidadMaxima=" + durabilidadMaxima +
                '\n' + '}';
    }
}
