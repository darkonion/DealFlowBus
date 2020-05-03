package com.dealflowbus.databasemainreader.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@DynamicUpdate
@JsonIgnoreProperties("hibernateLazyInitializer")
@Entity
@Table(name = "main")
public class Lead {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(LeadViews.List.class)
    private int id;

    @NotNull
    @JsonView(LeadViews.List.class)
    private String projectName;

    @NotNull
    @JsonView(LeadViews.List.class)
    private String projectOwner;

    @JsonView(LeadViews.Base.class)
    private String field;

    @NotNull
    @JsonView(LeadViews.List.class)
    private String email;

    @JsonView(LeadViews.List.class)
    private String extraAddress;

    @JsonView(LeadViews.Base.class)
    private boolean inProgress;

    @JsonView(LeadViews.Base.class)
    private boolean rejected;

    @JsonView(LeadViews.Base.class)
    private boolean inPortfolio;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "descr_id")
    private Detail detail;

    @JsonView(LeadViews.Base.class)
    private LocalDate dateArrival;

    @JsonView(LeadViews.Base.class)
    private LocalDate lastTouched;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lead_id")
    private List<Note> notes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lead_id2")
    private List<DBFile> files = new ArrayList<>();

    public Lead() {

    }

    public Lead(String projectName, String projectOwner,
            String field, String email, String extraAddress) {

        this.projectName = projectName;
        this.projectOwner = projectOwner;
        this.inProgress = false;
        this.rejected = false;
        this.inPortfolio = false;
        this.field = field;
        this.email = email;
        this.extraAddress = extraAddress;
    }

}
