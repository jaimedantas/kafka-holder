package com.jaimedantas.kafkaholder.kafka;

import com.jaimedantas.Register;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class DummyConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(DummyConsumer.class);

    @KafkaListener(topics = "canada")
    public void listen(@Payload Register message) {
        LOG.info("received message='{}'", message.toString());
    }

}