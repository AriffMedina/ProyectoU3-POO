package rpg;

import personajes.*;
import enemigos.Enemigo;
import items.Arma;
import items.Armadura;
import items.Consumible;
import items.Item;

public class JuegoServicio {

    private Personaje jugador;
    private GestorCombate accion;

    public JuegoServicio() {
        System.out.println("======== Bienvenido al Juego de Rol RPG ========\n");
        accion = new GestorCombate();

    }

    public void crearJugador(int opcion) {
        switch (opcion) {
            case 1:
                jugador = new Arquero("Legolas", 1, 100, 100, 12, 8, 6, 20);
                System.out.println("Has elegido a Legolas, el Arquero.");

                break;
            case 2:
                jugador = new Mago("Gandalf", 1, 80, 80, 6, 12, 10, 20);
                System.out.println("Has elegido a Gandalf, el Mago.");
                break;
            case 3:
                jugador = new Peleador("Conan", 1, 120, 120, 15, 6, 8, 10);
                System.out.println("Has elegido a Conan, el Peleador.");
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }
        System.out.println("¡Bienvenido al juego, " + jugador.getNombre() + "! Prepárate para la aventura.");
    }

    public void sortearItem() {
        if (jugador == null) {
            System.out.println("Primero crea un personaje.");
            return;
        }

        Item i = GestorItems.generarItem();
        System.out.println("Obtuviste: " + i.getNombre());
        i.equiparEn(jugador);
    }

    public void iniciarCombate() {
        if (jugador == null) {
            System.out.println("Primero debes crear tu personaje para iniciar un combate.");
            return;
        }
        Enemigo enemigo = GestorEnemigos.generarEnemigo(jugador.getNivel());
        accion.iniciar(jugador, enemigo);
    }

    public void usarConsumible() {
        if (jugador == null) {
            System.out.println("Primero crea un personaje.");
            return;
        }
        accion.usarConsumible(jugador);
    }

    public void guardarPartida() {
        if (jugador == null) {
            System.out.println("No hay partida para guardar. Crea un personaje primero.");
            return;
        }
        System.out.println("Partida guardada exitosamente para " + jugador.getNombre() + ".");
    }

    public void buscarItem(String nombre) {
    if (jugador == null) {
        System.out.println("Primero crea un personaje");
        return;
    }
    boolean encontrado = false;

    for (Arma arm : jugador.getArmas()) {
        if (arm.getNombre().equalsIgnoreCase(nombre)) {
            System.out.println("Item encontrado (Arma): " + arm);
            encontrado = true;
        }
    }
    for (Armadura a : jugador.getArmaduras()) {
        if (a.getNombre().equalsIgnoreCase(nombre)) {
            System.out.println("Item encontrado (Armadura): " + a);
            encontrado = true;
        }
    }
    for (Consumible c : jugador.getConsumibles()) {
        if (c.getNombre().equalsIgnoreCase(nombre)) {
            System.out.println("Item encontrado (Consumible): " + c);
            encontrado = true;
        }
    }

    if (!encontrado) {
        System.out.println("No se encontro ningun item con el nombre: " + nombre);
    }
}

}