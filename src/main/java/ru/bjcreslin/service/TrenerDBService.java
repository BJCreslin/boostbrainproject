package ru.bjcreslin.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import ru.bjcreslin.domain.Trener;
import ru.bjcreslin.domain.jsonobjects.JSONWrapperObject;
import ru.bjcreslin.repository.TrenerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
public class TrenerDBService {
    private TrenerRepository trenerRepository;

    public TrenerDBService(TrenerRepository trenerRepository) {
        this.trenerRepository = trenerRepository;
    }

    /**
     * todo:Доделать
     * @param wrapperObjectList
     */
    public void saveTrenersToBase(List<JSONWrapperObject> wrapperObjectList) {

        List<Trener> trenerList = wrapperObjectList.stream().map((x) ->
                Trener.builder().name(x.getCells().getName()).firstName(x.getCells().getLastName()).build()).collect(Collectors.toList());
        trenerRepository.saveAll(trenerList);

    }
}