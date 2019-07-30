package ru.bjcreslin.domain.jsonobjects;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Класс версии API сервера
 */
@Component
@Data
public class APIVersion {
    @JsonAlias("Version")
    String version;
}
