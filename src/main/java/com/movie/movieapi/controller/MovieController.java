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

    @GetMapping
    public List<Movie> getMovies() {
        return moviesService.getMovies();
    }

    @GetMapping(value ="{id}")
    public Movie getMovieById(@PathVariable long id) { return moviesService.getMovie(id); }

    @PostMapping
    public String saveMovie(@RequestBody Movie newMovie) {
        Movie movie = moviesService.addMovie(newMovie);
        return movie != null ? "Success" : "Failure";
    }

    @DeleteMapping(value = "{id}")
    public String deleteMovie(@PathVariable long id) {
        if (moviesService.movieExists(id)) {
            moviesService.deleteMovie(id);
            return "Success";
        } else
            return "Failure";
    }

    @PutMapping(value= "{id}")
    public String updateMovie(@PathVariable long id, @RequestBody Movie newMovie) {
        if (moviesService.movieExists(id)) {
            moviesService.updateMovie(id, newMovie);
            return "Success";
        } else
            return "Failure";
    }

}
