package com.dh.serieservice.services;

import com.dh.serieservice.models.Serie;
import com.dh.serieservice.queue.SerieSender;
import com.dh.serieservice.repositories.SerieRepository;
import com.dh.serieservice.shared.GenericServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SerieServiceImpl extends GenericServiceImpl<Serie,Long> implements SerieServiceAPI {
    private  final SerieRepository repository;
    private final SerieSender sender;
    @Override
    public MongoRepository<Serie, Long> getRepository() {
        return repository;
    }
    @Override
    public Serie save(Serie entity){
        var sDB=super.save(entity);
        sender.sendSerie(sDB);
        return sDB;
    }
    public Serie update(Serie serie){
        Optional<Serie> sBus = repository.findById(serie.getId());
        if(sBus.isPresent()){
            return  save(serie);
        }else{
            return null;
        }
    }

    @Override
    public List<Serie> findAllByGenre(String serie) {
        return repository.findAllByGenre(serie);
    }
}
