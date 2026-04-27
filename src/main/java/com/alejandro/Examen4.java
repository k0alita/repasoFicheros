package com.alejandro;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Examen4 {
    public static void main(String[] args) {
        Path entrada = Path.of("empleados.txt");
        Path rutaValidos = Path.of("validos.txt");
        Path rutaInvalidos = Path.of("invalidos.txt");

        String regex = "^[A-Za-záéíóúÁÉÍÓÚ]{2,} [A-Za-záéíóúÁÉÍÓÚ]{2,} [A-Za-záéíóúÁÉÍÓÚ]{2,} [1-9][0-9]?$";
        Pattern patron = Pattern.compile(regex);

        try (Stream<String> lineas = Files.lines(entrada);
             PrintWriter validos = new PrintWriter(Files.newBufferedWriter(rutaValidos));
             PrintWriter invalidos = new PrintWriter(Files.newBufferedWriter(rutaInvalidos))) {

            // Necesitamos el número de línea, usamos un array para poder usarlo en la lambda
            int[] numLinea = {0};

            lineas.forEach(linea -> {
                numLinea[0]++;
                Matcher matcher = patron.matcher(linea);

                if (matcher.matches()) {
                    validos.println(linea);
                } else {
                    invalidos.println("Línea " + numLinea[0] + ": " + linea);
                }
            });

            System.out.println("✅ Ficheros generados correctamente.");

        } catch (IOException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
