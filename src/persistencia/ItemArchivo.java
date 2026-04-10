package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import items.Arma;
import items.ArmaDistancia;
import items.ArmaMelee;
import items.Armadura;
import items.Consumible;
import personajes.Personaje;

public class ItemArchivo {

    private String rutaArchivo;

    public ItemArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void guardarInventario(List<Personaje> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Personaje p : lista) {
                for (Arma a : p.getArmas()) {
                    bw.write(p.getNombre() + "," + a.toCSV());
                    bw.newLine();
                }
                for (Armadura a : p.getArmaduras()) {
                    bw.write(p.getNombre() + "," + a.toCSV());
                    bw.newLine();
                }
                for (Consumible c : p.getConsumibles()) {
                    bw.write(p.getNombre() + "," + c.toCSV());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar inventario: " + e.getMessage());
        }
    }

    public void cargarInventario(List<Personaje> personajes) {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists())
            return;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    String[] partes = linea.split(",");
                    String nombre = partes[0];
                    String caracteristicas = partes[1];

                    Personaje p = buscarPorNombre(personajes, nombre);
                    if (p == null) {
                        System.out.println("Dueño no encontrado, línea ignorada: " + linea);
                        continue;
                    }

                    switch (caracteristicas.toLowerCase()) {
                        case "arma_melee":
                        case "armamelee":
                            p.setArma(ArmaMelee.fromCSV(linea));
                            break;
                        case "arma_distancia":
                        case "distancia":
                        case "armadistancia":
                            p.setArma(ArmaDistancia.fromCSV(linea));
                            break;
                        case "armadura":
                            p.setArmadura(Armadura.fromCSV(linea));
                            break;
                        case "consumible":
                            p.setConsumible(Consumible.fromCSV(linea));
                            break;
                        default:
                            System.out.println("Tipo de ítem desconocido: " + caracteristicas);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Línea con formato incorrecto (faltan campos): " + linea);
                } catch (NumberFormatException e) {
                    System.out.println("Línea con valor numérico inválido: " + linea);
                } catch (IllegalArgumentException e) {
                    System.out.println("Línea con datos inválidos (" + e.getMessage() + "): " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el inventario: " + e.getMessage());
        }
    }

    private Personaje buscarPorNombre(List<Personaje> lista, String nombre) {
        for (Personaje p : lista) {
            if (p.getNombre().equalsIgnoreCase(nombre))
                return p;
        }
        return null;
    }
}
