package com.mitchmele.soundboard.controller;

import com.mitchmele.soundboard.service.ShowService;
import com.mitchmele.soundboard.show.model.ShowDO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @GetMapping("/shows")
    public List<ShowDO> getShows(@RequestParam String band) {
        return showService.getAllShowsByBand(band);
    }
}
