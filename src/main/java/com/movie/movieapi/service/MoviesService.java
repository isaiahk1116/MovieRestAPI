package com.movie.movieapi.service;

import com.movie.movieapi.entity.Movie;
import com.movie.movieapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class MoviesService {

    @Autowired
    private MovieRepository movieRepository;

    // Returns list of movies
    public List<Movie> getMovies() {
       return movieRepository.findAll();
    }

    // Accepts an id and returns the corresponding movie
    public Movie getMovie(long id) {
        return movieRepository.findById(id).orElse(null);
    }

    // Accepts an id and deletes the corresponding movie
    public void deleteMovie(long id) { movieRepository.deleteById(id); }

    // Accepts an id and a movie object to update the movie corresponding to the id
    public void updateMovie(long id, Movie newMovie) {
        Movie existingMovie = movieRepository.findById(id).orElse(null);
        existingMovie = newMovie;
        movieRepository.save(existingMovie);
    }

    // Accepts an id and searches for the corresponding movie
    public boolean movieExists(long id) { return movieRepository.existsById(id); }

    // Accepts a movie object and saves it to the database
    public Movie addMovie(Movie movie) {
        if(StringUtils.hasText(movie.getTitle())) {
            return movieRepository.save(movie);
        } else {
            return null;
        }
    }

    // Initializes the database with a default movie
    @PostConstruct
    public void addData() {
        Movie test = new Movie();
        test.setTitle("The Dark Knight");
        test.setYear(2008);
        test.setGenre("Action");
        movieRepository.save(test);
    }

}
