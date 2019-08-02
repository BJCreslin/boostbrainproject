package ru.bjcreslin.domain.jsonobjects;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;

@Entity
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@Data
@Table(name = "sport")
public class Sport {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @JsonAlias(value = "SeniorityPeriod")
    private String SeniorityPeriod;

    @Override
    public String toString() {
        return "Sport{" +
                "sportName='" + sportName + '\'' +
                '}';
    }

    @JsonAlias(value = "SportName")
    private String sportName;
}
