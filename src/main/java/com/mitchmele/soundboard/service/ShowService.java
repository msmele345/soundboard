package com.mitchmele.soundboard.service;

import com.mitchmele.soundboard.show.ShowRepository;
import com.mitchmele.soundboard.show.model.ShowDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final ShowRepository showRepository;

    public List<ShowDO> getAllShowsByBandName(String bandName) {
        return showRepository.findAllByBandName(bandName);
    }
}
