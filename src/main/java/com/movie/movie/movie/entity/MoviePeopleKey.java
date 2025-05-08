package com.movie.movie.movie.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoviePeopleKey implements Serializable {

    @Column(name = "PEOPLE_CD")
    private String peopleCd;

    @Column(name = "MOVIE_CD")
    private String movieCd;

    @Column(name = "ROLE")
    private String role;

}
