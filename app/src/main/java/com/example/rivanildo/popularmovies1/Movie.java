package com.example.rivanildo.popularmovies1;

/**
 * Created by Rivanildo on 10/04/16.
 */
public class Movie {
    private String linkImg;
    private String sinopse;
    private String dataRelease;
    private String tituloOriginal;
    private String avaliacao;

    public Movie(String linkImg, String sinopse, String dataRelease, String tituloOriginal, String avaliacao) {
        this.linkImg = linkImg;
        this.sinopse = sinopse;
        this.dataRelease = dataRelease;
        this.tituloOriginal = tituloOriginal;
        this.avaliacao = avaliacao;

    }

    public String toString() {
        return "Link: " + linkImg + " - Data: " + dataRelease + " Titulo Original: " + tituloOriginal + " avaliacao: " + avaliacao  + " Sinopse: " + sinopse;
    }

    public String getLinkImg() {
        return linkImg;
    }

    public String getSinopse() {
        return sinopse;
    }

    public String getDataRelease() {
        return dataRelease;
    }

    public String getTituloOriginal() {
        return tituloOriginal;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

}
