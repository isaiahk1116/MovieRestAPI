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

    public List<Movie> getMovies() {
       return movieRepository.findAll();
    }

    public Movie getMovie(long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public void deleteMovie(long id) { movieRepository.deleteById(id); }

    public boolean movieExists(long id) { return movieRepository.existsById(id); }

    public Movie addMovie(Movie movie) {
        if(StringUtils.hasText(movie.getTitle())) {
            return movieRepository.save(movie);
        } else {
            return null;
        }
    }

    @PostConstruct
    public void addData() {
        Movie test = new Movie();
        test.setTitle("Test");
        test.setDuration(100l);
        test.setDirector("test director");
        movieRepository.save(test);
    }

}
