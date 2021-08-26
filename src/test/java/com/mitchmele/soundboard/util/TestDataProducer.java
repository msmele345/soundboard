package com.mitchmele.soundboard.util;

import com.mitchmele.soundboard.show.model.ShowDO;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 enum class with one member is effectively a singleton
 This approach is functionally equivalent to the public field approach,
 except that it is more concise, provides the serialization machinery for free, and provides an ironclad guarantee against multiple instantiation, even in the face of sophisticated serialization
 */
public enum TestDataProducer {
    INSTANCE;

    private final Random rand = new Random();

    public ShowDO produceShow(String bandName) {
        return ShowDO.builder()
                .bandName(bandName)
                .url(buildUrl(rand.nextInt(100), bandName))
                .build();
    }

    public List<ShowDO> produceShows(int numberOfShows, String bandName) {

        List<ShowDO> shows = new ArrayList<>();

        Random rand = new Random();

        for (int i = 0; i < numberOfShows; i++) {
            ShowDO show = ShowDO.builder()
                    .bandName(bandName)
                    .url(buildUrl(rand.nextInt(100), bandName))
                    .build();
            shows.add(show);
        }

        return shows;
    }

    private static String buildUrl(int number, String bandName) {
        return "www." + bandName + number + ".com";
    }
}
