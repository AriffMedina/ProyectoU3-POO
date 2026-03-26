package items;

import personajes.Personaje;

public class Consumible extends Item {
    private int curativo;

    public Consumible(String nombre, int cantidad, int curativo) {
        super(nombre, cantidad);
        if (curativo < 0)
            throw new IllegalArgumentException("El valor curativo no puede ser negativo");
        this.curativo = curativo;
    }

    @Override
    public void usar() {
        if (estaVacio())
            throw new IllegalStateException("No tienes " + nombre + " para usar.");
        cantidad--;
        System.out.println("Usaste " + nombre + ". Cura: " + curativo + ". Cantidad restante: " + cantidad);
    }

    public int curar(int vidaActual, int vidaMaxima) {
        usar();
        return calcularCuracion(vidaActual, vidaMaxima);
    }

    public int calcularCuracion(int vidaActual, int vidaMaxima) {
        if (vidaMaxima < 0)
            throw new IllegalArgumentException("La vida maxima no puede ser negativa");
        if (vidaActual < 0)
            throw new IllegalArgumentException("La vida actual no puede ser negativa");
        int vidaActualAjustada = Math.min(vidaActual, vidaMaxima);
        return Math.min(vidaActualAjustada + curativo, vidaMaxima);
    }

    public int getCurativo() {
        return curativo;
    }

    public boolean estaVacio() {
        return cantidad <= 0;
    }

    @Override
    public void equiparEn(Personaje p) {
        p.setConsumible(this);
        System.out.println("> Consumible guardado: " + nombre);
    }

    @Override
    public String toCSV() {
        return "consumible," + nombre + "," + cantidad + "," + curativo + ",";
    }

    public static Consumible fromCSV(String linea) {
        String[] partes = linea.split(",");
        return new Consumible(partes[2], Integer.parseInt(partes[3]), Integer.parseInt(partes[4]));
    }

    @Override
    public String toString() {
        return "|Consumible{" +
                "\n|- nombre='" + nombre +
                "\n|- cantidad=" + cantidad +
                "\n|- curativo=" + curativo +
                '\n' + '}';
    }
}

