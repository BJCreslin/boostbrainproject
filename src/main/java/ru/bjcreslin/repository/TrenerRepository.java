package ru.bjcreslin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bjcreslin.domain.Trener;

public interface TrenerRepository extends JpaRepository<Long, Trener> {
}
