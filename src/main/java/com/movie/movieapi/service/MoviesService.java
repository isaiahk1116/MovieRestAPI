package com.movie.movieapi.service;

import com.movie.movieapi.entity.Movie;
import com.movie.movieapi.exception.ResourceNotFoundException;
import com.movie.movieapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
    public ResponseEntity<Movie> getMovie(long id) throws ResourceNotFoundException {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found: " + id));
        return ResponseEntity.ok().body(movie);
    }

    // Accepts an id and deletes the corresponding movie
    public String deleteMovie(long id) throws ResourceNotFoundException {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found: " + id));
        movieRepository.deleteById(id);
        return "deleted";
    }

    // Accepts an id and a movie object to update the movie corresponding to the id
    public ResponseEntity<Movie> updateMovie(long id, Movie newMovie) throws ResourceNotFoundException {
        Movie updatedMovie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found:" + id));
        updatedMovie.setId(id);
        updatedMovie.setTitle(newMovie.getTitle());
        updatedMovie.setGenre(newMovie.getGenre());
        updatedMovie.setYear(newMovie.getYear());
        movieRepository.save(updatedMovie);
        return ResponseEntity.ok(updatedMovie);
    }

    // Accepts a movie object and saves it to the database
    public ResponseEntity<Movie> addMovie(Movie movie) {
        movieRepository.save(movie);
        return ResponseEntity.ok(movie);
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
