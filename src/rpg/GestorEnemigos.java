package rpg;

import enemigos.Enemigo;
import java.util.ArrayList;
import java.util.List;

public class GestorEnemigos {
    private List<Enemigo> listaEnemigos;

    public GestorEnemigos() {
        this.listaEnemigos = new ArrayList<>();
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
