package com.mitchmele.soundboard.video.model.service;

import com.mitchmele.soundboard.video.YouTubeService;
import com.mitchmele.soundboard.video.model.YouTubeItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class YouTubeServiceTest {

    @InjectMocks
    private YouTubeService service;

    @Test
    void fetchVideosByQuery_createsYoutubeQuery_callsService() {

        YouTubeItem video1 = YouTubeItem.builder()
                .videoId("1")
                .description("phish show")
                .thumbnailUrl("www.url.com")
                .build();

        List<YouTubeItem> actual = service.fetchVideosByQuery("phish");

        assertThat(actual).usingRecursiveComparison().isEqualTo(singletonList(video1));
    }
}