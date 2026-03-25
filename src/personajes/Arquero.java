package personajes;

import Excepciones.ArmaRotaException;
import Excepciones.ManaInsuficienteException;
import enemigos.Enemigo;

public class Arquero extends Personaje {
    private int precision;
    private int agilidad;

    public Arquero(String nombre, int nivel, int vidaMaxima, int vidaActual, int defensa, int danio, int precision,
            int agilidad) {
        super(nombre, nivel, vidaMaxima, vidaActual, defensa, danio);
        if (precision < 0) {
            throw new IllegalArgumentException("La precision no puede ser negativa.");
        }
        if (agilidad < 0) {
            throw new IllegalArgumentException("La agilidad no puede ser negativa.");
        }
        this.precision = precision;
        this.agilidad = agilidad;
    }

    @Override
    public void atacar(Enemigo e) throws ManaInsuficienteException {
        if (!estaVivo()) {
            System.out.println("El arquero " + getNombre()
                    + " intento bloquear el ataque con el arma " + getArma()
                    + "   pero la diosa abandona este mundo, dejandolo morir por un ataque del enemigo"
                    + getNombre());
            return;
        }

        if (e == null) {
            System.out.println("No hay enemigo objetivo para atacar.");
            return;
        }

        int danioTotal = getDanio() + precision + agilidad;
        if (getArma() != null) {
            if (getArma().estaRota()) {
                System.out.println("El arma " + getArma().getNombre() + " esta rota, no puede ser usada para atacar.");
                return;
            } else {
                danioTotal += getArma().getDanio();
                System.out.println(getNombre() + " esta atacando con un " + getArma().getNombre() + " al enemigo "
                        + e.getNombre());
                try {
                    getArma().usar();
                } catch (ArmaRotaException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } else {
            System.out.println(getNombre() + " esta atacando sin arma al enemigo " + e.getNombre());
        }
        e.recibirDanio(danioTotal);

    }

    @Override
    public void bloquear() {
        if (!estaVivo()) {
            System.out.println("El arquero " + getNombre() + " está muerto y no puede bloquear.");
            return;
        }

        activarBloqueo();
        System.out.println(getNombre() + " adopta una posicion defensiva para reducir el daño.");
    }

    @Override
    public boolean estaVivo() {
        if (getVidaActual() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Arquero{" +
                "nombre='" + getNombre() + '\'' +
                ", nivel=" + getNivel() +
                ", vidaActual=" + getVidaActual() +
                ", vidaMaxima=" + getVidaMaxima() +
                ", defensa=" + getDefensa() +
                ", daño=" + getDanio() +
                ", precision=" + precision +
                ", agilidad=" + agilidad +
                ", arma=" + getArma() +
                ", armadura=" + getArmaduras() +
                '}';
    }
}
