package com.dh.catalogservice.shared;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class GenericServiceImpl <T, ID extends Serializable> implements GenericServiceAPI<T, ID>{
    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public T getOne(ID id) {
        Optional<T> optional = getRepository().findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<T> getAll() {
        return getRepository().findAll();
    }

    @Override
    public abstract MongoRepository<T, ID> getRepository();
}
