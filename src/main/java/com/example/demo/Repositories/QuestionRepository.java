package com.example.demo.Repositories;

import com.example.demo.Models.Question;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question,String> {
    // process the data in the form of stream not list it act as publisher
//    Flux<Question> findByAuthorId(String authorId);
//    gives only single value
//    Mono<Long> countByAuthorId(String authorId);
}
