package com.dh.catalogservice.rerpositories;

import com.dh.catalogservice.domain.model.Catalog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatalogRepository extends MongoRepository<Catalog,Long> {
    Catalog findByGenre(String genre);
}
