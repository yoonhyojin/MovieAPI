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
public class MovieCompanyKey implements Serializable {

    @Column(name = "COMPANY_CD")
    private String companyCd;

    @Column(name = "MOVIE_CD")
    private String movieCd;

}
