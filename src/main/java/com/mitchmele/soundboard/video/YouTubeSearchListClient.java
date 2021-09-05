package com.mitchmele.soundboard.video;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class YouTubeSearchListClient {

    private final YoutubeProperties youtubeProperties;

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    @Cacheable("videos")
    public SearchListResponse getSearchListResponse(String query)  {

        try {
            YouTube youTubeClient = getYouTube();

            YouTube.Search.List request = youTubeClient.search()
                    .list("id,snippet");

            request.setKey(youtubeProperties.getApiKey());

            request.setQ(query);

            request.setType("video");

            request.setMaxResults(youtubeProperties.getMaxResults());

            request.setFields("items(id/kind,id/videoId,snippet/title,snippet/description,snippet/publishedAt,snippet/thumbnails/default/url)");

            log.info("Calling YouTube API for {} videos", query);

            return request.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private YouTube getYouTube() {
        return new YouTube.Builder(new NetHttpTransport(), JSON_FACTORY, (request) -> { })
                .setApplicationName("Soundstage").build();
    }
}
