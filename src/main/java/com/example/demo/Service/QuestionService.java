package com.example.demo.Service;

import com.example.demo.Dto.QuestionRequestDTO;
import com.example.demo.Dto.QuestionResponseDTO;
import com.example.demo.Models.Question;
import com.example.demo.Repositories.QuestionRepository;
import com.example.demo.Utils.CursorUtils;
import com.example.demo.adapter.QuestionAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {

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
                .doOnError(error -> System.out.println("Error creating question: " + error));

    }

    @Override
    public Mono<QuestionResponseDTO> getQuestionById(String id) {
        return questionRepository.findById(id).map(QuestionAdapter::toquestionResponseDTO)
                .doOnSuccess(response -> System.out.println("id found sucessfully: " + response))
                .doOnError(error -> System.out.println("id not found: " + error));
    }

    @Override
    public Flux<QuestionResponseDTO> getAllQuestions(String cursor, int size) {
        Pageable pageable = PageRequest.of(0,size);
        if(!CursorUtils.isValidCursor(cursor)){
            return questionRepository.findTop10ByOrderByCreatedAtAsc()
                    .take(size)
                    .map(QuestionAdapter::toquestionResponseDTO)
                    .doOnError(error-> System.out.println("Error fetching questions: "+error))
                    .doOnComplete(()-> System.out.println("Questions fetched successfully"));
        }else{
           LocalDateTime cursorTimeStamp = CursorUtils.parseCursor(cursor);
           return questionRepository.findByCreatedAtGreaterThanOrderByCreatedAtAsc(cursorTimeStamp, pageable)
                   .map(QuestionAdapter::toquestionResponseDTO)
                   .doOnError(error-> System.out.println("Error fetching questions: "+error))
                   .doOnComplete(()-> System.out.println("Questions fetched successfully"));
        }
    }

//    @Override
//    public Flux<QuestionResponseDTO> getAllQuestions() {
//        return questionRepository.findAll().map(QuestionAdapter::toquestionResponseDTO).
//                doOnNext(response -> System.out.println("id found sucessfully: " + response))
//                .doOnError(error -> System.out.println("id not found: " + error));
//    }

    @Override
    public Mono<Void> deleteQuestionById(String id) {
//        return questionRepository.deleteById(id).doOnError(error -> System.out.println("Error deleting " + id + ": " + error.getMessage()))
//                .doOnTerminate(() -> System.out.println("Delete process completed for id: " + id));

        return questionRepository.existsById(id).flatMap(exits -> {
            if (exits) {
                return questionRepository.deleteById(id).doOnTerminate(() -> System.out.println("Delete process completed for id: " + id));
            } else {
                System.out.println("⚠️ No record found for id: " + id);
                return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,"Id not found: " + id));
            }
        });
    }

    @Override
    public Flux<QuestionResponseDTO> SearchByQuestion(String searchTerm, int offset, int page) {
        return questionRepository.findByTitleOrContainingIgnoreCase(searchTerm, PageRequest.of(offset, page))
                .map(QuestionAdapter::toquestionResponseDTO)
                .doOnError(error-> System.out.println("Error searching questions:"+error))
                .doOnComplete(()-> System.out.println("Questions searched successfully: "));
    }
}



