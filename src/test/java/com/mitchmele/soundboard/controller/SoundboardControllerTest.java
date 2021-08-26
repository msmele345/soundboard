package com.mitchmele.soundboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitchmele.soundboard.service.ShowService;
import com.mitchmele.soundboard.show.model.ShowDO;
import com.mitchmele.soundboard.video.YouTubeService;
import com.mitchmele.soundboard.video.model.SearchContentRequest;
import com.mitchmele.soundboard.video.model.YouTubeItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class SoundboardControllerTest {

    @Mock
    private ShowService showService;

    @Mock
    private YouTubeService youtubeService;

    @InjectMocks
    private SoundboardController soundboardController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(soundboardController).build();;
    }

    @Test
    void getAllShowsByBand() throws Exception {

        ShowDO show1 = ShowDO.builder()
                .bandName("Phish")
                .url("www.url1.com")
                .build();

        ShowDO show2 = ShowDO.builder()
                .bandName("Phish")
                .url("www.url2.com")
                .build();

        List<ShowDO> shows = asList(show1, show2);

        when(showService.getAllShowsByBandName(anyString())).thenReturn(shows);

        mockMvc.perform(get("/api/v1/shows?band=phish"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(shows)));

        verify(showService).getAllShowsByBandName("phish");
    }

    @Test
    void getVideos() throws Exception {

        SearchContentRequest request = SearchContentRequest.builder()
                .queryTerm("phish")
                .build();

        YouTubeItem video1 = YouTubeItem.builder()
                .videoId("1")
                .description("phish show")
                .thumbnailUrl("www.url.com")
                .build();

        List<YouTubeItem> videos = singletonList(video1);

        when(youtubeService.fetchVideosByQuery(anyString())).thenReturn(videos);

        mockMvc.perform(post("/api/v1/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(videos)));

        verify(youtubeService).fetchVideosByQuery("phish");
    }
}