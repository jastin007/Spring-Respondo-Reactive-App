package com.example.demo.Service;

import com.example.demo.Dto.AnswerRequestDTO;
import com.example.demo.Dto.AnswerResponseDTO;
import reactor.core.publisher.Mono;

public interface IAnswerService {
    public Mono<AnswerResponseDTO> createAnswer(AnswerRequestDTO answerRequestDTO);
    public Mono<AnswerResponseDTO>  getAnswerById(String id);
}
