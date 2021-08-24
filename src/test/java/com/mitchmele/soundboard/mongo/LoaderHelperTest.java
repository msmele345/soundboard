package com.mitchmele.soundboard.mongo;

import com.mitchmele.soundboard.show.model.ShowDO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class LoaderHelperTest {

    @InjectMocks
    private LoaderHelper helper;

    private static final List<String> permittedBandNames = asList("Phish", "Disco Biscuits", "Widespread Panic", "STS9");

    @Test
    void loadShows_createsListOfShows() {

        List<ShowDO> actual = helper.buildShowList();

        assertThat(actual).hasSize(10);

        actual.forEach(show -> {
            assertThat(permittedBandNames).contains(show.getBandName());
        });
    }
}