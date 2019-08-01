package ru.bjcreslin.domain.jsonobjects;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class DataSetsVersion {
    @JsonAlias("VersionNumber")
    private String versionNumber;

    @JsonAlias("ReleaseNumber")
    private String releaseNumber;
}
