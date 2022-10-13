package com.dh.serieservice.controller;

import com.dh.serieservice.models.Serie;
import com.dh.serieservice.services.SerieServiceAPI;
import com.dh.serieservice.services.SerieServiceImpl;
import com.dh.serieservice.shared.GenericRestControllers;
import com.dh.serieservice.shared.GenericServiceAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class SerieRestController extends GenericRestControllers<Serie,Long> {
    private final SerieServiceImpl serviceAPI;

    public SerieRestController(SerieServiceImpl serviceAPI) {
        super(serviceAPI);
        this.serviceAPI=serviceAPI;
    }
    @GetMapping("/all")
    public List<Serie> findAll(){
        return serviceAPI.getAll();
    }
    @PostMapping
    public ResponseEntity <Serie> save (@RequestBody Serie serie){
        return ResponseEntity.ok().body(serviceAPI.save(serie));
    }
    @GetMapping("/{genre}")
    public ResponseEntity<List<Serie>> findAllByGenre(@PathVariable String genre){
        return ResponseEntity.ok().body(serviceAPI.findAllByGenre(genre));
    }
    @PutMapping("/update")
    public ResponseEntity<Serie> update (@RequestBody Serie serie){
        return ResponseEntity.ok().body(serviceAPI.update(serie));
    }
}
