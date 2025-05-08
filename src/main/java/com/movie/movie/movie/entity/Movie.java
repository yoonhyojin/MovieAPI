package com.movie.movie.movie.entity;

import com.movie.movie.dailyBoxOffice.entity.DailyBoxOffice;
import com.movie.movie.weeklyBoxOffice.entity.WeeklyBoxOffice;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MOVIE")
@Getter
@Setter
@NoArgsConstructor
public class Movie {
    @Id
    @Column(name = "MOVIE_CD")
    private String movieCd;

    @Column(name = "MOVIE_NM", nullable = false)
    private String movieNm;

    @Column(name = "OPEN_DT", nullable = false)
    private LocalDate openDt;

    @Column(name = "SHOW_TIME", nullable = false)
    private Integer showTime;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MoviePeople> moviePeople;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MovieCompany> movieCompanies;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DailyBoxOffice> dailyBoxOffices;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<WeeklyBoxOffice> weeklyBoxOffices;
}
