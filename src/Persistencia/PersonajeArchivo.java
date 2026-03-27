package Persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import personajes.Personaje;
import personajes.Peleador;
import personajes.Mago;
import personajes.Arquero;

public class PersonajeArchivo {

    private String rutaArchivo;

    public PersonajeArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void guardarPersonajes(List<Personaje> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Personaje p : lista) {
                bw.write(p.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar personajes: " + e.getMessage());
        }
    }

    public Personaje fromCSV(String linea) {
        String tipo = linea.split(",")[0];
        switch (tipo) {
            case "Peleador":
                return Peleador.fromCSV(linea);
            case "Mago":
                return Mago.fromCSV(linea);
            case "Arquero":
                return Arquero.fromCSV(linea);
            default:
                throw new IllegalArgumentException("Tipo de personaje desconocido: " + tipo);
        }
    }

    public List<Personaje> cargarPersonajes() {
        List<Personaje> lista = new ArrayList<>();
        File archivo = new File(rutaArchivo);
        if (!archivo.exists())
            return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    Personaje p = fromCSV(linea);
                    lista.add(p);
                } catch (IllegalArgumentException e) {
                    System.out.println("Línea corrupta ignorada: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar personajes: " + e.getMessage());
        }
        return lista;
    }
}