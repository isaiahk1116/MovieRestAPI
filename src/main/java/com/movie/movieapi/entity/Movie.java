package com.movie.movieapi.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
public class Movie {

    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String genre;
    private long year;
}
