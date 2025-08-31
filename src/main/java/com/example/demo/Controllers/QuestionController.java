package com.example.demo.Controllers;

import com.example.demo.Dto.QuestionRequestDTO;
import com.example.demo.Dto.QuestionResponseDTO;
import com.example.demo.Service.IQuestionService;
import com.example.demo.adapter.QuestionAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final IQuestionService questionService;

    @PostMapping()
    public Mono<QuestionResponseDTO> createQuestion(@RequestBody QuestionRequestDTO questionRequestDTO){
           return questionService.createQuestion(questionRequestDTO)
                   .doOnSuccess(response-> System.out.println("Question created successfully: "+response))
                   .doOnError(error-> System.out.println("Error creating question: "+error));
    }
}
