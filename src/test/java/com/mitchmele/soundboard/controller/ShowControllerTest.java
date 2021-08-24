package com.mitchmele.soundboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitchmele.soundboard.service.ShowService;
import com.mitchmele.soundboard.show.model.ShowDO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ShowControllerTest {

    @Mock
    private ShowService showService;

    @InjectMocks
    private ShowController showController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(showController).build();;
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

        when(showService.getAllShowsByBand(anyString())).thenReturn(shows);

        mockMvc.perform(get("/api/v1/shows?band=phish"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(shows)));

        verify(showService).getAllShowsByBand("phish");
    }
}