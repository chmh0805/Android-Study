package com.example.instaex01.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Integer id;
    private String username;
    private String realName;
    private String userBio;
    private Integer postNum;
    private Integer followerNum;
    private Integer followingNum;
}
