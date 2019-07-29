package ru.bjcreslin.service;

import ru.bjcreslin.domain.Trener;

import java.util.List;

public interface WebService {

    /**
     * Выдает количество объектов с сайта
     *
     * @return int- количество
     */
    int getCount();

    /**
     * Получает все данные с сайта
     */
    List<Trener> getAll();
}
