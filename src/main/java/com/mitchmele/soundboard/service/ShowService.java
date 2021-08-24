package com.mitchmele.soundboard.service;

import com.mitchmele.soundboard.show.model.ShowDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowService {

    public List<ShowDO> getAllShowsByBand(String bandName) {
        return null;
    }
}
