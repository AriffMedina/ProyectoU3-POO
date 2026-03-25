package rpg;

import enemigos.*;

public class GestorEnemigos {
    public static Enemigo generarEnemigo(int nivel) {
        int random = (int) (1 + Math.random() * 3); // Genera un número aleatorio entre 1 y 3

        switch (random) {
            case 1:
                return new Esqueleto(nivel);
            case 2:
                return new Dragon(nivel);
            case 3:
                return new Zombie(nivel);
            default:
                return null;
        }
    }
}
