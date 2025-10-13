package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Likes")
public class Like {
   @Id
    private String id;

    private String targetId;

//    TODO: Add enum for targettype
    private String targetType;

    private Boolean isLike;

    @CreatedDate
    private LocalDateTime createdAt;

}
