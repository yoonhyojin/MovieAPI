package com.movie.movie.movie.entity;

import com.movie.movie.company.entity.Company;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MOVIE_COMPANY")
@Getter
@Setter
@NoArgsConstructor
public class MovieCompany {

    @EmbeddedId
    private MovieCompanyKey id;

    @MapsId("movieCd")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_CD", insertable = false, updatable = false)
    private Movie movie;

    @MapsId("companyCd")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_CD", insertable = false, updatable = false)
    private Company company;


}
