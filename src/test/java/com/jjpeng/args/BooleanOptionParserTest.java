package com.jjpeng.args;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

public class BooleanOptionParserTest {

    //sad path: -l t
    @Test
    public void should_not_accept_extra_argument_for_boolean_option() {
        TooManyArgumentException e = Assertions.assertThrows(TooManyArgumentException.class, () -> {
            new BooleanOptionParser().parse(Arrays.asList("-l", "t"), option("l"));
        });

        Assertions.assertEquals("l", e.getOption());
    }

    //sad path: -l t f
    @Test
    public void should_not_accept_extra_arguments_for_boolean_option() {
        TooManyArgumentException e = Assertions.assertThrows(TooManyArgumentException.class, () -> {
            new BooleanOptionParser().parse(Arrays.asList("-l", "t", "f"), option("l"));
        });

        Assertions.assertEquals("l", e.getOption());
    }

    //default
    @Test
    public void should_set_default_value_to_false_if_not_present() {
        Assertions.assertFalse(new BooleanOptionParser().parse(List.of(), option("l")));
    }

    static Option option(String value) {
        return new Option() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return Option.class;
            }

            @Override
            public String value() {
                return value;
            }
        };
    }
}
