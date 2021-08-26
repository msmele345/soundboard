package com.mitchmele.soundboard.service;

import com.mitchmele.soundboard.show.ShowRepository;
import com.mitchmele.soundboard.show.model.ShowDO;
import com.mitchmele.soundboard.util.TestDataProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShowServiceTest {

    @Mock
    ShowRepository repository;

    @InjectMocks
    ShowService showService;

    final TestDataProducer testDataProducer = TestDataProducer.INSTANCE;

    @Test
    void getAllShowsByBandName() {

        List<ShowDO> expected =
                testDataProducer.produceShows(3, "phish");

        when(repository.findAllByBandName(anyString())).thenReturn(expected);

        List<ShowDO> actual = showService.getAllShowsByBandName("phish");

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verify(repository).findAllByBandName("phish");
    }
}