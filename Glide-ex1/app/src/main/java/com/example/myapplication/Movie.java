package com.example.myapplication;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie implements Serializable {
    private Integer id;
    private String title;
    private Integer pic;
}
