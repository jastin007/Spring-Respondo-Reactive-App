package com.example.demo.Service;

import com.example.demo.Models.Question;
import com.example.demo.Models.QuestionElasticDocument;
import reactor.core.publisher.Mono;

public interface IQuestionIndexService {
    Mono<Void> createQuestionIndex(Question questions);

}
