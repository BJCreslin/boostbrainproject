package ru.bjcreslin.domain.jsonobjects;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class JSONWrapperObject {
    @JsonAlias("Number")
    private Long number;
    @JsonAlias("global_id")
    private float global_id;
    @JsonAlias("Cells")
    JSONTrenerObject cells;

}

