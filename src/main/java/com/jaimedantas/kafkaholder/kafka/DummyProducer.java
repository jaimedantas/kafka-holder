package com.jaimedantas.kafkaholder.kafka;

import com.jaimedantas.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DummyProducer {

    private static final Logger LOG = LoggerFactory.getLogger(DummyProducer.class);

    @Autowired
    private KafkaTemplate<String, Payment> kafkaTemplate;

    private String topic = "brazil";

    public void send(Payment message){
        LOG.info("sending message='{}' to topic='{}'", message.toString(), topic);
        kafkaTemplate.send(topic, message);
    }
}