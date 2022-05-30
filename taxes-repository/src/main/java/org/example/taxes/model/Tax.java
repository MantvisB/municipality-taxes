package org.example.taxes.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "taxes")
public class Tax {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double rate;

    @OneToOne
    @JoinColumn(name = "municipality_id")
    private Municipality municipality;

    @Column(name = "date_from")
    private LocalDate dateFrom;


    @Column(name = "date_to")
    private LocalDate dateTo;

    public Long getId() {
        return id;
    }

    public Tax setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getRate() {
        return rate;
    }

    public Tax setRate(Double rate) {
        this.rate = rate;
        return this;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public Tax setMunicipality(Municipality municipality) {
        this.municipality = municipality;
        return this;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public Tax setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public Tax setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }
}
