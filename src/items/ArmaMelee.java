package items;

import Excepciones.ArmaRotaException;

public class ArmaMelee extends Arma {
    private int estamina;

    public ArmaMelee(String nombre, int cantidad, int durabilidad, int danio, int estamina) {
        super(nombre, cantidad, danio, durabilidad);
        this.estamina = (estamina < 0) ? 0 : estamina;
    }

    @Override
    public void usar() throws ArmaRotaException {
        if (getCantidad() <= 0)
            throw new IllegalStateException("No tienes " + nombre + " para usar.");
        if (estaRota()) {
            throw new ArmaRotaException(
                    "El arma " + nombre
                            + " se encuentra los suficiente desgastada para considerarse rota y su actual durabilidad es "
                            + durabilidad);
        }
        reducirDurabilidad(1);
        System.out.println("Usaste el arma " + nombre + ". Daño: " + danio + ". Durabilidad restante: " + durabilidad);
    }

    public int getEstamina() {
        return estamina;
    }

    public void golpeCritico() {
        if (estamina < 2) {
            System.out
                    .println("No tienes suficiente stamina para realizar un golpe crítico con el arma " + nombre + ".");
            return;
        }
        estamina -= 2;
        int danioCritico = danio * 2;
        System.out.println("Realizaste un golpe crítico con el arma " + nombre + ". Daño: " + danioCritico
                + ". Durabilidad restante: " + durabilidad);
    }

    @Override
    public void reparar(int cantidad) {
        if (cantidad < 0)
            throw new IllegalArgumentException("La cantidad a reparar no puede ser negativa");
        durabilidad += cantidad;
        System.out.println("Reparaste el arma " + nombre + ". Durabilidad actual: " + durabilidad);
    }

    @Override
    public String toCSV(String propietarioString) {
        return propietarioString + "ArmaMelee," + nombre + "," + cantidad + "," + danio + "," + durabilidad + ","
                + estamina + ",";
    }

    public static Arma fromCSV(String linea) {
        String[] partes = linea.split(",");
        return new ArmaMelee(partes[2], Integer.parseInt(partes[3]), Integer.parseInt(partes[5]),
                Integer.parseInt(partes[4]), Integer.parseInt(partes[6]));
    }

    @Override
    public String toString() {
        return "|ArmaMelee{" +
                "\n|— nombre='" + nombre + '\'' +
                "\n|— cantidad=" + cantidad +
                "\n|— danio=" + danio +
                "\n|— durabilidad=" + durabilidad +
                "\n|— stamina=" + estamina +
                '\n' + '}';
    }

}
