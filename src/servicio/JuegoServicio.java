package servicio;

import personajes.*;
import enemigos.*;
import items.*;
import excepciones.ArmaRotaException;
import excepciones.ManaInsuficienteException;
import interfaces.PartidaRepositorio;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JuegoServicio {
    private Personaje jugador;
    private GestorCombate accion;
    private GestorEnemigos gestorEnemigos;
    private PartidaRepositorio repositorio;

    public JuegoServicio(PartidaRepositorio repositorio) {
        accion = new GestorCombate();
        gestorEnemigos = new GestorEnemigos();
        this.repositorio = repositorio;
        cargarPartida();
    }

    public String crearJugador(int opcion) {
        switch (opcion) {
            case 1:
                jugador = new Arquero("Legolas", 2, 100, 100, 12, 8, 6, 20);
                break;
            case 2:
                jugador = new Mago("Gandalf", 2, 80, 80, 6, 12, 50, 50);
                break;
            case 3:
                jugador = new Peleador("Conan", 2, 120, 120, 15, 6, 8, 10);
                break;
            default:
                return "Opción no válida.";
        }
        guardarPartida();
        return "Personaje creado exitosamente.\n" + jugador.toString();
    }

    public String mostrarInventario() {
        if (jugador == null) {
            return "No se pudo mostrar el inventario porque no hay un personaje creado.";
        }
        StringBuilder sb = new StringBuilder(jugador.getNombre() + " inventario: \n");
        int total = 0;

        for (Arma a : jugador.getArmas()) {
            sb.append(" ").append(a).append("\n");
            total++;
        }
        for (Armadura a : jugador.getArmaduras()) {
            sb.append(" ").append(a).append("\n");
            total++;
        }
        for (Consumible c : jugador.getConsumibles()) {
            sb.append(" ").append(c).append("\n");
            total++;
        }
        if (total == 0) {
            return "Inventario vacio.";
        }
        return sb.toString();
    }

    public String sortearItem() {
        if (jugador == null) {
            return "No se pudo sortear el item porque no hay un personaje creado.";
        }

        Item i = GestorItems.generarItem();
        i.equiparEn(jugador);
        guardarPartida();
        return "Item añadido al inventario: " + i.getNombre();
    }

    public String iniciarCombate() {
        if (jugador == null) {
            return "No se pudo iniciar el combate porque no hay un personaje creado.";
        }

        Enemigo enemigo = GestorEnemigos.generarEnemigo(jugador.getNivel() + 3);

        gestorEnemigos.agregarEnemigo(enemigo);

        accion.iniciar(jugador, enemigo);
        if (!jugador.estaVivo()) {
            return "Combate finalizado.";
        }
        guardarPartida();
        return "Combate iniciado exitosamente.";
    }

    public String usarConsumible() {
        if (jugador == null) {
            return "No se pudo usar el consumible porque no hay un personaje creado.";
        }
        accion.usarConsumible(jugador);
        guardarPartida();
        return "Consumible usado exitosamente.: " + jugador.getConsumibles();
    }

    public String guardarPartida() {
        if (jugador == null) {
            return "No se pudo guardar la partida porque no hay un personaje creado.";
        }
        List<Personaje> lista = new ArrayList<>();
        lista.add(jugador);
        repositorio.guardarPersonajes(lista);
        repositorio.guardarInventario(lista);
        return "Partida guardada exitosamente para " + jugador.getNombre() + ".";
    }

    public String cargarPartida() {
        List<Personaje> lista = repositorio.cargarPersonajes();
        if (lista.isEmpty()) {
            return "No hay partida para cargar.";
        }
        repositorio.cargarInventario(lista);
        jugador = lista.get(0);
        return "Partida cargada exitosamente para " + jugador.getNombre() + ".";
    }

    public Item buscarItem(String nombre) {
        if (jugador == null) {
            return null;
        }

        for (Arma arm : jugador.getArmas()) {
            if (arm.getNombre().equalsIgnoreCase(nombre)) {
                return arm;
            }
        }
        for (Armadura a : jugador.getArmaduras()) {
            if (a.getNombre().equalsIgnoreCase(nombre)) {
                return a;
            }
        }
        for (Consumible c : jugador.getConsumibles()) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

    public String eliminarItem(String nombre) {
        if (jugador == null) {
            return "No se pudo eliminar el item porque no hay un personaje creado.";
        }

        Iterator<Arma> itArm = jugador.getArmas().iterator();
        while (itArm.hasNext()) {
            Arma a = itArm.next();
            if (a.getNombre().equalsIgnoreCase(nombre)) {
                if (a.getCantidad() > 1) {
                    a.disminuirCantidad(1);
                } else {
                    itArm.remove();
                }
                guardarPartida();
                return "Item eliminado: " + nombre;
            }
        }

        Iterator<Armadura> itArmaduras = jugador.getArmaduras().iterator();
        while (itArmaduras.hasNext()) {
            Armadura a = itArmaduras.next();
            if (a.getNombre().equalsIgnoreCase(nombre)) {
                if (a.getCantidad() > 1) {
                    a.disminuirCantidad(1);
                } else {
                    itArmaduras.remove();
                }
                guardarPartida();
                return "Item eliminado: " + nombre;
            }
        }

        Iterator<Consumible> itCons = jugador.getConsumibles().iterator();
        while (itCons.hasNext()) {
            Consumible c = itCons.next();
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                if (c.getCantidad() > 1) {
                    c.disminuirCantidad(1);
                } else {
                    itCons.remove();
                }
                guardarPartida();
                return "Item eliminado: " + nombre;
            }
        }
        return "No se encontro ningun item con el nombre: " + nombre;
    }

    public List<Arma> filtrarArmas() {
        if (jugador == null) {
            return new ArrayList<>();
        }

        List<Arma> armasFiltradas = new ArrayList<>();

        for (Arma a : jugador.getArmas()) {
            if (!a.estaRota()) {
                armasFiltradas.add(a);
            }
        }
        return armasFiltradas;
    }

    public Enemigo buscarPorNombre(String nombre) {
        return gestorEnemigos.buscarPorNombre(nombre);
    }

    public List<Enemigo> filtrarPorNivel(int nivel) {
        return gestorEnemigos.filtrarPorNivel(nivel);
    }

    public String probarExcepciones() {
        StringBuilder sb = new StringBuilder();

        Mago m1 = new Mago("Michi", 1, 80, 80, 2, 12, 5, 20);
        m1.setArma(new ArmaMelee("Baston oscuro", 1, 10, 10, 5));
        Enemigo esqueleto = new Esqueleto(1);
        try {
            m1.atacar(esqueleto);
        } catch (ManaInsuficienteException e) {
            sb.append(">> Error: ").append(e.getMessage()).append("\n");
        }

        ArmaMelee a1 = new ArmaMelee("Espada oxidada", 1, 0, 5, 5);
        try {
            a1.usar();
        } catch (ArmaRotaException e) {
            sb.append(">> Error: ").append(e.getMessage()).append("\n");
        }

        return sb.toString();
    }
}
