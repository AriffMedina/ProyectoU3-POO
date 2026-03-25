package enemigos;

import personajes.Personaje;

public class Esqueleto extends Enemigo {
    public Esqueleto(int nivel) {
        super("Esqueleto", nivel, nivel * 10, nivel * 10);
    }

    @Override
    public void atacar(Personaje personaje) {
        int danio = getNivel() * 5;
        System.out.println("El Esqueleto dispara una flecha de hueso.");
        personaje.recibirDanio(danio);
    }
}