package personajes;

import enemigos.Enemigo;
import excepciones.ManaInsuficienteException;

public class Mago extends Personaje {
    private int mana;
    private int manaMaximo;

    public Mago(String nombre, int nivel, int vidaMaxima, int vidaActual, int defensa, int danio, int mana,
            int manaMaximo) {
        super(nombre, nivel, vidaMaxima, vidaActual, defensa, danio);
        if (manaMaximo <= 0)
            throw new IllegalArgumentException("El mana maximo debe ser mayor a 0");
        this.mana = mana;
        this.manaMaximo = manaMaximo;
    }

    public int getMana() {
        return mana;
    }

    public int getManaMaximo() {
        return manaMaximo;
    }

    @Override
    public String toCSV() {
        return "Mago," + super.toCSV() + "," + mana + "," + manaMaximo;
    }

    public static Mago fromCSV(String linea) {
        String[] partes = linea.split(",");
        if (partes.length < 9)
            throw new IllegalArgumentException("Línea inválida para Mago: " + linea);
        try {
            return new Mago(
                    partes[1],
                    Integer.parseInt(partes[2]),
                    Integer.parseInt(partes[3]),
                    Integer.parseInt(partes[4]),
                    Integer.parseInt(partes[5]),
                    Integer.parseInt(partes[6]),
                    Integer.parseInt(partes[7]),
                    Integer.parseInt(partes[8]));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error de número en Mago: " + linea, e);
        }
    }

    @Override
    public void atacar(Enemigo e) throws ManaInsuficienteException {
        if (!estaVivo())
            return;
        if (mana < 10)
            throw new ManaInsuficienteException("Mana insuficiente: " + mana);

        int danioTotal = getDanio() + mana;
        if (getArma() != null)
            danioTotal += getArma().getDanio();
        e.recibirDanio(danioTotal);
        mana -= 10;
    }

    public void recargarMana(int recarga) {
        mana = Math.min(mana + recarga, manaMaximo);
        System.out.println("Mana actual: " + mana + "/" + manaMaximo);
    }

    @Override
    public void bloquear() {
        if (!estaVivo())
            return;
        activarBloqueo();
    }

    @Override
    public boolean estaVivo() {
        return getVidaActual() > 0;
    }

    @Override
    public String toString() {
        return "Mago{" + "nombre='" + getNombre() + "', mana=" + getMana() + "/" + getManaMaximo() + '}';
    }
}
