package com.mitchmele.soundboard.util;

import com.mitchmele.soundboard.show.model.ShowDO;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static java.util.Arrays.asList;

public class ShowFactory {

    private static final ShowFactory INSTANCE = new ShowFactory();

    private static final List<String> bandNames = asList(
            "Phish",
            "Disco Biscuits",
            "Widespread Panic",
            "STS9"
    );

    private ShowFactory() { }

    public static ShowFactory getInstance() {
        return INSTANCE;
    }

    public List<ShowDO> produceShows(int numberOfShows) {
        List<ShowDO> shows = new ArrayList<>();

        for(int i = 0; i < numberOfShows; i++) {
            Random rand = new Random();
            String bandName = getBandName(rand);
            ShowDO show = ShowDO.builder()
                    .bandName(bandName)
                    .url(buildUrl(rand.nextInt(100), bandName))
                    .build();
            shows.add(show);
        }

        return shows;
    }

    private String getBandName(Random rand) {
        return bandNames.get(rand.nextInt(bandNames.size()));
    }

    private String buildUrl(int number, String bandName) {
        return "www." + bandName + number + ".com";
    }
}
