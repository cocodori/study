package xunit;

import java.util.Objects;

public class Assert {
    public static void assertEquals(Object expected, Object actual) {
        if (!expected.equals(actual))
            throw new AssertionError("expected <" + expected +"> but was <" + actual + ">");
    }
}
