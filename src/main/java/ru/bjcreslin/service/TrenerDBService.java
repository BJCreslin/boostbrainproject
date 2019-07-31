package ru.bjcreslin.service;

import lombok.extern.java.Log;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.bjcreslin.domain.Trener;
import ru.bjcreslin.domain.jsonobjects.JSONWrapperObject;
import ru.bjcreslin.repository.TrenerRepository;

import java.util.List;

@Service
@Log
public class TrenerDBService {
    private TrenerRepository trenerRepository;

    public TrenerDBService(TrenerRepository trenerRepository) {
        this.trenerRepository = trenerRepository;
    }

    /**
     * Сохраняет данные в базу
     *
     * @param wrapperObjectList List с данными mosdata
     */
    public void saveTrenersToBase(List<JSONWrapperObject> wrapperObjectList) {
        List<Trener> trenerList = objectConversionService.listJSONWrapperToTrenerList(wrapperObjectList);
        trenerRepository.saveAll(trenerList);

    }

    public List<Trener> findAll(Pageable pageable) {
        return trenerRepository.findAll(pageable).getContent();
    }


}