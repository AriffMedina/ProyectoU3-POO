package interfaces;

public interface Durable {
    void reducirDurabilidad(int cantidad);
    void reparar(int cantidad);
    boolean estaRota();
}
