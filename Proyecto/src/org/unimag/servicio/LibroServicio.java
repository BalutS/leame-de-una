package org.unimag.servicio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.unimag.api.ApiOperacionBD;
import org.unimag.modelo.Libro;
import org.unimag.modelo.Autor;
import org.unimag.modelo.Editorial;

public class LibroServicio implements ApiOperacionBD<Libro, String> {

    private final String resourcePath = "/org/unimag/recurso/libros.txt";
    private final String filePath = System.getProperty("user.home") + "/libros.txt";
    private final AutorServicio autorServicio = new AutorServicio();
    private final EditorialServicio editorialServicio = new EditorialServicio();

    @Override
    public int getSerial() {
        int ultimoId = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] partes = linea.split(";");
                    ultimoId = Integer.parseInt(partes[0]);
                }
            }
        } catch (IOException | NumberFormatException e) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(resourcePath)))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (!linea.trim().isEmpty()) {
                        String[] partes = linea.split(";");
                        ultimoId = Integer.parseInt(partes[0]);
                    }
                }
            } catch (IOException | NumberFormatException e2) {
                // Manejar la excepcion o registrarla
            }
        }
        return ultimoId + 1;
    }

    @Override
    public Libro insertInto(Libro libro, String ruta) {
        libro.setIdLibro(getSerial());
        String registro = libro.getIdLibro() + ";" + libro.getNombreLibro() + ";" + libro.getPrecioLibro() + ";" + libro.getAnioLibro() + ";" + libro.getIdEditorial().getIdEditorial() + ";" + libro.getIdAutor().getIdAutor();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(registro);
            bw.newLine();
        } catch (IOException e) {
            return null;
        }
        return libro;
    }

    @Override
    public List<Libro> selectFrom() {
        List<Libro> libros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] partes = linea.split(";");
                    Libro libro = new Libro();
                    libro.setIdLibro(Integer.parseInt(partes[0]));
                    libro.setNombreLibro(partes[1]);
                    libro.setPrecioLibro(Double.parseDouble(partes[2]));
                    libro.setAnioLibro(Short.parseShort(partes[3]));

                    Editorial editorial = editorialServicio.getOne(partes[4]);
                    libro.setIdEditorial(editorial);

                    Autor autor = autorServicio.getOne(partes[5]);
                    libro.setIdAutor(autor);

                    libros.add(libro);
                }
            }
        } catch (IOException | NumberFormatException e) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(resourcePath)))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (!linea.trim().isEmpty()) {
                        String[] partes = linea.split(";");
                        Libro libro = new Libro();
                        libro.setIdLibro(Integer.parseInt(partes[0]));
                        libro.setNombreLibro(partes[1]);
                        libro.setPrecioLibro(Double.parseDouble(partes[2]));
                        libro.setAnioLibro(Short.parseShort(partes[3]));

                        Editorial editorial = editorialServicio.getOne(partes[4]);
                        libro.setIdEditorial(editorial);

                        Autor autor = autorServicio.getOne(partes[5]);
                        libro.setIdAutor(autor);

                        libros.add(libro);
                    }
                }
            } catch (IOException | NumberFormatException e2) {
                // Manejar la excepcion o registrarla
            }
        }
        return libros;
    }

    @Override
    public int numRows() {
        int contador = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while (br.readLine() != null) {
                contador++;
            }
        } catch (IOException e) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(resourcePath)))) {
                while (br.readLine() != null) {
                    contador++;
                }
            } catch (IOException e2) {
                // Manejar la excepcion o registrarla
            }
        }
        return contador;
    }

    @Override
    public Boolean delectFrom(String coidgo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Libro updateSet(String codigo, Libro objeto, String ruta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Libro getOne(String codigo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}