package com.dh.serieservice.data;

import com.dh.serieservice.models.Serie;
import com.dh.serieservice.services.SerieServiceAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataLoader implements ApplicationRunner {

    private  final  SerieServiceAPI service;
    private final MongoTemplate mongoTemplate;

    public DataLoader(SerieServiceAPI service, MongoTemplate mongoTemplate) {
        this.service = service;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        mongoTemplate.dropCollection("series");
        var s1= new Serie();
        s1.setName("game of trones");
        s1.setGenre("adventure");
        s1.setSeasons(2);

        var sDB1= service.save(s1);
        log.info(sDB1.toString());
        var s2= new Serie();
        s1.setName("Breaking Bad");
        s1.setGenre("drama");
        s1.setSeasons(5);

        var sDB2= service.save(s2);
        log.info(sDB2.toString());


    }
}
