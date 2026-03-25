package personajes;

import Excepciones.ManaInsuficienteException;
import enemigos.Enemigo;

public class Mago extends Personaje {
    private int mana;
    private int manaMaximo;

    public Mago(String nombre, int nivel, int vidaMaxima, int vidaActual, int defensa, int danio, int mana,
            int manaMaximo) {
        super(nombre, nivel, vidaMaxima, vidaActual, defensa, danio);
        if (manaMaximo <= 0) {
            throw new IllegalArgumentException("El mana maximo debe ser mayor a 0");
        }

        if (mana < 0 || mana > manaMaximo) {
            throw new IllegalArgumentException("El mana debe estar entre 0 y el mana maximo");
        }

        this.mana = mana;
        this.manaMaximo = manaMaximo;
    }

    @Override
    public void atacar(Enemigo e) throws ManaInsuficienteException {
        if (!estaVivo()) {
            System.out.println("El mago " + getNombre()
                    + " intento bloquear el ataque con el arma " + getArma()
                    + "   pero la diosa abandona este mundo, dejandolo morir por un ataque del enemigo"
                    + getNombre());
            return;
        }

        if (getArma() == null) {
            System.out.println(getNombre() + " no tiene un arma equipada y no puede atacar.");
            return;
        }

        if (mana < 10) {
            throw new ManaInsuficienteException("No tienes sufciente mana para atacar y tu mana es " + mana);
        }

        int danioTotal = getDanio() + getArma().getDanio() + mana;
        System.out.println(getNombre() + " ataca con " + getArma().getNombre() + " y su mana a " + e.getNombre());
        e.recibirDanio(danioTotal);
        mana -= 10;
    }

    public void recargarMana(int recarga) {
        if (mana + recarga > manaMaximo) {
            System.out.println("No puedes recargar mas mana en tu corazon de mana porque genera un desbordamiento");
            mana = manaMaximo;
        } else {
            mana += recarga;
            System.out.println("Tu corazon de mana vuelve a llenarse");
        }
        System.out.println("El mago " + getNombre() + " recargó su maná.");
        System.out.println("\nMana actual: " + mana + "/" + manaMaximo);
    }

    @Override
    public void bloquear() {
        if (!estaVivo()) {
            System.out.println("El mago " + getNombre() + " está muerto y no puede bloquear.");
            return;
        }
        activarBloqueo();
        if (defensa >= danio) {
            System.out.println(getNombre() + " pasa a la defensa y logra bloquear el ataque.");
        } else {
            System.out.println(getNombre() + " intenta bloquear pero su defensa es insuficiente.");
        }
    }

    @Override
    public void recibirDanio(int daño) {
        if (!estaVivo()) {
            return;
        }

        aplicarDanioRecibido(daño);
    }

    @Override
    public boolean estaVivo() {
        if (vidaActual == 0) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mago{" +
                "nombre='" + getNombre() + '\'' +
                ", nivel=" + getNivel() +
                ", vidaActual=" + getVidaActual() +
                ", vidaMaxima=" + getVidaMaxima() +
                ", defensa=" + defensa +
                ", daño=" + getDanio() +
                ", mana=" + mana +
                "/" + manaMaximo +
                '}';
    }
}
