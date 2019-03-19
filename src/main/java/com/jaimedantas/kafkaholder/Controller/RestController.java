package com.jaimedantas.kafkaholder.Controller;


import com.jaimedantas.Payment;
import com.jaimedantas.kafkaholder.kafka.DummyProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping(path="/kafka")
public class RestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private DummyProducer producer;

    @CrossOrigin(origins = "*")
    @PostMapping(path="/add")
    public @ResponseBody String add (@RequestBody Payment payment) {
        producer.send(payment);
        return "Saved";
    }


}