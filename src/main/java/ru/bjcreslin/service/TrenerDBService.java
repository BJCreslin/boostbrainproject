package ru.bjcreslin.service;

import lombok.extern.java.Log;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.bjcreslin.domain.Trener;
import ru.bjcreslin.domain.jsonobjects.JSONWrapperObject;
import ru.bjcreslin.domain.jsonobjects.Sport;
import ru.bjcreslin.repository.SportRepository;
import ru.bjcreslin.repository.TrenerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Log
public class TrenerDBService {
    private TrenerRepository trenerRepository;
    private ObjectConversionService objectConversionService;
    private SportRepository sportRepository;

    public TrenerDBService(TrenerRepository trenerRepository, ObjectConversionService objectConversionService, SportRepository sportRepository) {
        this.trenerRepository = trenerRepository;
        this.objectConversionService = objectConversionService;
        this.sportRepository = sportRepository;
    }

    /**
     * Сохраняет данные в базу
     *
     * @param wrapperObjectList List с данными mosdata
     */
    public void saveJSONWrapperObjectListToTrenersDBase(List<JSONWrapperObject> wrapperObjectList) {
        List<Trener> trenerList = objectConversionService.listJSONWrapperToTrenerList(wrapperObjectList);
        List<Sport> allSportList = new ArrayList<>();

        for (Trener trener : trenerList) {
            allSportList.addAll(trener.getSport());
        }
        sportRepository.saveAll(allSportList);
        trenerRepository.saveAll(trenerList);

    }


    public List<Trener> findAll(Pageable pageable) {
        return trenerRepository.findAll(pageable).getContent();
    }

    public List<Trener> findAll() {
        return trenerRepository.findAll();
    }


}