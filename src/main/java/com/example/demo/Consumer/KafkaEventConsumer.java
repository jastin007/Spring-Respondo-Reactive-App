package com.example.demo.Consumer;

import com.example.demo.Repositories.QuestionRepository;
import com.example.demo.config.KafkaConfig;
import com.example.demo.events.ViewCountEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaEventConsumer {
        private final QuestionRepository questionRepository;
        @KafkaListener(topics =KafkaConfig.TOPIC_NAME,
                groupId = "view-count-consumer-group",
               containerFactory = "kafkaListenerContainerFactory")
        public void handleViewCountEvent(ViewCountEvent event){
            // if we want another mono and we need asynchrously flat another mono we need to use this
             questionRepository.findById(event.getTargetId()).
                     flatMap(question->{
                         System.out.println("Incrementing view count for question: "+question.getId());
                         Integer views = question.getViews();
                         question.setViews(question.getViews()==null ? 0 : question.getViews() +1);
                         return questionRepository.save(question);
                     }).subscribe(updatedQuestion->{
                         System.out.println("View count incremented for question:"+updatedQuestion.getId());
                     }, error->{
                         System.out.println("Error incrementing view count for questions: "+ error.getMessage());
                     });

        }
    }

