package com.movie.movieapi.controller;

import com.movie.movieapi.entity.Movie;
import com.movie.movieapi.exception.ResourceNotFoundException;
import com.movie.movieapi.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MoviesService moviesService;

    // HTTP GET Request will return a list of movies
    @GetMapping
    public List<Movie> getMovies() {
        return moviesService.getMovies();
    }

    // HTTP GET Request with an id in the path will return the corresponding movie
    @GetMapping(value = "{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable long id) throws ResourceNotFoundException {
        return moviesService.getMovie(id);
    }

    @PostMapping
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie newMovie) {
        return moviesService.addMovie(newMovie);
    }

    // HTTP PUT Request with an id in the path accepts a movie object in the request
    // body and updates movie corresponding to the provided id. Returns a string
    @PutMapping(value = "{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable long id, @RequestBody Movie newMovie) throws ResourceNotFoundException {
        return moviesService.updateMovie(id, newMovie);
    }

    // HTTP DELETE Request with an id in the path will delete the corresponding movie from the database. Returns a string
    @DeleteMapping(value = "{id}")
    public String deleteMovie(@PathVariable long id) throws ResourceNotFoundException {
        return moviesService.deleteMovie(id);
    }
}
