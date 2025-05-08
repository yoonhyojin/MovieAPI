package com.movie.movie.people.entity;

import com.movie.movie.movie.entity.MoviePeople;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PEOPLE")
@Getter
@Setter
@NoArgsConstructor
public class People {
    @Id
    @Column(name = "PEOPLE_CD")
    private String peopleCd;

    @Column(name = "PEOPLE_NM", nullable = false)
    private String peopleNm;

    @Column(name = "BIRTHDAY")
    private Date birthday;

    @OneToMany(mappedBy = "people", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MoviePeople> moviePeople;
}