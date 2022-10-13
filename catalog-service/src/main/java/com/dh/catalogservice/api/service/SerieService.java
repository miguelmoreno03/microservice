package com.dh.catalogservice.api.service;

import com.dh.catalogservice.domain.model.Serie;
import com.dh.catalogservice.rerpositories.ISerieRepository;
import com.dh.catalogservice.shared.GenericServiceImpl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService extends GenericServiceImpl<Serie,Long> implements ISerieService {
    private final ISerieRepository repository;

    public SerieService(ISerieRepository repository) {
        this.repository = repository;
    }


    public List<Serie> findSeriesByGenre(String genre){
        return repository.findAllByGenre(genre);
    }

    @Override
    public MongoRepository<Serie, Long> getRepository() {
        return repository;
    }
}
