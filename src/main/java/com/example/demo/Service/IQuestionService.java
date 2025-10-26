package com.example.demo.Service;

import com.example.demo.Dto.QuestionRequestDTO;
import com.example.demo.Dto.QuestionResponseDTO;
import com.example.demo.Models.Question;
import com.example.demo.Models.QuestionElasticDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IQuestionService {
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO);

    public Mono<QuestionResponseDTO> getQuestionById(String id);

    public Flux<QuestionResponseDTO> getAllQuestions(String cursor, int size);

    public Mono<Void> deleteQuestionById(String id);

    public Flux<QuestionResponseDTO> SearchByQuestion(String query,int page,int size);

    public Flux<QuestionElasticDocument> searchQuestionsByElasticSearch(String query);
}
