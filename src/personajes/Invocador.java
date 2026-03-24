package personajes;

import java.util.ArrayList;
import java.util.List;
import enemigos.Enemigo;

public class Invocador extends Personaje {
    private int capacidadMaxima;
    private List<Subdito> listaSubditos;
    private int bonoExtra;

    public Invocador(String nombre, int nivel, int vidaMaxima, int vidaActual, int defensa, int danio,
            int capacidadMaxima) {
        super(nombre, nivel, vidaMaxima, vidaActual, defensa, danio);
        if (capacidadMaxima < 0)
            throw new IllegalArgumentException("La capacidad maxima debe ser mayor o igual a 0");
        if (capacidadMaxima == 0 || capacidadMaxima > 10) {
            System.out
                    .println("La capacidad maxima es muy baja o muy alta, se ajustara a un valor predeterminado de 5");
            capacidadMaxima = 5;
        }
        this.capacidadMaxima = capacidadMaxima;
        this.listaSubditos = new ArrayList<>();
        this.bonoExtra = 5;
    }

    @Override
    public void atacar(Enemigo e) {
        if (!estaVivo()) {
            System.out.println("El invocador " + getNombre() + " está muerto y no puede atacar.");
            return;
        }

        if (e == null) {
            System.out.println("No hay enemigo objetivo para atacar.");
            return;
        }

        int danioTotal = getDanio() + this.bonoExtra;

        if (getArma() != null) {
            if (getArma().estaRota()) {
                System.out.println("El arma " + getArma().getNombre() + " esta rota, no puede ser usada para atacar.");
                return;
            } else {
                danioTotal += getArma().getDanio();
                getArma().usar();
            }
        }

        System.out.println(getNombre() + " esta atacando al enemigo " + e.getNombre());

        for (Subdito s : listaSubditos) {
            s.atacar(e);
        }

        e.recibirDanio(danioTotal);
    }

    public void invocar(Subdito nuevoSubdito) {
        if (!estaVivo()) {
            System.out.println("El invocador " + getNombre() + " está muerto y no puede invocar subditos.");
            return;
        }
        if (nuevoSubdito == null) {
            System.out.println("No puedes invocar un subdito nulo.");
            return;
        }
        if (listaSubditos.size() >= capacidadMaxima) {
            System.out.println("Capacidad maxima alcanzada, no puedes invocar mas.");
            return;
        }

        listaSubditos.add(nuevoSubdito);
        System.out.println("Subdito invocado con exito. Subditos actuales: " + listaSubditos.size());
    }

    public void invocar(String nombre, int nivel, int vidaMaxima, int vidaActual, int defensa, int danio) {
        Subdito nuevoSubdito = new Subdito(nombre, nivel, vidaMaxima, vidaActual, defensa, danio);
        invocar(nuevoSubdito);
    }

    public int getCantidadSubditos() {
        return listaSubditos.size();
    }

    @Override
    public void bloquear() {
        if (!estaVivo()) {
            return;
        }
        activarBloqueo();
        System.out.println(getNombre() + " entra en guardia junto a " + listaSubditos.size() + " subditos.");
    }

    @Override
    public void recibirDanio(int daño) {
        if (!estaVivo()) {
            return;
        }

        aplicarDanioRecibido(daño);
    }

    @Override
    public boolean estaVivo() {
        return vidaActual > 0;
    }

    @Override
    public String toString() {
        return "Invocador{" +
                "nombre='" + getNombre() + '\'' +
                ", nivel=" + getNivel() +
                ", vidaActual=" + getVidaActual() +
                ", vidaMaxima=" + getVidaMaxima() +
                ", defensa=" + defensa +
                ", daño=" + getDanio() +
                ", capacidadMaxima=" + capacidadMaxima +
                ", subditosInvocados=" + listaSubditos.size() +
                '}';
    }
}
