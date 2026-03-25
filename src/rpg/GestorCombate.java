package rpg;

import personajes.*;
import Excepciones.ManaInsuficienteException;
import enemigos.Enemigo;
import items.Consumible;

public class GestorCombate {
    private static final int max_turnos = 50;

    public void iniciar(Personaje p, Enemigo e) {
        if (p == null || e == null) {
            System.out.println("No se puede iniciar el combate sin un personaje o enemigo válido.");
            return;
        }

        System.out.println("¡El combate ha comenzado entre " + p.getNombre() + " y " + e.getNombre() + "!");
        int turnos = 0;

        while (p.estaVivo() && e.estaVivo() && turnos < max_turnos) {
            turnoJugador(p, e);

            if (e.estaVivo()) {
                calcularTurnoEnemigo(e, p);
            }

            turnos++;
        }

        if (!p.estaVivo()) {
            System.out.println(
                    p.getNombre() + " ha sido derrotado por " + e.getNombre() + ". ¡Mejor suerte la próxima vez!");
        } else if (!e.estaVivo()) {
            System.out.println(p.getNombre() + " ha ganado el combate y obtiene " + e.getExperienciaOtorgada()
                    + " puntos de experiencia.");
        } else {
            System.out.println("El combate terminó sin vencedor tras " + max_turnos + " turnos.");
        }

    }

    public void turnoJugador(Personaje p, Enemigo e) {
        System.out.println("Es el turno de " + p.getNombre() + ". Ataca a " + e.getNombre() + "!");
        try {
            p.atacar(e);
        } catch (ManaInsuficienteException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void usarConsumible(Personaje p) {
        Consumible c = p.getConsumibles().get(0);
        if (c == null) {
            System.out.println("No tienes ningún consumible para usar.");
            return;
        }
        System.out.println("Usando consumible: " + c.getNombre() + " cura " + c.getCurativo() + " puntos de vida.");
        int vidaAntes = p.getVidaActual();
        int vidaNueva = c.curar(vidaAntes, p.getVidaMaxima());
        p.setVidaActual(vidaNueva);

        System.out.println("Vida restaurada: " + vidaAntes + " -> " + vidaNueva);
    }

    public void calcularTurnoEnemigo(Enemigo e, Personaje p) {
        System.out.println("Es el turno de " + e.getNombre() + ". El enemigo ataca a " + p.getNombre() + "!");
        e.atacar(p);
    }

    public void realizarBloqueo(Personaje p, Enemigo e) {
        if (p == null)
            return;
        System.out.println(p.getNombre() + " decide bloquear el siguiente ataque de "
                + (e != null ? e.getNombre() : "el enemigo") + ".");
        p.bloquear();
    }

}