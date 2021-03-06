package com.martinsaman.personaservice.events.database;

import com.google.gson.Gson;
import com.martinsaman.personaservice.events.database.DatabaseDto;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class DatabaseProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topic.db-events}")
    private String topicName;

    private void enviar(String dataDto) {
        kafkaTemplate.send(topicName, dataDto);
    }

    public void enviarEvento(DatabaseDto databaseDto) {
        //Transformar a json string y enviar
        Gson gson = new Gson();
        String json = gson.toJson(databaseDto);
        System.out.println("Enviado a dbtopic:");
        System.out.println(json);
        enviar(json);
    }
}
