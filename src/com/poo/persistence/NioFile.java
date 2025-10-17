package com.poo.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;

public class NioFile {

    private final Path path;

    public NioFile(String filePath) throws IOException {
        this.path = Paths.get(filePath);
        if (Files.notExists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }
        if (Files.notExists(path)) {
            Files.createFile(path);
        }
    }

    public int ultimoCodigo() throws IOException {
        List<String> lines = Files.readAllLines(path);
        if (lines.isEmpty()) {
            return 0;
        }
        String lastLine = lines.get(lines.size() - 1);
        String[] parts = lastLine.split(";");
        if (parts.length > 0 && !parts[0].isEmpty()) {
            try {
                return Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }

    public boolean agregarRegistro(String row) {
        try {
            Files.write(path, Collections.singletonList(row), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getAllLines() throws IOException {
        return Files.readAllLines(path);
    }
}