package com.alejandro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.stream.Stream;

public class ExamenReal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce la ruta del directorio1 ");
        Path rutaLectura = Path.of(sc.nextLine());
        System.out.println("Introduce la ruta del directorio2 ");
        Path rutaEscritura = Path.of(sc.nextLine());

        if (!Files.isDirectory(rutaLectura) || !Files.isDirectory(rutaEscritura)) {
            System.out.println("❌ Alguna ruta no es un directorio.");
            return;
        }
        if (!Files.isReadable(rutaLectura) || !Files.isWritable(rutaEscritura)) {
            System.out.println("❌ Permisos incorrectos.");
            return;
        }

        try (Stream<Path> archivos = Files.list(rutaLectura)){
            archivos.filter(Files::isRegularFile).filter(path -> path.toString().endsWith(".txt"))
                    .filter(path -> {
                        try { return Files.size(path) > 1024; }
                        catch (IOException e) { return false; }
                    })
                    .filter(path -> {
                        try {
                            return Files.readString(path).trim().toLowerCase().startsWith("copiar");
                        } catch (IOException e) {
                            return false;
                        }
                    })
                    .forEach(path -> {
                        try {
                            Files.copy(path, rutaEscritura.resolve(path.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("Copiado: " + path.getFileName());
                        } catch (IOException e) {
                            System.out.println("Error copiando: " + path.getFileName());
                        }
                    });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
