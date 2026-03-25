package rpg;

import items.*;

public class GestorItems {
    public static Item generarItem() {
        int random = (int) (1 + Math.random() * 4);
        switch (random) {
            case 1:
                return new Consumible("Poción de Salud", 1, 5);
            case 2:
                return new ArmaMelee("Daga", 1, 10, 10, 5);
            case 3:
                return new ArmaDistancia("Arco de madera", 15, 10, 5, 5, 0.5);
            case 4:
                return new Armadura("Armadura de piel", 1, 0, 20);
            default:
                return null;
        }
    }
}
