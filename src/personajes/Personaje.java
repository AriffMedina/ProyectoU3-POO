package personajes;

import java.util.ArrayList;
import java.util.List;

import Excepciones.ManaInsuficienteException;
import enemigos.Enemigo;
import items.Arma;
import items.Armadura;
import interfaces.Vida;
import items.Consumible;

public abstract class Personaje implements Vida {
    private String nombre;
    private int nivel;
    private int vidaMaxima;
    private int vidaActual;
    private int defensa;
    private int danio;
    private List<Arma> armas;
    private List<Armadura> armaduras;
    private List<Consumible> consumibles;
    private boolean bloqueando;

    public Personaje(String nombre, int nivel, int vidaMaxima, int vidaActual, int defensa, int danio) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El personaje debe tener un nombre válido.");
        }
        if (nivel <= 0) {
            throw new IllegalArgumentException("El nivel debe ser mayor a 0.");
        }
        if (vidaMaxima <= 0) {
            throw new IllegalArgumentException("La vida máxima debe ser mayor a 0.");
        }
        if (vidaActual < 0 || vidaActual > vidaMaxima) {
            throw new IllegalArgumentException("La vida actual debe estar entre 0 y la vida máxima.");
        }
        if (defensa < 0) {
            throw new IllegalArgumentException("La defensa no puede ser negativa.");
        }
        if (danio < 0) {
            throw new IllegalArgumentException("El daño no puede ser negativo.");
        }
        this.nombre = nombre;
        this.danio = danio;
        this.nivel = nivel;
        this.vidaActual = vidaActual;
        this.vidaMaxima = vidaMaxima;
        this.defensa = defensa;
        this.armas = new ArrayList<>();
        this.armaduras = new ArrayList<>();
        this.consumibles = new ArrayList<>();
        this.bloqueando = false;
    }

    // * Métodos abstractos
    public abstract void atacar(Enemigo e) throws ManaInsuficienteException;
    public abstract void bloquear();
    
    // Método para persistencia
    public abstract String toCSV();

    // * Getters y Setters
    public int getNivel() { return nivel; }
    public String getNombre() { return nombre; }
    public Arma getArma() {
        if (armas.isEmpty()) return null;
        return armas.get(armas.size() - 1);
    }
    public int getDanio() { return danio; }
    public int getVidaActual() { return vidaActual; }
    public int getDefensa() { return defensa; }
    public int getVidaMaxima() { return vidaMaxima; }
    public List<Arma> getArmas() { return armas; }
    public List<Armadura> getArmaduras() { return armaduras; }
    public List<Consumible> getConsumibles() { return consumibles; }

    public void activarBloqueo() { bloqueando = true; }

    public int calcularDefensaTotal() {
        int defensaTotal = defensa;
        if (!armaduras.isEmpty()) {
            Armadura ultima = armaduras.get(armaduras.size() - 1);
            if (!ultima.estaRota()) {
                defensaTotal += ultima.getDefensa();
                ultima.reducirDurabilidad(1);
                System.out.println(getNombre() + " bloquea parte del daño con su armadura " + ultima.getNombre() + ". Defensa total: " + defensaTotal);
            }
        }
        if (bloqueando) {
            defensaTotal += defensa;
            bloqueando = false;
            System.out.println(getNombre() + " refuerza su defensa al bloquear.");
        }
        return defensaTotal;
    }

    @Override
    public void recibirDanio(int danioRecibido) {
        int danioFinal = Math.max(0, danioRecibido - calcularDefensaTotal());
        vidaActual = Math.max(0, vidaActual - danioFinal);
        System.out.println(getNombre() + " recibió " + danioFinal + " de daño.");
        System.out.println("Vida actual: " + vidaActual);
    }

    public void setVidaActual(int nuevaVida) {
        if (nuevaVida < 0) {
            vidaActual = 0;
            return;
        }
        vidaActual = Math.min(nuevaVida, vidaMaxima);
    }

    public void setArma(Arma a) {
        if (a == null) {
            System.out.println("No se puede equipar un arma nula.");
            return;
        }
        armas.add(a);
    }

    public void setArmadura(Armadura a) {
        if (a == null) {
            System.out.println("El personaje " + getNombre() + " no tiene ninguna armadura para usar.");
            return;
        }
        armaduras.add(a);
    }

    public void setConsumible(Consumible c) {
        if (c == null) {
            System.out.println("El personaje " + getNombre() + " no tiene ningun consumible para usar.");
            return;
        }
        consumibles.add(c);
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "nombre='" + getNombre() + '\'' +
                ", nivel=" + getNivel() +
                ", vidaMaxima=" + getVidaMaxima() +
                ", vidaActual=" + getVidaActual() +
                ", defensa=" + getDefensa() +
                ", daño=" + getDanio() +
                '}';
    }
}
