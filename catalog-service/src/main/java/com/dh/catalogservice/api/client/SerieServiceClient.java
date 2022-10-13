package com.dh.catalogservice.api.client;

import com.dh.catalogservice.domain.model.Serie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "serie-service")
public interface SerieServiceClient {
    @GetMapping ("/series/{genre}")
    List<Serie> getSerieByGenre(@PathVariable String genre);

    @PostMapping("/series/")
    Serie save(@RequestBody Serie serie);

    @PutMapping("/series/update")
    Serie update(@RequestBody Serie serie);

}
