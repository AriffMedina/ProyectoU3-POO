package rpg;

import enemigos.*;
import java.util.ArrayList;
import java.util.List;

public class GestorEnemigos {
    private List<Enemigo> listaEnemigos;

    public GestorEnemigos() {
        this.listaEnemigos = new ArrayList<>();
    }

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

    public void agregarEnemigo(Enemigo e) {
        if (e != null) {
            listaEnemigos.add(e);
        }
    }

    public Enemigo buscarPorNombre(String nombre) {
        for (Enemigo e : listaEnemigos) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        return null;
    }

    public List<Enemigo> filtrarPorNivel(int nivelMinimo) {
        List<Enemigo> filtrados = new ArrayList<>();
        for (Enemigo e : listaEnemigos) {
            if (e.getNivel() >= nivelMinimo) {
                filtrados.add(e);
            }
        }
        return filtrados;
    }
}
