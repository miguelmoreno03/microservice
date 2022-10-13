package com.dh.catalogservice.api.service;

import com.dh.catalogservice.api.client.MovieServiceClient;
import com.dh.catalogservice.api.client.SerieServiceClient;
import com.dh.catalogservice.domain.model.Catalog;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Serie;
import com.dh.catalogservice.rerpositories.ICatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatalogService {
    private final ICatalogRepository repository;

    private final MovieServiceClient movieClient;

    private final SerieServiceClient serieClient;



    public Catalog findCatalogByGenre(String genre){

        Catalog catalog= repository.findByGenre(genre);
        if (catalog == null){
            return null;
        }
        List<Movie> movies = movieClient.getMovieByGenre(genre);
        List<Serie> series = serieClient.getSerieByGenre(genre);
        assert catalog != null;
        movies.forEach(catalog::addMovie);
        series.forEach(catalog::addSerie);
        return catalog;

    }

    public Movie crearMovie(Movie movie){
        return movieClient.saveMovie(movie);
    }

    public Movie actualizarMovie(Movie movie){
       return  movieClient.updateMovie(movie);
    }

    public Serie crearSerie(Serie serie){
     return serieClient.save(serie);
    }

    public Serie actualizarSerie(Serie serie){
      return serieClient.update(serie);
    }


}
