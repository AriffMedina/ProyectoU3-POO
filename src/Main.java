import personajes.*;
import enemigos.*;
import items.*;
import rpg.*;
import Excepciones.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("========== DEMO ENTREGA 2 ==========\n");

        Personaje legolas = new Arquero("Legolas", 1, 100, 100, 12, 8, 6, 20);
        Personaje gandalf = new Mago("Gandalf", 1, 80, 80, 2, 12, 10, 20);
        Personaje conan = new Peleador("Conan", 1, 120, 120, 3, 6, 8, 10);

        System.out.println(legolas);
        System.out.println(gandalf);
        System.out.println(conan);

        System.out.println("\n--- Equipando items ---");
        ArmaMelee daga = new ArmaMelee("Daga", 1, 10, 10, 5);
        legolas.setArma(daga);
        conan.setArma(new ArmaMelee("Hacha", 1, 20, 15, 8));
        gandalf.setArma(new ArmaMelee("Baston", 1, 5, 2, 3));

        System.out.println("\n--- Probando ManaInsuficienteException ---");
        Mago magoSinMana = new Mago("Saruman", 1, 80, 80, 2, 12, 5, 20);
        magoSinMana.setArma(new ArmaMelee("Baston oscuro", 1, 10, 10, 5));
        Enemigo esqueleto = new Esqueleto(1);
        try {
            magoSinMana.atacar(esqueleto);
        } catch (ManaInsuficienteException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n--- Probando ArmaRotaException ---");
        ArmaMelee armaRota = new ArmaMelee("Espada oxidada", 1, 0, 10, 5);
        try {
            armaRota.usar();
        } catch (ArmaRotaException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n--- GestorEnemigos: búsqueda y filtrado ---");
        GestorEnemigos gestor = new GestorEnemigos();
        gestor.agregarEnemigo(new Esqueleto(1));
        gestor.agregarEnemigo(new Dragon(3));
        gestor.agregarEnemigo(new Zombie(2));
        gestor.agregarEnemigo(new Dragon(5));

        Enemigo encontrado = gestor.buscarPorNombre("Dragon");
        System.out.println("Búsqueda por nombre: " + (encontrado != null ? encontrado : "No encontrado"));

        System.out.println("Filtrado nivel >= 3:");
        for (Enemigo e : gestor.filtrarPorNivel(3)) {
            System.out.println("  " + e);
        }

        System.out.println("\n--- Combate: Legolas vs Esqueleto ---");
        GestorCombate combate = new GestorCombate();
        combate.iniciar(legolas, new Esqueleto(10));
    }
}