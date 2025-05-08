package com.movie.movie.company.entity;

import com.movie.movie.movie.entity.MovieCompany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "COMPANY")
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id
    @Column(name = "COMPANY_CD")
    private String companyCd;

    @Column(name = "COMPANY_NM", nullable = false)
    private String companyNm;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MovieCompany> movieCompanies;
}
