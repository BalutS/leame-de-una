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
import org.unimag.modelo.Editorial;

public class EditorialServicio implements ApiOperacionBD<Editorial, String> {

    private final String resourcePath = "/org/unimag/recurso/editoriales.txt";
    private final String filePath = System.getProperty("user.home") + "/editoriales.txt";

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
    public Editorial insertInto(Editorial editorial, String ruta) {
        editorial.setIdEditorial(getSerial());
        String registro = editorial.getIdEditorial() + ";" + editorial.getNombreEditorial() + ";" + editorial.getPaisEditorial() + ";" + editorial.getFormatoEditorial();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(registro);
            bw.newLine();
        } catch (IOException e) {
            return null;
        }
        return editorial;
    }

    @Override
    public List<Editorial> selectFrom() {
        List<Editorial> editoriales = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] partes = linea.split(";");
                    Editorial editorial = new Editorial();
                    editorial.setIdEditorial(Integer.parseInt(partes[0]));
                    editorial.setNombreEditorial(partes[1]);
                    editorial.setPaisEditorial(partes[2]);
                    editorial.setFormatoEditorial(Short.parseShort(partes[3]));
                    editoriales.add(editorial);
                }
            }
        } catch (IOException e) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(resourcePath)))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (!linea.trim().isEmpty()) {
                        String[] partes = linea.split(";");
                        Editorial editorial = new Editorial();
                        editorial.setIdEditorial(Integer.parseInt(partes[0]));
                        editorial.setNombreEditorial(partes[1]);
                        editorial.setPaisEditorial(partes[2]);
                        editorial.setFormatoEditorial(Short.parseShort(partes[3]));
                        editoriales.add(editorial);
                    }
                }
            } catch (IOException e2) {
                // Manejar la excepcion o registrarla
            }
        }
        return editoriales;
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
    public Editorial updateSet(String codigo, Editorial objeto, String ruta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Editorial getOne(String codigo) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] partes = linea.split(";");
                    if (partes[0].equals(codigo)) {
                        Editorial editorial = new Editorial();
                        editorial.setIdEditorial(Integer.parseInt(partes[0]));
                        editorial.setNombreEditorial(partes[1]);
                        editorial.setPaisEditorial(partes[2]);
                        editorial.setFormatoEditorial(Short.parseShort(partes[3]));
                        return editorial;
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
                            Editorial editorial = new Editorial();
                            editorial.setIdEditorial(Integer.parseInt(partes[0]));
                            editorial.setNombreEditorial(partes[1]);
                            editorial.setPaisEditorial(partes[2]);
                            editorial.setFormatoEditorial(Short.parseShort(partes[3]));
                            return editorial;
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