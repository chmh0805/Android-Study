package com.example.kakao_ex1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat {
    private Integer id;
    private Integer imgSrc;
    private String username;
    private String content;
    private String time;
}
