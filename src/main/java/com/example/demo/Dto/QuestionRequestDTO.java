package com.example.demo.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequestDTO {

    @NotBlank(message="Tiltle is required")
    @Size(min=10,max=100,message = "Title must be between 10 and 100 character")
    private String title;

    @NotBlank(message="Content is required")
    @Size(min=10, max=1000, message = "content must be between 10 and 1000 characters")
    private String content;


}
