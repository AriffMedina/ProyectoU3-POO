package enemigos;
import personajes.Personaje;
public class Zombie extends Enemigo {
    public Zombie(int nivel) {
        super("Zombie", nivel, nivel * 3, nivel * 3);
    }

    @Override
    public void atacar(Personaje personaje) {
        int danio = getNivel() * 4;
        System.out.println("El Zombie lanza un mordisco infectado.");
        personaje.recibirDanio(danio);
    }
}