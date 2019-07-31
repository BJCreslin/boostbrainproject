package ru.bjcreslin.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bjcreslin.domain.jsonobjects.Sport;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    List<Sport> sport = new ArrayList<>();

    ArrayList<String> education = new ArrayList<>();

}

