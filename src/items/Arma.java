package items;

import personajes.Personaje;
import excepciones.ArmaRotaException;
import interfaces.Durable;

public abstract class Arma extends Item implements Durable {
    protected int danio;
    protected int durabilidad;

    public Arma(String nombre, int cantidad, int danio, int durabilidad) {
        super(nombre, cantidad);
        if (danio < 0)
            throw new IllegalArgumentException("El daño no puede ser negativo");
        if (durabilidad < 0)
            throw new IllegalArgumentException("La durabilidad no puede ser negativa");
        this.danio = danio;
        this.durabilidad = durabilidad;
    }

    @Override
    public void usar() throws ArmaRotaException {
        if (getCantidad() <= 0)
            throw new IllegalStateException("No tienes " + nombre + " para usar.");
        if (durabilidad <= 0) {
            throw new ArmaRotaException(
                    "El arma " + nombre
                            + " se encuentra los suficiente desgastada para considerarse rota y su actual durabilidad es "
                            + durabilidad);
        }
        durabilidad--;
        System.out.println("Usaste el arma " + nombre + ". Daño: " + danio + ". Durabilidad restante: " + durabilidad);
    }

    public int getDanio() {
        return danio;
    }

    public int getDurabilidad() {
        return durabilidad;
    }

    public void reducirDurabilidad(int cantidad) {
        if (cantidad < 0)
            throw new IllegalArgumentException("La cantidad a reducir no puede ser negativa");
        durabilidad -= cantidad;
        if (durabilidad < 0)
            durabilidad = 0;
    }

    public boolean estaRota() {
        return durabilidad <= 0;
    }

    public String getArma() {
        return nombre;
    }

    @Override
    public void equiparEn(Personaje p) {
        p.setArma(this);
        System.out.println(" Arma equipada: " + nombre);
    }

    public abstract String toCSV();

    @Override
    public String toString() {
        return "Arma{" +
                "nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", danio=" + danio +
                ", durabilidad=" + durabilidad +
                '}';
    }
}
