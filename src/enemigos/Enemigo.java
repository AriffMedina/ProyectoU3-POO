package enemigos;
import interfaces.Vida;
import personajes.Personaje;

public abstract class Enemigo implements Vida {
    private String nombre;
    private int vida;
    private int nivel;
    private int experienciaOtorgada;

    /* validación uso de illegal*/
    public Enemigo(String nombre, int nivel, int vida, int experienciaOtorgada) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del enemigo no puede estar vacío.");
        }
        if (nivel <= 0) {
            throw new IllegalArgumentException("El nivel debe ser mayor a 0.");
        }
        if (vida < 0) {
            throw new IllegalArgumentException("La vida no puede ser negativa.");
        }
        if (experienciaOtorgada < 0) {
            throw new IllegalArgumentException("La experiencia no puede ser negativa.");
        }

        this.nombre = nombre;
        this.nivel = nivel;
        this.vida = vida;
        this.experienciaOtorgada = experienciaOtorgada;
    }


    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = vida; }

    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }

    public int getExperienciaOtorgada() { return experienciaOtorgada; }

    public int getDanio() {
        return nivel * 10; 
    }


    @Override
    public void recibirDanio(int cantidad) {
        this.vida -= cantidad;
        if (this.vida < 0) this.vida = 0;
        System.out.println(nombre + " recibió " + cantidad + " de daño. Vida restante: " + vida);
    }

    @Override
    public boolean estaVivo() {
        return this.vida > 0;
    }

    /* polimorfismo*/
    public abstract void atacar(Personaje personaje);

    public int getExperiencia() {
        return experienciaOtorgada;
    }


    @Override
    public String toString() {
        return String.format("Enemigo [Nombre: %s, Nivel: %d, Vida: %d, XP: %d]",
                nombre, nivel, vida, experienciaOtorgada);
    }
}