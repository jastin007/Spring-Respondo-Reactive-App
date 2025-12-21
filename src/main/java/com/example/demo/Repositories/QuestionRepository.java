package com.example.demo.Repositories;

import com.example.demo.Models.Question;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question,String> {
    // process the data in the form of stream not list it act as publisher
//    Flux<Question> findByAuthorId(String authorId);
//    gives only single value
//    Mono<Long> countByAuthorId(String authorId);
    @Query("{ '$or': [ {'title':{$regex:?0, $options: 'i'} }, {'content':{$regex:?0,$options: 'i'} }] }")
    Flux<Question> findByTitleOrContainingIgnoreCase(String searchterm,  Pageable pageable);

    Flux<Question> findByCreatedAtGreaterThanOrderByCreatedAtAsc(LocalDateTime cursorTimeStamp, Pageable pageable);

    Flux<Question> findTop10ByOrderByCreatedAtAsc();
}
