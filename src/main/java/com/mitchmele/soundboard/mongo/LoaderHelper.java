package com.mitchmele.soundboard.mongo;

import com.mitchmele.soundboard.show.model.ShowDO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

@Component
public class LoaderHelper {

    private static final List<String> bandNames = asList("Phish", "Disco Biscuits", "Widespread Panic", "STS9");

    public List<ShowDO> buildShowList() {
        List<ShowDO> shows = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            Random rand = new Random();
            ShowDO show = ShowDO.builder()
                    .bandName(bandNames.get(rand.nextInt(bandNames.size())))
                    .url(buildUrl(i))
                    .build();
            shows.add(show);
        }

        return shows;
    }

    private String buildUrl(int number) {
        return "www.url" + number + ".com";
    }
}
