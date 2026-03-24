package rpg;

import enemigos.*;
import items.*;

public class RPG{
    
    public Item generarItem(){
        int random = (int)(1 + Math.random() * 4); // Genera un número aleatorio entre 1 y 4

        switch(random){
            case 1: 
                return new Consumible("Poción de Salud", 1, 5);
            case 2:
                return new ArmaMelee("Daga", 1, 10, 10, 5);
            case 3:
                return new ArmaDistancia("Arco de madera", 15, 10, 5, 5, 10);
            case 4:
                return new Armadura("Armadura de piel", 1, 0, 20);
            default:
                return null;
        }
    }

    public Enemigo generarEnemigo(int nivel){
        int random = (int)(1 + Math.random() * 5); // Genera un número aleatorio entre 1 y 3

        switch(random){
            case 1: 
                return new Esqueleto(nivel);
            case 2:
                return new Slime(nivel);
            case 3:
                return new Dragon(nivel);
            case 4:
                return new Zombie(nivel);
            case 5: 
                return new ElMuro(nivel);
            default:
                return null;
        }
    }
}