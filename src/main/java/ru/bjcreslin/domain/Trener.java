package ru.bjcreslin.domain;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "treners")
@Builder
public class Trener {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name="name")
    private String name;

    @Column(name = "firstName")
    private String firstName;


}

