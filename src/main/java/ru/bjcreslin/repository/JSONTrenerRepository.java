package ru.bjcreslin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bjcreslin.domain.jsonobjects.JSONTrenerObject;

public interface JSONTrenerRepository extends JpaRepository<JSONTrenerObject, Long> {
}
