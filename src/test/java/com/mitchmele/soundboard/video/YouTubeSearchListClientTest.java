package com.mitchmele.soundboard.video;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class YouTubeSearchListClientTest {

    @Mock
    private YoutubeProperties properties;

    @InjectMocks
    private YouTubeSearchListClient client;

    @Test
    @Disabled
    void getSearchListResponse() {

        when(properties.getApiKey()).thenReturn("key");
        when(properties.getMaxResults()).thenReturn(5L);
//        YouTube.Search search = mock(YouTube.Search.class);
//        when(search.list(anyString())).thenReturn((YouTube.Search.List) asList("id"));
        verify(properties).getMaxResults();
        verify(properties).getApiKey();
    }
}