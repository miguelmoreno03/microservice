package com.dh.serieservice.repositories;

import com.dh.serieservice.models.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SerieRepository  extends MongoRepository<Serie,Long> {

    List<Serie> findAllByGenre(String serie);
}
