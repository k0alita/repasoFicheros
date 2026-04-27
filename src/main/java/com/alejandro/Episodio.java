package com.alejandro;

public class Episodio {
    private String titulo;
    private int anioEmision;
    private String sinopsis;

    public Episodio(String titulo, int anioEmision, String sinopsis) {
        this.titulo = titulo;
        this.anioEmision = anioEmision;
        this.sinopsis = sinopsis;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnioEmision() {
        return anioEmision;
    }

    public void setAnioEmision(int anioEmision) {
        this.anioEmision = anioEmision;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }



    @Override
    public String toString() {
        return titulo + " (" + anioEmision + ")";
    }
}
