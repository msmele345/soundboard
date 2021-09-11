package com.mitchmele.soundboard.video.model.service;

import com.google.api.services.youtube.model.*;
import com.mitchmele.soundboard.video.YouTubeSearchListService;
import com.mitchmele.soundboard.video.YouTubeService;
import com.mitchmele.soundboard.video.model.YouTubeItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class YouTubeServiceTest {

    @Mock
    private YouTubeSearchListService searchListClient;

    @InjectMocks
    private YouTubeService youTubeService;

    @Test
    void fetchVideosByQuery_createsYoutubeQuery_callsService() {

        YouTubeItem video1 = YouTubeItem.builder()
                .description("at camden")
                .title("phish show")
                .url("https://www.youtube.com/watch?v=1")
                .thumbnailUrl("www.url.com")
                .build();

        SearchListResponse searchListResponse = mock(SearchListResponse.class);
        when(searchListClient.getSearchListResponse(anyString())).thenReturn(searchListResponse);

        SearchResult result1 = mock(SearchResult.class);
        when(searchListResponse.getItems()).thenReturn(singletonList(result1));
        when(result1.getId()).thenReturn(new ResourceId().setVideoId("1"));

        SearchResultSnippet snippet = new SearchResultSnippet();
        snippet.setTitle("phish show");
        snippet.setDescription("at camden");

        ThumbnailDetails thumbnails = new ThumbnailDetails();
        thumbnails.setDefault(new Thumbnail().setUrl("www.url.com"));
        snippet.setThumbnails(thumbnails);
        when(result1.getSnippet()).thenReturn(snippet);

        List<YouTubeItem> actual = youTubeService.fetchVideosByQuery("phish");

        assertThat(actual).usingRecursiveComparison().isEqualTo(singletonList(video1));
    }
}