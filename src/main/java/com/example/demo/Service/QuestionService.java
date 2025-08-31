package com.example.demo.Service;

import com.example.demo.Dto.QuestionRequestDTO;
import com.example.demo.Dto.QuestionResponseDTO;
import com.example.demo.Models.Question;
import com.example.demo.Repositories.QuestionRepository;
import com.example.demo.adapter.QuestionAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService{

    private final QuestionRepository questionRepository;


    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO) {
        Question question = Question.builder()
                .title(questionRequestDTO.getTitle())
                .content(questionRequestDTO.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now()).build();

       return questionRepository.save(question).map(QuestionAdapter::toquestionResponseDTO)
                .doOnSuccess(response -> System.out.println("Question created sucessfully: " + response))
                .doOnError(error-> System.out.println("Error creating question: " + error));

    }
}
