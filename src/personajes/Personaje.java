package personajes;

import Excepciones.ManaInsuficienteException;
import enemigos.Enemigo;
import items.Arma;
import items.Armadura;
import interfaces.Vida;
import items.Consumible;

public abstract class Personaje implements Vida {
    protected String nombre;
    protected int nivel;
    protected int vidaMaxima;
    protected int vidaActual;
    protected int defensa;
    protected int danio;
    protected Arma arma;
    protected Armadura armadura;
    protected Consumible consumible;
    protected boolean bloqueando;

    public Personaje(String nombre, int nivel, int vidaMaxima, int vidaActual, int defensa, int danio) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El personaje debe tener un nombre válido.");
        }
        this.nombre = nombre;
        this.danio = danio;
        this.nivel = nivel;
        this.vidaActual = vidaActual;
        this.vidaMaxima = vidaMaxima;
        this.defensa = defensa;
        this.arma = null;
        this.armadura = null;
        this.consumible = null;
        this.bloqueando = false;
    }

    public abstract void atacar(Enemigo e) throws ManaInsuficienteException;

    public abstract void bloquear();

    public int getNivel() {
        return nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public Arma getArma() {
        return arma;
    }

    public int getDanio() {
        return danio;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public Consumible getConsumible() {
        return consumible;
    }

    public void activarBloqueo() {
        bloqueando = true;
    }

    public int calcularDefensaTotal() {
        int defensaTotal = defensa;

        if (armadura != null && !armadura.estaRota()) {
            defensaTotal += armadura.getDefensa();
            armadura.reducirDurabilidad(1);
            System.out.println(getNombre() + " bloquea parte del daño con su armadura " + armadura.getNombre()
                    + ". Defensa total: " + defensaTotal);
        }

        if (bloqueando) {
            defensaTotal += defensa;
            bloqueando = false;
            System.out.println(getNombre() + " refuerza su defensa al bloquear.");
        }

        return defensaTotal;
    }

    public void aplicarDanioRecibido(int danioRecibido) {
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
        this.arma = a;
    }

    public void setArmadura(Armadura a) {
        if (a == null) {
            System.out.println("El personaje " + getNombre() + " no tiene ninguna armadura para usar.");
            return;
        }
        this.armadura = a;
    }

    public void setConsumible(Consumible c) {
        if (c == null) {
            System.out.println("El personaje " + getNombre() + " no tiene ningun consumible para usar.");
            return;
        }
        this.consumible = c;
    }

    public String toString() {
        return "Personaje{" +
                "nombre='" + nombre + '\'' +
                ", nivel=" + nivel +
                ", vidaMaxima=" + vidaMaxima +
                ", vidaActual=" + vidaActual +
                ", defensa=" + defensa +
                ", daño=" + danio +
                '}';
    }
}