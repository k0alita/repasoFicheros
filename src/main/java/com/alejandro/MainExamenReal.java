package com.alejandro;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainExamenReal {
    private static ArrayList<Episodio> cargarEpisodios() throws IOException {
        Gson gson = new Gson();
        String json = Files.readString(Path.of("simpsons.json"));
        Type tipo = new TypeToken<ArrayList<Episodio>>(){}.getType();
        return gson.fromJson(json, tipo);
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Episodio> episodios = cargarEpisodios();

        episodios.stream()
                .filter(episodio -> episodio.getAnioEmision() > 1992)
                .forEach(e -> System.out.println(e.getTitulo() + " - " + e.getAnioEmision()));

        ArrayList<Episodio> largos = episodios.stream()
                .filter(e -> e.getSinopsis().trim().split("\\s+").length > 20)
                .collect(Collectors.toCollection(ArrayList::new));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Files.writeString(Path.of("sinopsis_largas.json"), gson.toJson(largos));
        System.out.println("generado");

        // La regex que lo hace todo a la vez
        String regex = "(Homer|Marge|Bart|Lisa|Maggie)";

// Creamos una copia de la lista con las sinopsis modificadas
        ArrayList<Episodio> marcados = new ArrayList<>();

        for (Episodio e : episodios) {
            // replaceAll sustituye TODAS las ocurrencias con "**nombre**"
            String sinopsisMarcada = e.getSinopsis().replaceAll(regex, "**$1**");
            Episodio copia = new Episodio(e.getTitulo(), e.getAnioEmision(), sinopsisMarcada);
            marcados.add(copia);
        }

        Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
        Files.writeString(Path.of("simpsons_marcados.json"), gson2.toJson(marcados));
        System.out.println("✅ simpsons_marcados.json generado");
    }




}
