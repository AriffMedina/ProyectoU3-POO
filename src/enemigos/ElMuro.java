package enemigos;

import personajes.Personaje;

public class ElMuro extends Enemigo {
    public ElMuro(int nivel) {
        super("El Muro", nivel, nivel * 6, nivel * 7);
    }

    @Override
    public void atacar(Personaje personaje) {
        int danio = getNivel() * 10;
        System.out.println("El Muro no se mueve, pero aplasta todo a su paso.");
        personaje.recibirDanio(danio);

    }
}