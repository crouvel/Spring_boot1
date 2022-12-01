package com.example.demo.service;

import com.example.demo.repository.OfferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OfferConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfferConsumer.class);

    private OfferRepository dataRepository;

    public OfferConsumer(OfferRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(String eventMessage){

        LOGGER.info(String.format("Nouvelle offre publiée dans la ville de --> %s", eventMessage));

    }

}
