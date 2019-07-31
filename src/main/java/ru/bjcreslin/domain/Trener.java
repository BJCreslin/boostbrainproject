package ru.bjcreslin.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "treners")
@Data
@NoArgsConstructor
public class Trener {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    private String middleName;

    private String gender;

    ArrayList<String> academicDegree = new ArrayList<>();

    ArrayList<String> sport = new ArrayList<>();

    ArrayList<String> education = new ArrayList<>();

}

