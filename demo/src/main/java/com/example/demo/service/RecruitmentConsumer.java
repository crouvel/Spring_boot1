package com.example.demo.service;


import com.example.demo.repository.ChercheurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RecruitmentConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecruitmentConsumer.class);

    private ChercheurRepository dataRepository;

    public RecruitmentConsumer(ChercheurRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name2}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(String eventMessage){

        LOGGER.info(String.format("Notification recrutement --> %s", eventMessage));

    }

}
