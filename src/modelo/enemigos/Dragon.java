package enemigos;

import personajes.Personaje;

public class Dragon extends Enemigo {
    public Dragon(int nivel) {
        super("Dragón Eterno", nivel, nivel * 100, nivel * 100);
    }

    @Override
    public void atacar(Personaje personaje) {
        int danio = getNivel() * 20;
        System.out.println("El Dragón exhala una ráfaga de fuego.");
        personaje.recibirDanio(danio);
    }
}