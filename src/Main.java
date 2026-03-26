import java.util.Scanner;

import enemigos.Enemigo;
import enemigos.Esqueleto;
import excepciones.ArmaRotaException;
import excepciones.ManaInsuficienteException;
import items.ArmaMelee;
import persistencia.RepositorioArchivo;
import personajes.Mago;
import rpg.JuegoServicio;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static JuegoServicio juego;

    public static void main(String[] args) {
        RepositorioArchivo repositorio = new RepositorioArchivo();
        juego = new JuegoServicio(repositorio);

        mostrarMenu();
        sc.close();
    }

    public static void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n========== MENU RPG ==========");
            System.out.println("1. Crear personaje");
            System.out.println("2. Sortear item");
            System.out.println("3. Mostrar inventario");
            System.out.println("4. Iniciar combate");
            System.out.println("5. Guardar partida");
            System.out.println("6. Cargar partida");
            System.out.println("7. Filtrado y búsqueda ");
            System.out.println("8. Probar excepciones");
            System.out.println("9. Salir");
            System.out.print(">> Elige una opcion: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    int opcion2;
                    do {
                        System.out.println("=== Elige un personaje ===");
                        System.out.println("1. Arquero");
                        System.out.println("2. Mago");
                        System.out.println("3. Peleador");
                        System.out.print(">> Elige una opcion: ");
                        opcion2 = sc.nextInt();
                        juego.crearJugador(opcion2);
                    } while (opcion2 < 1 || opcion2 > 3);
                    break;
                case 2:
                    juego.sortearItem();
                    break;
                case 3:
                    juego.mostrarInventario();
                    break;
                case 4:
                    juego.iniciarCombate();
                    break;
                case 5:
                    juego.guardarPartida();
                    break;
                case 6:
                    juego.cargarPartida();
                    break;
                case 7:
                    filtradoMenu();
                    break;
                case 8:
                    probarExcepciones();
                    break;
                case 9:
                    System.out.println("Saliendo ...");
                    System.out.println("Gracias por jugar!");
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        } while (opcion != 8);

    }

    public static void filtradoMenu() {
        System.out.println("1. Enemigos ");
        System.out.println("2. Items");
        System.out.println("3. Salir");
        System.out.print(">> Elige una opcion: ");
        int opcion = sc.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("1. Buscar por nombre");
                System.out.println("2. Filtrar por Nivel");
                int opcion2 = sc.nextInt();
                if (opcion2 == 1) {
                    System.out.println(">> Ingrese el nombre del enemigo: ");
                    String nombre = sc.next();
                    juego.buscarPorNombre(nombre);
                } else if (opcion2 == 2) {
                    System.out.println(">> Ingrese el nivel del enemigo: ");
                    int nivel = sc.nextInt();
                    juego.filtrarPorNivel(nivel);
                }
                break;
            case 2:
                System.out.println("1. Buscar por nombre");
                System.out.println("2. Eliminar Item");
                int opcion3 = sc.nextInt();
                if (opcion3 == 1) {
                    System.out.println(">> Ingrese el nombre del item: ");
                    String nombre = sc.nextLine();
                    juego.buscarItem(nombre);
                } else if (opcion3 == 2) {
                    System.out.println(" >> Ingrese el nombre del item a eliminar: ");
                    String nombre = sc.nextLine();
                    juego.eliminarItem(nombre);
                }
                break;
            default:
                System.out.println("Opcion no valida.");
                break;
        }
    }

    public static void probarExcepciones() {
        // 1) ManaInsuficienteException
        Mago magoSinMana = new Mago("Saruman", 1, 80, 80, 2, 12, 5, 20);
        magoSinMana.setArma(new ArmaMelee("Baston oscuro", 1, 10, 10, 5));
        Enemigo esqueleto = new Esqueleto(1);

        try {
            magoSinMana.atacar(esqueleto);
        } catch (ManaInsuficienteException e) {
            System.out.println("ManaInsuficienteException capturada: " + e.getMessage());
        }

        // 2) ArmaRotaException
        ArmaMelee armaRota = new ArmaMelee("Espada oxidada", 1, 0, 5, 5);

        try {
            armaRota.usar();
        } catch (ArmaRotaException e) {
            System.out.println("ArmaRotaException capturada: " + e.getMessage());
        }

        System.out.println("=== FIN PRUEBA EXCEPCIONES ===\n");
    }

}
