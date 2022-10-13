package com.dh.catalogservice.api.service;

import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.rerpositories.IMovieRepository;
import com.dh.catalogservice.shared.GenericServiceImpl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService extends GenericServiceImpl<Movie,Long>  implements IMovieService{
    private final IMovieRepository repository;

    public MovieService(IMovieRepository repository) {
        this.repository = repository;
    }

    public List<Movie> findMovieByGenre(String genre){
        return repository.findAllByGenre(genre);
    }
    @Override
    public MongoRepository<Movie, Long> getRepository() {
        return repository;
    }
}
