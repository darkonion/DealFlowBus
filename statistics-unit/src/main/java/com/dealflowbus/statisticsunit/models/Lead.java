package com.dealflowbus.statisticsunit.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class Lead implements Serializable {

    private static final long serialVersionUID = 2908116142472869016L;

    //only crucial for statistics parameters
    private boolean inProgress;
    private boolean rejected;
    private String field;
    private LocalDate dateArrival;
    private LocalDate lastTouched;
    private boolean inPortfolio;

    public Lead() {
    }

    public Lead(boolean inProgress, boolean rejected, String field,
            LocalDate dateArrival, LocalDate lastTouched, boolean inPortfolio) {

        this.inProgress = inProgress;
        this.rejected = rejected;
        this.field = field;
        this.dateArrival = dateArrival;
        this.lastTouched = lastTouched;
        this.inPortfolio = inPortfolio;
    }


}
