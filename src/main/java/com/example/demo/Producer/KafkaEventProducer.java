package com.example.demo.Producer;

import com.example.demo.config.KafkaConfig;
import com.example.demo.events.ViewCountEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaEventProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;


    public void publishViewCountEvent(ViewCountEvent viewCountEvent){
          kafkaTemplate.send(KafkaConfig.TOPIC_NAME, viewCountEvent.getTargetId(), viewCountEvent)
                  .whenComplete((result, err)->{
                     if(err !=null){
                         System.out.println("Error publishing view count event: "+err.getMessage());
                     }
                  });
    }
}
