package com.example.demo.Service;

import com.example.demo.Dto.QuestionRequestDTO;
import com.example.demo.Dto.QuestionResponseDTO;
import com.example.demo.Models.Question;
import reactor.core.publisher.Mono;

public interface IQuestionService {
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO);

}
