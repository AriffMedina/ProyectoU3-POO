package personajes;

import Excepciones.ManaInsuficienteException;
import enemigos.Enemigo;

public class Peleador extends Personaje {
    private int fuerza;
    private int resistencia;

    public Peleador(String nombre, int nivel, int vidaMaxima, int vidaActual, int defensa, int danio, int fuerza,
            int resistencia) {
        super(nombre, nivel, vidaMaxima, vidaActual, defensa, danio);
        if (fuerza < 0) {
            throw new IllegalArgumentException("La fuerza no puede ser negativa.");
        }
        if (resistencia < 0) {
            throw new IllegalArgumentException("La resistencia no puede ser negativa.");
        }
        this.fuerza = fuerza;
        this.resistencia = resistencia;
    }

    @Override
    public void atacar(Enemigo e) throws ManaInsuficienteException {
        if (!estaVivo()) {
            System.out.println("El peleador " + getNombre() + " está muerto y no puede atacar.");
            return;
        }
        if (getArma() == null) {
            System.out.println(getNombre() + " no tiene un arma equipada.");
            return;
        }
        int danioTotal = getDanio() + this.fuerza * 2 + getArma().getDanio();
        System.out.println(
                getNombre() + " está atacando con un " + getArma().getNombre() + " al enemigo " + e.getNombre());
        e.recibirDanio(danioTotal);
    }

    @Override
    public void bloquear() {
        if (!estaVivo()) {
            return;
        }
        activarBloqueo();
        if (getDefensa() + resistencia >= getDanio()) {
            System.out.println(getNombre() + " bloquea el ataque con su fuerza y resistencia.");
        } else {
            System.out.println(getNombre() + " intenta bloquear pero no puede absorber todo el daño.");
        }
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
        return "|Peleador{" +
                "\n|— nombre='" + getNombre() + '\'' +
                "\n|— nivel=" + getNivel() +
                "\n|— vidaMaxima=" + getVidaMaxima() +
                "\n|— vidaActual=" + getVidaActual() +
                "\n|— defensa=" + (getDefensa() + resistencia) +
                "\n|— daño=" + getDanio() +
                "\n|— fuerza=" + fuerza +
                "\n|— resistencia=" + resistencia +
                '}';
    }
}
