package ru.bjcreslin.domain.jsonobjects;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@Data
public class Sport {
    @JsonAlias(value = "SeniorityPeriod")
    private String SeniorityPeriod;
    @JsonAlias(value = "SportName")
    private String sportName;
}
