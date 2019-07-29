package ru.bjcreslin.service;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bjcreslin.repository.TrenerRepository;

@Service
@Log
public class TrenerDBService {
    @Autowired
    TrenerRepository trenerRepository;


}
