package com.mitchmele.soundboard.video;

import com.google.api.services.youtube.model.SearchListResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class YouTubeSearchListServiceTest {

    @Mock
    private YouTubeClient youTubeClient;

    @InjectMocks
    private YouTubeSearchListService service;

    @Test
    void getSearchListResponse() {

        SearchListResponse searchListResponse = mock(SearchListResponse.class);
        when(youTubeClient.fetchVideos(anyString())).thenReturn(searchListResponse);

        SearchListResponse actual = service.getSearchListResponse("Phish");

        assertThat(actual).isEqualTo(searchListResponse);
        verify(youTubeClient).fetchVideos("Phish");
    }
}