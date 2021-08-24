package com.mitchmele.soundboard.mongo;

import com.mitchmele.soundboard.show.ShowRepository;
import com.mitchmele.soundboard.show.model.ShowDO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShowLoaderTest {

    @Mock
    private LoaderHelper helper;

    @Mock
    private ShowRepository showRepository;

    @InjectMocks
    private ShowLoader loader;

    @Test
    void onEvent_callsHelper_SavesToRepo() {

        ContextRefreshedEvent event = mock(ContextRefreshedEvent.class);

        ShowDO show1 = ShowDO.builder()
                .bandName("Phish")
                .url("www.url1.com")
                .build();

        ShowDO show2 = ShowDO.builder()
                .bandName("Phish")
                .url("www.url2.com")
                .build();

        List<ShowDO> shows = asList(show1, show2);

        when(helper.buildShowList()).thenReturn(shows);

        loader.onApplicationEvent(event);

        verify(helper).buildShowList();
        verify(showRepository).saveAll(shows);
    }
}