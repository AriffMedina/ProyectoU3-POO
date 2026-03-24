package rpg;

import personajes.*;
import enemigos.Enemigo;
import items.Item;


public class Juego {

    private Personaje jugador;
    private RPG rng;
    private Combate accion;
    

    public Juego() {
        System.out.println("======== Bienvenido al Juego de Rol RPG ========\n");
        rng = new RPG();
        accion = new Combate();
    
    }

    public void crearJugador(int opcion){
        switch (opcion){
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
            case 4:
                jugador = new Invocador("Morgana", 1, 90, 90, 8, 10, 3);
                System.out.println("Has elegido a Morgana, el Invocador.");
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }
        System.out.println("¡Bienvenido al juego, " + jugador.getNombre() + "! Prepárate para la aventura.");
    }



    public void sortearItem(){
        if(jugador == null){
            System.out.println("Primero crea un personaje.");
            return;
        }

        Item i = rng.generarItem();
        System.out.println("Obtuviste: " + i.getNombre());
        i.equiparEn(jugador);
    }

    public void iniciarCombate(){
        if(jugador == null){
            System.out.println("Primero debes crear tu personaje para iniciar un combate.");
            return;
        }
        Enemigo enemigo = rng.generarEnemigo(jugador.getNivel());
        accion.iniciar(jugador, enemigo);
    }

    public void usarConsumible(){
        if(jugador == null){
            System.out.println("Primero crea un personaje.");
            return;
        }
        accion.usarConsumible(jugador);
    }

    public void guardarPartida(){
        if(jugador == null){
            System.out.println("No hay partida para guardar. Crea un personaje primero.");
            return;
        }
        System.out.println("Partida guardada exitosamente para " + jugador.getNombre() + ".");
    }
}