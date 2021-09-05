package com.mitchmele.soundboard.video;

import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.mitchmele.soundboard.video.model.YouTubeItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class YouTubeService {

    private final YouTubeSearchListClient searchListClient;

    public List<YouTubeItem> fetchVideosByQuery(String queryTerm) {

        List<YouTubeItem> videos = new ArrayList<>();

        try {

            SearchListResponse searchListResponse = searchListClient.getSearchListResponse(queryTerm);

            System.out.println(searchListResponse);

            List<SearchResult> searchResults = searchListResponse.getItems();

            if (!isEmpty(searchResults)) {
                for (SearchResult result : searchResults) {
                    YouTubeItem video = YouTubeItem.builder()
                            .title(result.getSnippet().getTitle())
                            .thumbnailUrl(result.getSnippet().getThumbnails().getDefault().getUrl())
                            .description(result.getSnippet().getDescription())
                            .url(buildVideoUrl(result.getId().getVideoId()))
                            .build();

                    videos.add(video);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return videos;
    }

    private String buildVideoUrl(String videoId) {
        String baseUrl = "https://www.youtube.com/watch?v=";
        return baseUrl + videoId;
    }
}
