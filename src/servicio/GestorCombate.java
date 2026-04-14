package servicio;

import personajes.*;
import enemigos.Enemigo;
import excepciones.ManaInsuficienteException;
import items.Consumible;
import java.util.Scanner;

public class GestorCombate {
    private static final int max_turnos = 50;
    private final Scanner sc = new Scanner(System.in);

    public void iniciar(Personaje p, Enemigo e) {
        if (p == null || e == null) {
            System.out.println("No se puede iniciar el combate sin un personaje o enemigo válido.");
            return;
        }

        System.out.println("\n¡El combate ha comenzado entre " + p.getNombre() + " y " + e.getNombre() + "!");
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
        boolean turnoCompletado = false;

        while (!turnoCompletado) {
            System.out.println("\n--- Turno de " + p.getNombre() + " ---");
            System.out.println("1. Atacar");
            System.out.println("2. Bloquear");
            System.out.println("3. Usar consumible");
            System.out.println("4. Estado");
            System.out.print(">> Elige una opción: ");

            int opcion;
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException error) {
                System.out.println("Opción no válida. Debes ingresar un número.");
                continue;
            }

            switch (opcion) {
                case 1:
                    try {
                        p.atacar(e);
                        turnoCompletado = true;
                    } catch (ManaInsuficienteException ex) {
                        System.out.println("No tienes suficiente mana para atacar. " + ex.getMessage());
                        System.out.println("¡Por favor, elige otra acción!");
                    }
                    break;
                case 2:
                    realizarBloqueo(p, e);
                    turnoCompletado = true;
                    break;
                case 3:
                    if (p.getConsumibles() == null || p.getConsumibles().isEmpty()) {
                        System.out.println("No tienes ningún consumible en tu inventario.");
                    } else {
                        usarConsumible(p);
                        turnoCompletado = true;
                    }
                    break;
                case 4:
                    System.out.println("\n===ESTADO===");
                    System.out.println(p.toString());
                    System.out.println(e.toString());
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    public void usarConsumible(Personaje p) {
        if (p.getConsumibles() == null || p.getConsumibles().isEmpty()) {
            System.out.println("No tienes ningún consumible para usar.");
            return;
        }

        Consumible c = p.getConsumibles().get(0);
        System.out.println("\nUsando consumible: " + c.getNombre() + " cura " + c.getCurativo() + " puntos de vida.");

        int vidaAntes = p.getVidaActual();
        int vidaNueva = c.curar(vidaAntes, p.getVidaMaxima());
        p.setVidaActual(vidaNueva);

        if (c.getCantidad() == 0) {
            p.getConsumibles().remove(0);
        }

        System.out.println("Vida restaurada: " + vidaAntes + " -> " + vidaNueva);
    }

    public void calcularTurnoEnemigo(Enemigo e, Personaje p) {
        System.out.println("\nEs el turno de " + e.getNombre() + ". ¡El enemigo ataca a " + p.getNombre() + "!");
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