package com.mitchmele.soundboard.video;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.mitchmele.soundboard.video.model.YouTubeItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class YouTubeService {

    private final YoutubeProperties youtubeProperties;

    public List<YouTubeItem> fetchVideosByQuery(String queryTerm) {

        List<YouTubeItem> videos = new ArrayList<>();

        try {
            YouTube youTubeClient = getYouTube();

            YouTube.Search.List request = youTubeClient.search()
                    .list("id,snippet");

            request.setKey(youtubeProperties.getApiKey());

            //set the search term
            request.setQ(queryTerm);

            //we only want video results
            request.setType("video");

            //set the fields that we're going to use
            request.setFields("items(id/kind,id/videoId,snippet/title,snippet/description,snippet/publishedAt,snippet/thumbnails/default/url)");

            SearchListResponse listResponse = request.execute();

            System.out.println(listResponse);

            List<SearchResult> searchResults = listResponse.getItems();

            if(!CollectionUtils.isEmpty(searchResults)) {
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
        StringBuilder builder = new StringBuilder();
        builder.append("https://www.youtube.com/watch?v=");
        builder.append(videoId);

        return builder.toString();
    }

    private YouTube getYouTube() {
//        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(),
                (request) -> {}
        ).setApplicationName("Soundstage").build();
    }
}
