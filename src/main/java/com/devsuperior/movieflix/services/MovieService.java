package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieProjection;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public MovieDetailsDTO findById(Long id) {
        Optional<Movie> result = movieRepository.findById(id);
        Movie movie = result.orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        return new MovieDetailsDTO(movie);
    }

    @Transactional(readOnly = true)
    public  Page<MovieProjection> searchMovieByGenre(String genre, Pageable pageable) {

        List<Long> genreList = Arrays.asList();
        if (!genre.equals("0")){
            String[] split = genre.split(",");
            genreList = Arrays.stream(split).map(s -> Long.valueOf(s)).toList();
        }

        Page<MovieProjection> result = movieRepository.searchMovieByGenre(genreList, pageable);


        return result;
    }


}
