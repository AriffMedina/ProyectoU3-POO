package Persistencia;

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
import rpg.JuegoServicio;

public class ItemArchivo {

    private String rutaArchivo;

    public ItemArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void guardarInventario(List<Personaje> lista) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Personaje p : lista) {
                for (Arma a : p.getArmas()) {
                    bw.write(p.getNombre() + "," + a.toCSV(p.getNombre()));
                    bw.newLine();
                }
                for (Armadura a : p.getArmaduras()) {
                    bw.write(p.getNombre() + "," + a.toCSV(p.getNombre()));
                    bw.newLine();
                }
                for (Consumible c : p.getConsumibles()) {
                    bw.write(p.getNombre() + "," + c.toCSV(p.getNombre()));
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar inventario: " + e.getMessage());
        }
    }

    public void cargarInventario(List<Personaje> personajes) throws IOException {
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

                    Personaje p = JuegoServicio.buscarPorNombre(personajes, nombre);
                    if (p == null) {
                        System.out.println("Dueño no encontrado, línea ignorada: " + linea);
                        continue;
                    }

                    switch (caracteristicas) {
                        case "arma_melee":
                            p.setArma(ArmaMelee.fromCSV(linea));
                            break;
                        case "arma_distancia":
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
                } catch (Exception e) {
                    System.out.println("Linea ignorada: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el inventario: " + e.getMessage());
        }
    }
}
