package com.example.demo.adapter;

import com.example.demo.Dto.QuestionResponseDTO;
import com.example.demo.Models.Question;

public class QuestionAdapter {
    public static QuestionResponseDTO toquestionResponseDTO(Question question){
        return QuestionResponseDTO.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .build();
    }
}
