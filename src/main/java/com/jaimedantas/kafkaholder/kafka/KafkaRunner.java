/*
package com.jaimedantas.kafkaholder.kafka;

import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.event.ListenerContainerIdleEvent;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class KafkaRunner {

    private final String LISTENER_ID = "myid";// consumer group id
    private final String LISTENER_ID_DASH = LISTENER_ID + "-";
    private Set<String> consumerIds = new HashSet<>();

    private final KafkaListenerEndpointRegistry registry;
    private KafkaTemplate<GenericRecord, GenericRecord> kafkaTemplate;


    @Autowired
    public KafkaRunner(KafkaListenerEndpointRegistry registry, KafkaTemplate<GenericRecord, GenericRecord> kafkaTemplate) {
        this.registry = registry;
        this.kafkaTemplate = kafkaTemplate;
    }

    // setting my groud id, and Kafka topic - "reddit_posts", it's created by default when you spin up Kafka Docker image.
    @KafkaListener(id = LISTENER_ID, topics = "reddit_posts")
    public void receive(GenericRecord payload) {
        //transformation on message, GenericRecord is an avro message type.
        payload.put("body", payload.get("body").toString().toUpperCase());
        //send data to next kafka topic
        kafkaTemplate.send("reddit_posts_modified", payload);
    }

    //Listen for the events, when EACH Consumer will be idle for 3 seconds it will emit event
    //"condition" is our filter, which will accept only events which starts with consumer group id, in our case it's a "myid"
    @EventListener(condition = "event.listenerId.startsWith('" + LISTENER_ID_DASH + "')")
    public void eventHandler(ListenerContainerIdleEvent event) {
        countEventsAndDeregister(event);
    }

    //Note that this method is synchronized, because events are emmited from 5 consumers, 5 different threads..
    private synchronized void countEventsAndDeregister(ListenerContainerIdleEvent event) {
        //check if all consumers in our hashset
        //if yes, all consumers finished reading all messages
        //and we can terminate application
        if (consumerIds.size() > 4) {
            kafkaTemplate.flush();
            //terminating application
            registry.stop();
        }
        //add only unique consumer id to hashset.
        //remember we need 5 unique ids.
        //why we do it this way?
        //Each consumer/thread will emit continuosly every 3 sec event when it's idle
        //so we don't want to stop application because one of thread finished it's job faster and emited 5 events.
        // in hashset we keep only unique id's of consumers/threads
        consumerIds.add(event.getListenerId());
    }

}
*/