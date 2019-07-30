package ru.bjcreslin.domain.jsonobjects;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class DataSetsVersion {
    @JsonAlias("VersionNumber")
    private String versionNumber;

    @JsonAlias("ReleaseNumber")
    private String releaseNumber;
}
