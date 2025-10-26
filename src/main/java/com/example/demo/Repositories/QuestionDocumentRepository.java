package com.example.demo.Repositories;

import com.example.demo.Models.QuestionElasticDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;


public interface QuestionDocumentRepository extends ElasticsearchRepository<QuestionElasticDocument, String> {
    List<QuestionElasticDocument> findByTitleContainingOrContentContaining(String title, String content);
}
