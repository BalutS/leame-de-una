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
import org.unimag.modelo.Autor;

public class AutorServicio implements ApiOperacionBD<Autor, String> {

    private final String resourcePath = "/org/unimag/recurso/autores.txt";
    private final String filePath = System.getProperty("user.home") + "/autores.txt";

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
    public Autor insertInto(Autor autor, String ruta) {
        autor.setIdAutor(getSerial());
        String registro = autor.getIdAutor() + ";" + autor.getNombreAutor() + ";" + autor.getGeneroAutor();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(registro);
            bw.newLine();
        } catch (IOException e) {
            return null;
        }
        return autor;
    }

    @Override
    public List<Autor> selectFrom() {
        List<Autor> autores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] partes = linea.split(";");
                    Autor autor = new Autor();
                    autor.setIdAutor(Integer.parseInt(partes[0]));
                    autor.setNombreAutor(partes[1]);
                    autor.setGeneroAutor(Boolean.valueOf(partes[2]));
                    autores.add(autor);
                }
            }
        } catch (IOException e) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(resourcePath)))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (!linea.trim().isEmpty()) {
                        String[] partes = linea.split(";");
                        Autor autor = new Autor();
                        autor.setIdAutor(Integer.parseInt(partes[0]));
                        autor.setNombreAutor(partes[1]);
                        autor.setGeneroAutor(Boolean.valueOf(partes[2]));
                        autores.add(autor);
                    }
                }
            } catch (IOException e2) {
                // Manejar la excepcion o registrarla
            }
        }
        return autores;
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
    public Autor updateSet(String codigo, Autor objeto, String ruta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Autor getOne(String codigo) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] partes = linea.split(";");
                    if (partes[0].equals(codigo)) {
                        Autor autor = new Autor();
                        autor.setIdAutor(Integer.parseInt(partes[0]));
                        autor.setNombreAutor(partes[1]);
                        autor.setGeneroAutor(Boolean.valueOf(partes[2]));
                        return autor;
                    }
                }
            }
        } catch (IOException e) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(resourcePath)))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (!linea.trim().isEmpty()) {
                        String[] partes = linea.split(";");
                        if (partes[0].equals(codigo)) {
                            Autor autor = new Autor();
                            autor.setIdAutor(Integer.parseInt(partes[0]));
                            autor.setNombreAutor(partes[1]);
                            autor.setGeneroAutor(Boolean.valueOf(partes[2]));
                            return autor;
                        }
                    }
                }
            } catch (IOException e2) {
                // Manejar la excepcion o registrarla
            }
        }
        return null;
    }
}