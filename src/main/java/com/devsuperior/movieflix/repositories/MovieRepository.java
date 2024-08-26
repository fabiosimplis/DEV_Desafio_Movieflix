package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(nativeQuery = true, value = """
            SELECT m.id, m.title, m.sub_title AS subTitle, m.movie_year AS movieYear, m.img_url AS imgUrl FROM tb_movie AS m WHERE (:genre IS NULL OR m.genre_id IN :genre) ORDER BY m.title

            """,
            countQuery = """
                    SELECT COUNT(*) FROM
                    (SELECT m.id, m.title, m.sub_title AS subTitle, m.movie_year AS movieYear, m.img_url AS imgUrl FROM tb_movie AS m WHERE (:genre IS NULL OR m.genre_id IN :genre) ORDER BY m.title)
                    """)
    Page<MovieProjection> searchMovieByGenre(List<Long> genre, Pageable pageable);


}
