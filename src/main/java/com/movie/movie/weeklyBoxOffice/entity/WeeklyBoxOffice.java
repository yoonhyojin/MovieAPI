package com.movie.movie.weeklyBoxOffice.entity;

import com.movie.movie.movie.entity.Movie;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.sql.In;

@Entity
@Table(name = "WEEKLY_BOX_OFFICE")
@Getter
@Setter
@NoArgsConstructor
public class WeeklyBoxOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WEEKLYBOXOFFICE_ID")
    private Integer weeklyBoxOffcieId;

    @Column(name = "MOVIE_CD")
    private String movieCd;

    @Column(name = "WEEK_GB")
    private String weekGb;

    @Column(name = "TARGET_DT")
    private LocalDate targetDt;

    @Column(name = "RANK")
    private Integer rank;

    @Column(name = "AUDIENCE")
    private BigInteger audience;

    @Column(name = "SALES")
    private BigDecimal sales;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_CD", insertable = false, updatable = false)
    private Movie movie;


}
