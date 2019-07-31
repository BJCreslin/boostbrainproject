package ru.bjcreslin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bjcreslin.domain.jsonobjects.Sport;

public interface SportRepository extends JpaRepository<Sport, Long> {

}
