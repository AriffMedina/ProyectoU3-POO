package interfaces;

import java.util.List;
import personajes.Personaje;

public interface PartidaRepositorio {
    void guardarPersonajes(List<Personaje> personajes);

    List<Personaje> cargarPersonajes();

    void guardarInventario(List<Personaje> lista);

    void cargarInventario(List<Personaje> lista);
}
