package com.mitchmele.soundboard.util;

import com.mitchmele.soundboard.show.model.ShowDO;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 enum class with one member is effectively a singleton
 This approach is functionally equivalent to the public field approach,
 except that it is more concise, provides the serialization machinery for free, and provides an ironclad guarantee against multiple instantiation, even in the face of sophisticated serialization

 The basic idea behind Java's enum types is simple: they are classes that export one instance for each enumeration constant via a public static final field.
  Enum types are effectively final, by virtue of having no accessible constructors.
   Because clients can neither create instances of an enum type nor extend it,
    there can be no instances but the declared enum constants.
     In other words, enum types are instance-controlled.
      They are a generalization of singletons (Item 3),
       which are essentially single-element enums

       Enums provide compile-time type safety


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

/*
* It is easy to write a rich enum type such as Planet.
*  To associate data with enum constants,
*  declare instance fields and write a constructor that takes the data and stores it in the fields.
*  Enums are by their nature immutable, so all fields should be final.
*  They can be public, but it is better to make them private and provide public accessors
*/
