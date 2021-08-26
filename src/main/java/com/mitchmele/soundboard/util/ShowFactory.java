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

    private ShowFactory() {
    }

    public static ShowFactory getInstance() {
        return INSTANCE;
    }

    public List<ShowDO> produceShows(int numberOfShows) {

        List<ShowDO> shows = new ArrayList<>();

        Random rand = new Random();

        for (int i = 0; i < numberOfShows; i++) {
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

/*

This is great for an Api. The implementation is not exposed so it can change anytime. The client just calls the static factory method like before


 A second advantage of static factory methods is that,
 unlike constructors, they are not required to create a new object each time they're invoked.

 The ability of static factory methods to return the same object from repeated invocations allows classes to maintain strict control over what instances exist at any time.
 Classes that do this are said to be instance-controlled. There are several reasons to write instance-controlled classes. Instance control allows a class to guarantee that it is a singleton (Item 3)


 they can return an object of any subtype of their return type. This gives you great flexibility in choosing the class of the returned object.

One application of this flexibility is that an API can return objects without making their classes public.
Hiding implementation classes in this fashion leads to a very compact API. This technique lends itself to interface-based frameworks (Item 18),
 where interfaces provide natural return types for static factory methods.
 */