package com.mitchmele.soundboard.util;

public enum ShowTypes {
    FESTIVAL {String apply(String venue, String location) {return venue + "FESTIVAL" + " is a sick venue";}},
    THEATRE{String apply(String venue, String location) {return "The" + venue + " is a sick venue";}},
    CLUB {String apply(String venue, String location) {return venue + "FESTIVAL" + " is a sick venue";}},
    ARENA {String apply(String venue, String location) {return venue + "FESTIVAL" + " is a sick venue";}},
    SECRET{String apply(String venue, String location) {return venue + "FESTIVAL" + " is a sick venue";}};

    abstract String apply(String venue, String location);
}


/* are knows as constant-specific method implementations:
// Enum type with constant-specific method implementations
public enum Operation {
    PLUS   { double apply(double x, double y){return x + y;} },
    MINUS  { double apply(double x, double y){return x - y;} },
    TIMES  { double apply(double x, double y){return x * y;} },
    DIVIDE { double apply(double x, double y){return x / y;} };

    abstract double apply(double x, double y);
}
If you add a new constant to the second version of Operation,
*  it is unlikely that you'll forget to provide an apply method,
*  as the method immediately follows each constant declaration. In the unlikely event that you do forget, the compiler will remind you, as abstract methods in an enum type must be overridden with concrete methods in all of its constants.

* */