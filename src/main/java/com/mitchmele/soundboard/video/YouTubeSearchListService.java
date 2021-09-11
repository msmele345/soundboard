package com.mitchmele.soundboard.video;

import com.google.api.services.youtube.model.SearchListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class YouTubeSearchListService {

    private final YouTubeClient youTubeClient;

    @Cacheable("videos")
    public SearchListResponse getSearchListResponse(String query)  {
        return youTubeClient.fetchVideos(query);
    }
}
