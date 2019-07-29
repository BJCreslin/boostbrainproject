package ru.bjcreslin.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "treners")
@Builder
@Data
@AllArgsConstructor
public class Trener extends People {


    @Column(name = "name")
    private String name;

    @Column(name = "firstName")
    private String firstName;


    public Trener() {
    }
}

