package com.devsuperior.movieflix.projections;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface MovieProjection {

    Long getId();
    String getTitle();
    String getSubTitle();
    @JsonProperty("year")
    String getMovieYear();
    String getImgUrl();
}
