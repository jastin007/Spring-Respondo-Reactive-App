package com.example.demo.Service;

import com.example.demo.Models.Question;
import com.example.demo.Models.QuestionElasticDocument;
import com.example.demo.Repositories.QuestionDocumentRepository;
import com.example.demo.Repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;




@Service
@RequiredArgsConstructor
public class QuestionIndexService implements IQuestionIndexService{

    private final QuestionDocumentRepository questionDocumentRepository;

    @Override
    public Mono<Void> createQuestionIndex(Question questions) {
        QuestionElasticDocument document = QuestionElasticDocument.builder()
                .id(questions.getId())
                .title(questions.getTitle())
                .content(questions.getContent())
                .build();

        questionDocumentRepository.save(document);
        return null;
    }

   
}
