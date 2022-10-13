package com.dh.catalogservice.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Document(collection ="Catalogs")
public class Catalog {
    @Id
    private Long id;
    private String genre;
    @Transient
    private List<Movie> movies;
    @Transient
    private List<Serie> series;

    public Catalog(){
        this.movies=new ArrayList<>();
        this.series=new ArrayList<>();
    }

    public Catalog(Long id, String genre, List<Movie> movies, List<Serie> series) {
        this.id = id;
        this.genre = genre;
        this.movies = movies;
        this.series = series;
    }

    public void addMovie(Movie movie){
        this.movies.add(movie);
    }
    public void addSerie(Serie serie){
        this.series.add(serie);
    }
}
