package enemigos;
import personajes.Personaje;
public class Slime extends Enemigo {
    public Slime(int nivel) {

        super("Slime", nivel, nivel * 2, nivel * 2);
    }

    @Override
    public void atacar(Personaje personaje) {
        int danio = getNivel() * 3;
        System.out.println("El Slime salta y atrapa de forma pegajosa.");
        personaje.recibirDanio(danio);
    }
}