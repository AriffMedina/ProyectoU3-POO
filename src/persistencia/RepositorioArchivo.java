package persistencia;

import interfaces.PartidaRepositorio;
import java.util.List;
import personajes.Personaje;

public class RepositorioArchivo implements PartidaRepositorio {

    private PersonajeArchivo personaje;
    private ItemArchivo item;

    public RepositorioArchivo() {
        personaje = new PersonajeArchivo("personajes.csv");
        item = new ItemArchivo("inventario.csv");
    }

    @Override
    public void guardarPersonajes(List<Personaje> personajes) {
        personaje.guardarPersonajes(personajes);
    }

    @Override
    public List<Personaje> cargarPersonajes() {
        return personaje.cargarPersonajes();
    }

    @Override
    public void guardarInventario(List<Personaje> lista) {
        item.guardarInventario(lista);
    }

    @Override
    public void cargarInventario(List<Personaje> lista) {
        item.cargarInventario(lista);
    }
}
