package com.dh.catalogservice.queue;

import com.dh.catalogservice.api.service.SerieService;
import com.dh.catalogservice.domain.model.Serie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SerieListener {
    public  final SerieService service;

    public SerieListener(SerieService service) {
        this.service = service;
    }
    @RabbitListener(queues = {"queue.series.name"})
    public void receiveSerie(@Payload Serie serie){
        var r = service.save(serie);
        log.info(r.toString());
    }
}
