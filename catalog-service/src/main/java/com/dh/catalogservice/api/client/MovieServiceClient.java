package com.dh.catalogservice.api.client;

import com.dh.catalogservice.domain.model.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name ="movie-service")
public interface MovieServiceClient {

    @GetMapping("/movie/{genre}")
    List<Movie> getMovieByGenre(@PathVariable String genre);
    @PostMapping("/movie/save")
    Movie saveMovie(@RequestBody Movie movie);
    @PutMapping("/movie/update")
    Movie updateMovie(@RequestBody Movie movie);

}
