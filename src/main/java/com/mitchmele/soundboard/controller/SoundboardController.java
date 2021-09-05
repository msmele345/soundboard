package com.mitchmele.soundboard.controller;

import com.mitchmele.soundboard.service.ShowService;
import com.mitchmele.soundboard.show.model.ShowDO;
import com.mitchmele.soundboard.video.YouTubeService;
import com.mitchmele.soundboard.video.model.SearchContentRequest;
import com.mitchmele.soundboard.video.model.YouTubeItem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SoundboardController {

    private final ShowService showService;
    private final YouTubeService youtubeService;

    @GetMapping("/shows")
    public List<ShowDO> getShows(@RequestParam String band) {
        return showService.getAllShowsByBandName(band);
    }

    @CrossOrigin
    @PostMapping("/videos")
    public List<YouTubeItem> getVideos(@RequestBody SearchContentRequest request) {
        return youtubeService.fetchVideosByQuery(request.getQueryTerm());
    }
}
