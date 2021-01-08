package com.movie.movieapi.controller;

import com.movie.movieapi.entity.Movie;
import com.movie.movieapi.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class MovieController {

    @Autowired
    private MoviesService moviesService;

    @GetMapping
    public List<Movie> getMovies() {
        return moviesService.getMovies();
    }

    @GetMapping(value ="movie")
    public Movie getMovieById(@RequestParam long id) {
       return moviesService.getMovie(id);
    }

    @PostMapping
    public String saveMovie(@RequestBody Movie newMovie) {
        Movie movie = moviesService.addMovie(newMovie);
        return movie != null ? "Success" : "Failure";
    }

    @DeleteMapping(value= "movie")
    public String deleteMovie(@RequestParam long id) {
        if (moviesService.movieExists(id)) {
            moviesService.deleteMovie(id);
            return "Success";
        } else
            return "Failure";
    }

    @PutMapping(value= "movie")
    public String updateMovie(@RequestParam long id, @RequestBody Movie newMovie) {
        if (moviesService.movieExists(id)) {
            moviesService.updateMovie(id, newMovie);
            return "Success";
        } else
            return "Failure";
    }

}
