import java.util.Scanner;

import enemigos.Enemigo;
import enemigos.Esqueleto;
import excepciones.ArmaRotaException;
import excepciones.ManaInsuficienteException;
import items.ArmaMelee;
import persistencia.RepositorioArchivo;
import personajes.Mago;
import rpg.GestorEnemigos;
import rpg.JuegoServicio;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        JuegoServicio juego = new JuegoServicio(new RepositorioArchivo());

        int opcion = -1;
        while (opcion != 0) {
            imprimirMenu();
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("1=Arquero, 2=Mago, 3=Peleador: ");
                    int personaje = sc.nextInt();
                    juego.crearJugador(personaje);
                    break;
                case 2:
                    juego.sortearItem();
                    break;
                case 3:
                    juego.iniciarCombate();
                    break;
                case 4:
                    GestorEnemigos.generarEnemigo(10);
                    break;
                case 5:
                    juego.usarConsumible();
                    break;
                case 6:
                    juego.guardarPartida();
                    break;
                case 7:
                    juego.cargarPartida();
                    break;
                case 8:
                    System.out.print("Nombre del item a buscar: ");
                    sc.nextLine();
                    juego.buscarItem(sc.nextLine());
                    break;
                case 9:
                    System.out.print("Nombre del item a eliminar: ");
                    sc.nextLine();
                    juego.eliminarItem(sc.nextLine());
                    break;
                case 10:
                    juego.filtrarArmas();
                    break;
                case 11:
                    PruebaExcepciones();
                    break;
                case 12:
                    juego.mostrarInventario();
                    break;
                case 0:
                    System.out.println("Saliendo del juego...");
                    System.out.println("Gracias por jugar!");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }

        }
        sc.close();
    }

    private static void imprimirMenu() {
        System.out.println("\n========== MENU RPG ==========");
        System.out.println("1. Crear personaje");
        System.out.println("2. Sortear item");
        System.out.println("3. Iniciar combate");
        System.out.println("4. Generar Enemigo");
        System.out.println("5. Usar consumible");
        System.out.println("6. Guardar partida");
        System.out.println("7. Cargar partida");
        System.out.println("8. Buscar item");
        System.out.println("9. Eliminar item");
        System.out.println("10. Filtrar armas disponibles");
        System.out.println("11. Probar excepciones");
        System.out.println("12. Mostrar inventario");
        System.out.println("0. Salir");
        System.out.print(">> Elige una opcion: ");
    }

    private static void PruebaExcepciones() {
        Mago magoSinMana = new Mago("Saruman", 1, 80, 80, 2, 12, 5, 20);
        magoSinMana.setArma(new ArmaMelee("Baston oscuro", 1, 10, 10, 5));
        Enemigo esqueleto = new Esqueleto(1);
        try {
            magoSinMana.atacar(esqueleto);
        } catch (ManaInsuficienteException e) {
            System.out.println("ManaInsuficienteException capturada: " + e.getMessage());
        }

        ArmaMelee armaRota = new ArmaMelee("Espada oxidada", 1, 0, 5, 5);
        try {
            armaRota.usar();
        } catch (ArmaRotaException e) {
            System.out.println("ArmaRotaException capturada: " + e.getMessage());
        }
    }
}
