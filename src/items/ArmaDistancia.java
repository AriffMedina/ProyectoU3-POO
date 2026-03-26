package items;

import Excepciones.ArmaRotaException;

public class ArmaDistancia extends Arma {
    private int municion;
    private double precision;

    public ArmaDistancia(String nombre, int cantidad, int danio, int durabilidad, int municion, double precision) {
        super(nombre, cantidad, danio, durabilidad);
        this.municion = (municion < 0) ? 0 : municion;
        this.precision = Math.max(0, Math.min(1, precision));
    }

    @Override
    public void usar() throws ArmaRotaException {
        if (getCantidad() <= 0)
            throw new IllegalStateException("No tienes " + nombre + " para usar.");
        if (estaRota()) {
            throw new ArmaRotaException(
                    "El arma " + nombre
                            + " se encuentra los suficiente desgastada para considerarse rota y su actual durabilidad es "
                            + durabilidad);
        }
        if (municion <= 0) {
            System.out.println("No tienes munición para usar el arma " + nombre + ".");
            return;
        }
        municion--;
        reducirDurabilidad(1);
        boolean acierto = Math.random() <= precision;
        if (acierto) {
            System.out.println("Usaste el arma " + nombre + ". Impacto exitoso. Daño: " + danio
                    + ". Durabilidad restante: " + durabilidad + ". Munición restante: " + municion);
            return;
        }
        System.out.println("Usaste el arma " + nombre + " pero fallaste el disparo. Durabilidad restante: "
                + durabilidad + ". Munición restante: " + municion);
    }

    public int getMunicion() {
        return municion;
    }

    public double getPrecision() {
        return precision;
    }

    @Override
    public void reparar(int cantidad) {
        if (cantidad < 0)
            throw new IllegalArgumentException("La cantidad a reparar no puede ser negativa");
        durabilidad += cantidad;
        System.out.println("Reparaste el arma " + nombre + ". Durabilidad actual: " + durabilidad);
    }

    @Override
    public String toCSV(String propietarioString) {
        return propietarioString + "ArmaDistancia," + nombre + "," + cantidad + "," + danio + "," + durabilidad + ","
                + municion + "," + precision;
    }

    public static Arma fromCSV(String linea) {

        // Validación
        String[] partes = linea.split(",");
        if (partes.length < 8) {
            throw new IllegalArgumentException("Línea inválida, se esperan 8 campos: " + linea);
        }

        return new ArmaDistancia(partes[2], Integer.parseInt(partes[3]), Integer.parseInt(partes[4]),
                Integer.parseInt(partes[5]), Integer.parseInt(partes[6]), Double.parseDouble(partes[7]));
    }

    @Override
    public String toString() {
        return "|ArmaDistancia{" +
                "\n|— nombre='" + nombre + '\'' +
                "\n|— cantidad=" + cantidad +
                "\n|— danio=" + danio +
                "\n|— durabilidad=" + durabilidad +
                "\n|— municion=" + municion +
                "\n|— precision=" + precision +
                '\n' + '}';
    }
}
