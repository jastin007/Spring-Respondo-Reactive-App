package com.example.demo.Service;

import com.example.demo.Dto.LikeRequestDTO;
import com.example.demo.Dto.LikeResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ILikeService {
    Mono<LikeResponseDTO> createLike(LikeRequestDTO likeRequestDTO);
    Mono<LikeResponseDTO> countLikeByTargetIdandTargetTypeAndUserId(String targetId, String targetType);
    Mono<LikeResponseDTO> countDisLikeByTargetIdandTargetTypeAndUserId(String targetId, String targetType);
    Mono<LikeResponseDTO> toggleLike(String targetId, String targetType, Boolean isLike);

}
