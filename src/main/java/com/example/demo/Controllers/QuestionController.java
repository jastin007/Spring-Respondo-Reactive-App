package com.example.demo.Controllers;

import com.example.demo.Dto.QuestionRequestDTO;
import com.example.demo.Dto.QuestionResponseDTO;
import com.example.demo.Models.QuestionElasticDocument;
import com.example.demo.Service.IQuestionService;
import com.example.demo.adapter.QuestionAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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
    @GetMapping("/{id}")
    public Mono<QuestionResponseDTO> getQuestionById(@PathVariable String id){
        return questionService.getQuestionById(id).doOnSuccess(response-> System.out.println("id found: "+response))
                .doOnError(error-> System.out.println("id not found : "+error));
    }

    @GetMapping
    public Flux<QuestionResponseDTO> getAllQuestions(@RequestParam(required=false) String cursor,@RequestParam(defaultValue = "10") int size){
        return questionService.getAllQuestions(cursor,size).doOnError(error-> System.out.println("Error fetching questions: "+error))
                .doOnComplete(()-> System.out.println("Questions fetched successfully"));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteQuestionById(@PathVariable String id){
        return questionService.deleteQuestionById(id);
    }

    @GetMapping("/search")
    public Flux<QuestionResponseDTO> searchQuestions(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return questionService.SearchByQuestion(query,page,size);
    }

    @GetMapping("/elasticsearch")
    public Flux<QuestionElasticDocument> searchQuestionsByElasticSearch(@RequestParam String query){
        return questionService.searchQuestionsByElasticSearch(query);
    }


}
