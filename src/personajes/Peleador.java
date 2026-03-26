package personajes;

import Excepciones.ManaInsuficienteException;
import enemigos.Enemigo;

public class Peleador extends Personaje {
    private int fuerza;
    private int resistencia;

    public Peleador(String nombre, int nivel, int vidaMaxima, int vidaActual, int defensa, int danio, int fuerza,
            int resistencia) {
        super(nombre, nivel, vidaMaxima, vidaActual, defensa, danio);
        this.fuerza = fuerza;
        this.resistencia = resistencia;
    }

    @Override
    public String toCSV() {
        return "Peleador," + getNombre() + "," + getNivel() + "," + getVidaMaxima() + "," + 
               getVidaActual() + "," + getDefensa() + "," + getDanio() + "," + 
               fuerza + "," + resistencia;
    }

    public static Peleador fromCSV(String linea) {
        String[] partes = linea.split(",");
        return new Peleador(
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
        int danioTotal = getDanio() + this.fuerza * 2 + getArma().getDanio();
        e.recibirDanio(danioTotal);
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
        return "Peleador{" + "nombre='" + getNombre() + "', fuerza=" + fuerza + ", resistencia=" + resistencia + '}';
    }
}
