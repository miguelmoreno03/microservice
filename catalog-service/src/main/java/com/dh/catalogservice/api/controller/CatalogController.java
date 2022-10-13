package com.dh.catalogservice.api.controller;

import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.model.Catalog;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Serie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class CatalogController {

    private final CatalogService service;
    private final CircuitBreakerFactory cbFactory;

    public CatalogController(CatalogService service, CircuitBreakerFactory cbFactory) {
        this.service = service;
        this.cbFactory = cbFactory;
    }


    @GetMapping("/{genre}")
   Catalog findByGenre(@PathVariable String genre) {
        return cbFactory.create("items").run(()->service.findCatalogByGenre(genre),throwable -> metodoAlternativo(genre ,throwable));

    }
    public Catalog metodoAlternativo(@PathVariable String genre,Throwable ex){
        Catalog item = new Catalog(9999L,genre,null,null);
        log.info("Response 200,fallback method for error: {}",ex.getMessage());
        return item;
    }
    @PostMapping("/movie/save")
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie){return ResponseEntity.ok().body(service.crearMovie(movie));}
    @PostMapping("/series/save")
    ResponseEntity<Serie> saveSerie(@RequestBody Serie serie){return ResponseEntity.ok().body(service.crearSerie(serie));}
    @PutMapping("/movie/update")
    ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) {return  ResponseEntity.ok().body(service.actualizarMovie(movie));}

    @PutMapping("/series/update")
    ResponseEntity<Serie> updateSerie(@RequestBody Serie serie) {return  ResponseEntity.ok().body(service.actualizarSerie(serie));}

}
