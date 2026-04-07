package personajes;

import enemigos.Enemigo;
import excepciones.ArmaRotaException;
import excepciones.ManaInsuficienteException;

public class Arquero extends Personaje {
    private int precision;
    private int agilidad;

    public Arquero(String nombre, int nivel, int vidaMaxima, int vidaActual, int defensa, int danio, int precision,
            int agilidad) {
        super(nombre, nivel, vidaMaxima, vidaActual, defensa, danio);
        if (precision < 0)
            throw new IllegalArgumentException("La precision no puede ser negativa.");
        if (agilidad < 0)
            throw new IllegalArgumentException("La agilidad no puede ser negativa.");
        this.precision = precision;
        this.agilidad = agilidad;
    }

    @Override
    public String toCSV() {
        return "Arquero," + super.toCSV() + "," + precision + "," + agilidad;
    }

    public static Arquero fromCSV(String linea) {
        String[] partes = linea.split(",");
        if (partes.length < 9)
            throw new IllegalArgumentException("Línea inválida para Arquero: " + linea);
        try {
            return new Arquero(
                    partes[1],
                    Integer.parseInt(partes[2]),
                    Integer.parseInt(partes[3]),
                    Integer.parseInt(partes[4]),
                    Integer.parseInt(partes[5]),
                    Integer.parseInt(partes[6]),
                    Integer.parseInt(partes[7]),
                    Integer.parseInt(partes[8]));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error de número en Arquero: " + linea, e);
        }
    }

    @Override
    public void atacar(Enemigo e) throws ManaInsuficienteException {
        if (!estaVivo()) {
            System.out.println("El arquero " + getNombre() + " murió intentando atacar.");
            return;
        }
        if (e == null)
            return;

        int danioTotal = getDanio() + precision + agilidad;
        if (getArma() != null) {
            if (getArma().estaRota()) {
                System.out.println("El arma " + getArma().getNombre() + " esta rota.");
                return;
            } else {
                danioTotal += getArma().getDanio();
                try {
                    getArma().usar();
                } catch (ArmaRotaException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        e.recibirDanio(danioTotal);
    }

    @Override
    public void bloquear() {
        if (!estaVivo())
            return;
        activarBloqueo();
        System.out.println(getNombre() + " adopta una posicion defensiva.");
    }

    @Override
    public boolean estaVivo() {
        return getVidaActual() > 0;
    }

    @Override
    public String toString() {
        return "Arquero{" + "nombre='" + getNombre() + "', precision=" + precision + ", agilidad=" + agilidad + '}';
    }
}
