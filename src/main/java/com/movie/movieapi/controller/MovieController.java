package com.movie.movieapi.controller;

import com.movie.movieapi.entity.Movie;
import com.movie.movieapi.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
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
    @GetMapping(value ="{id}")
    public Movie getMovieById(@PathVariable long id) { return moviesService.getMovie(id); }

    // HTTP POST Request accepts object in the request body and adds the object to the database. Returns a string
    @PostMapping
    public String saveMovie(@RequestBody Movie newMovie) {
        Movie movie = moviesService.addMovie(newMovie);
        return movie != null ? "Success" : "Failure";
    }

    // HTTP DELETE Request with an id in the path will delete the corresponding movie from the database. Returns a string
    @DeleteMapping(value = "{id}")
    public String deleteMovie(@PathVariable long id) {
        if (moviesService.movieExists(id)) {
            moviesService.deleteMovie(id);
            return "Success";
        } else
            return "Failure";
    }

    // HTTP PUT Request with an id in the path accepts a movie object in the request
    // body and updates movie corresponding to the provided id. Returns a string
    @PutMapping(value= "{id}")
    public String updateMovie(@PathVariable long id, @RequestBody Movie newMovie) {
        if (moviesService.movieExists(id)) {
            moviesService.updateMovie(id, newMovie);
            return "Success";
        } else
            return "Failure";
    }

}
