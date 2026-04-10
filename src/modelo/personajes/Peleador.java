package personajes;

import enemigos.Enemigo;
import excepciones.ManaInsuficienteException;

public class Peleador extends Personaje {
    private int fuerza;
    private int resistencia;

    public Peleador(String nombre, int nivel, int vidaMaxima, int vidaActual, int defensa, int danio, int fuerza,
            int resistencia) {
        super(nombre, nivel, vidaMaxima, vidaActual, defensa, danio);
        if (fuerza < 0)
            throw new IllegalArgumentException("La fuerza no puede ser negativa.");
        if (resistencia < 0)
            throw new IllegalArgumentException("La resistencia no puede ser negativa.");
        this.fuerza = fuerza;
        this.resistencia = resistencia;
    }

    @Override
    public String toCSV() {
        return "Peleador," + super.toCSV() + "," + fuerza + "," + resistencia;
    }

    public static Peleador fromCSV(String linea) {
        String[] partes = linea.split(",");
        if (partes.length < 9)
            throw new IllegalArgumentException("Línea inválida. Se esperan 9 campos: " + linea);
        try {
            return new Peleador(
                    partes[1],
                    Integer.parseInt(partes[2]),
                    Integer.parseInt(partes[3]),
                    Integer.parseInt(partes[4]),
                    Integer.parseInt(partes[5]),
                    Integer.parseInt(partes[6]),
                    Integer.parseInt(partes[7]),
                    Integer.parseInt(partes[8]));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error de número en Peleador: " + linea, e);
        }
    }

    @Override
    public void atacar(Enemigo e) throws ManaInsuficienteException {
        if (!estaVivo())
            return;
        if (getArma() == null)
            return;
        int danioTotal = getDanio() + this.fuerza * 2 + getArma().getDanio();
        e.recibirDanio(danioTotal);
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
        return "Peleador{" + "nombre='" + getNombre() + "', fuerza=" + fuerza + ", resistencia=" + resistencia + '}';
    }
}
