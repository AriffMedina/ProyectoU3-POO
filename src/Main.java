import java.util.Scanner;

import persistencia.RepositorioArchivo;
import rpg.JuegoServicio;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static JuegoServicio juego;

    public static void main(String[] args) {
        RepositorioArchivo repositorio = new RepositorioArchivo();
        juego = new JuegoServicio(repositorio);
        System.out.println("========== Bienvenido al Juego de Rol RPG =========");

        mostrarMenu();
        sc.close();
    }

    public static void mostrarMenu() {
        int opcion = 0;
        do {
            System.out.println("\n|========== MENU RPG ==========|");
            System.out.println("|1. Crear personaje            |");
            System.out.println("|2. Sortear item               |");
            System.out.println("|3. Mostrar inventario         |");
            System.out.println("|4. Iniciar combate            |");
            System.out.println("|5. Guardar partida            |");
            System.out.println("|6. Cargar partida             |");
            System.out.println("|7. Filtrado y búsqueda        |");
            System.out.println("|8. Probar excepciones         |");
            System.out.println("|9. Salir                      |");
            System.out.println("|==============================|");
            System.out.print(">> Elige una opcion: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("|!|: Entrada inválida. Por favor, ingresa un número.");
                opcion = 0;
                continue;
            }

            switch (opcion) {
                case 1:
                    int opcion2 = 0;
                    do {
                        System.out.println("\n|=== Elige un personaje ===|");
                        System.out.println("|1. Arquero                |");
                        System.out.println("|2. Mago                   |");
                        System.out.println("|3. Peleador               |");
                        System.out.println("|==========================|");
                        System.out.print(">> Elige una opcion: ");
                        try {
                            opcion2 = Integer.parseInt(sc.nextLine());
                            if (opcion2 >= 1 && opcion2 <= 3) {
                                juego.crearJugador(opcion2);
                            } else {
                                System.out.println("|!|: Opción no válida. Intenta de nuevo.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("|!|: Entrada inválida. Por favor, ingresa un número.");
                        }
                    } while (opcion2 < 1 || opcion2 > 3);
                    break;
                case 2:
                    System.out.println(juego.sortearItem());
                    break;
                case 3:
                    System.out.println(juego.mostrarInventario());
                    break;
                case 4:
                    System.out.println(juego.iniciarCombate());
                    break;
                case 5:
                    System.out.println(juego.guardarPartida());
                    break;
                case 6:
                    System.out.println(juego.cargarPartida());
                    break;
                case 7:
                    filtradoMenu();
                    break;
                case 8:
                    System.out.println(juego.probarExcepciones());
                    break;
                case 9:
                    System.out.println("Saliendo ...");
                    System.out.println("Gracias por jugar!");
                    break;
                default:
                    System.out.println("|!|: Opción no válida.");
                    break;
            }
        } while (opcion != 9);

    }

    public static void filtradoMenu() {
        System.out.println("\n|=== Filtrado y Búsqueda ===|");
        System.out.println("|1. Enemigos                  |");
        System.out.println("|2. Items                     |");
        System.out.println("|3. Salir                     |");
        System.out.println("|=============================|");
        System.out.print(">> Elige una opcion: ");

        int opcion = 0;
        try {
            opcion = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("|!|: Entrada inválida. Debe ser un número.");
            return;
        }

        switch (opcion) {
            case 1:
                System.out.println("\n|=== Filtrado y Búsqueda ===|");
                System.out.println("|1. Buscar por nombre         |");
                System.out.println("|2. Filtrar por Nivel         |");
                System.out.println("|3. Salir                     |");
                System.out.println("|=============================|");
                System.out.print(">> Elige una opcion: ");
                int opcion2 = 0;
                try {
                    opcion2 = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("|!|: Entrada inválida.");
                    break;
                }

                if (opcion2 == 1) {
                    System.out.print(">> Ingrese el nombre del enemigo: ");
                    String nombre = sc.nextLine();
                    juego.buscarPorNombre(nombre);
                } else if (opcion2 == 2) {
                    System.out.print(">> Ingrese el nivel del enemigo: ");
                    try {
                        int nivel = Integer.parseInt(sc.nextLine());
                        juego.filtrarPorNivel(nivel);
                    } catch (NumberFormatException e) {
                        System.out.println("|!|: Nivel inválido.");
                    }
                } else {
                    System.out.println("|!|: Opción no válida.");
                }
                break;
            case 2:
                System.out.println("\n|=== Filtrado y Búsqueda ===|");
                System.out.println("|1. Buscar por nombre         |");
                System.out.println("|2. Eliminar Item             |");
                System.out.println("|3. Salir                     |");
                System.out.println("|=============================|");
                System.out.print(">> Elige una opcion: ");
                int opcion3 = 0;
                try {
                    opcion3 = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("|!|: Entrada inválida.");
                    break;
                }

                if (opcion3 == 1) {
                    System.out.print(">> Ingrese el nombre del item: ");
                    String nombre = sc.nextLine();
                    System.out.println(juego.buscarItem(nombre));
                } else if (opcion3 == 2) {
                    System.out.print(">> Ingrese el nombre del item a eliminar: ");
                    String nombre = sc.nextLine();
                    System.out.println(juego.eliminarItem(nombre));
                } else {
                    System.out.println("|!|: Opción no válida.");
                }
                break;
            case 3:
                break;
            default:
                System.out.println("|!|: Opción no válida.");
                break;
        }
    }
}
