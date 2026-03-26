package personajes;

import Excepciones.ManaInsuficienteException;
import enemigos.Enemigo;

public class Mago extends Personaje {
    private int mana;
    private int manaMaximo;

    public Mago(String nombre, int nivel, int vidaMaxima, int vidaActual, int defensa, int danio, int mana,
            int manaMaximo) {
        super(nombre, nivel, vidaMaxima, vidaActual, defensa, danio);
        if (manaMaximo <= 0) throw new IllegalArgumentException("El mana maximo debe ser mayor a 0");
        this.mana = mana;
        this.manaMaximo = manaMaximo;
    }

    @Override
    public String toCSV() {
        return "Mago," + getNombre() + "," + getNivel() + "," + getVidaMaxima() + "," + 
               getVidaActual() + "," + getDefensa() + "," + getDanio() + "," + 
               mana + "," + manaMaximo;
    }

    public static Mago fromCSV(String linea) {
        String[] partes = linea.split(",");
        return new Mago(
            partes[1], 
            Integer.parseInt(partes[2]), 
            Integer.parseInt(partes[3]), 
            Integer.parseInt(partes[4]), 
            Integer.parseInt(partes[5]), 
            Integer.parseInt(partes[6]), 
            Integer.parseInt(partes[7]), 
            Integer.parseInt(partes[8])
        );
    }

    @Override
    public void atacar(Enemigo e) throws ManaInsuficienteException {
        if (!estaVivo()) return;
        if (getArma() == null) return;
        if (mana < 10) throw new ManaInsuficienteException("Mana insuficiente: " + mana);

        int danioTotal = getDanio() + getArma().getDanio() + mana;
        e.recibirDanio(danioTotal);
        mana -= 10;
    }

    public void recargarMana(int recarga) {
        mana = Math.min(mana + recarga, manaMaximo);
        System.out.println("Mana actual: " + mana + "/" + manaMaximo);
    }

    @Override
    public void bloquear() {
        if (!estaVivo()) return;
        activarBloqueo();
    }

    @Override
    public boolean estaVivo() {
        return getVidaActual() > 0;
    }

    @Override
    public String toString() {
        return "Mago{" + "nombre='" + getNombre() + "', mana=" + mana + "/" + manaMaximo + '}';
    }
}
