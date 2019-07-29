package ru.bjcreslin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "treners")
public class Trener extends People {
    @Column(name="name")
    private String name;

    @Column(name = "firstName")
    private String firstName;


}

