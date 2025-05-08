package com.movie.movie.movie.entity;

import com.movie.movie.people.entity.People;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MOVIE_PEOPLE")
@Getter
@Setter
@NoArgsConstructor
public class MoviePeople {

    @EmbeddedId
    private MoviePeopleKey id;

    @MapsId("peopleCd")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PEOPLE_CD", insertable = false, updatable = false)
    private People people;

    @MapsId("movieCd")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_CD", insertable = false, updatable = false)
    private Movie movie;
}