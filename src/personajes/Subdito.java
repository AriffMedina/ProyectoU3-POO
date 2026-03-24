package personajes;

import enemigos.Enemigo;

public class Subdito extends Personaje {

    public Subdito(String nombre, int nivel, int vidaMaxima, int vidaActual, int defensa, int danio) {
        super(nombre, nivel, vidaMaxima, vidaActual, defensa, danio);
    }

    @Override
    public void atacar(Enemigo e) {
        if (!estaVivo()) {
            System.out.println("El subdito " + getNombre() + " esta muerto y no puede atacar.");
            return;
        }
        if (e == null) {
            System.out.println("No hay enemigo objetivo para atacar.");
            return;
        }

        int danioTotal = getDanio();
        if (getArma() != null && !getArma().estaRota()) {
            danioTotal += getArma().getDanio();
            System.out.println("El subdito " + getNombre() + " ataca con " + getArma().getNombre() + " a " + e.getNombre());
            getArma().usar();
        } else {
            System.out.println("El subdito " + getNombre() + " ataca sin arma a " + e.getNombre());
        }

        e.recibirDanio(danioTotal);
    }

    @Override
    public void bloquear() {
        if (!estaVivo()) {
            return;
        }
        activarBloqueo();
        System.out.println("El subdito " + getNombre() + " se pone en guardia.");
    }

    @Override
    public void recibirDanio(int danio) {
        if (!estaVivo()) {
            return;
        }

        aplicarDanioRecibido(danio);
    }

    @Override
    public boolean estaVivo() {
        return vidaActual > 0;
    }

    @Override
    public String toString() {
        return "Subdito{" +
                "nombre='" + getNombre() + '\'' +
                ", nivel=" + getNivel() +
                ", vidaActual=" + getVidaActual() +
                ", vidaMaxima=" + getVidaMaxima() +
                ", defensa=" + defensa +
                ", dañoo=" + getDanio() +
                '}';
    }
}
