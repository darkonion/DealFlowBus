package com.dealflowbus.databasemainreader.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "content"})
@Entity
@Table(name = "description")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_description")
    private int descId;

    @Lob
    private String description;

    @JsonIgnore
    @OneToOne(mappedBy = "detail", fetch = FetchType.LAZY)
    private Lead lead;

    public Detail() {
    }

    public Detail(String description) {
        this.description = description;

    }
}
