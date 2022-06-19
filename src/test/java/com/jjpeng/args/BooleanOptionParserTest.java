package com.jjpeng.args;

import com.jjpeng.args.exceptions.TooManyArgumentException;
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
            OptionParsers.bool().parse(Arrays.asList("-l", "t"), option("l"));
        });

        Assertions.assertEquals("l", e.getOption());
    }

    //sad path: -l t f
    @Test
    public void should_not_accept_extra_arguments_for_boolean_option() {
        TooManyArgumentException e = Assertions.assertThrows(TooManyArgumentException.class, () -> {
            OptionParsers.bool().parse(Arrays.asList("-l", "t", "f"), option("l"));
        });

        Assertions.assertEquals("l", e.getOption());
    }

    //default
    @Test
    public void should_set_default_value_to_false_if_not_present() {
        Assertions.assertFalse(OptionParsers.bool().parse(List.of(), option("l")));
    }

    //happy path,与ArgsTest中happy path测试等同功能的测试
    @Test
    public void should_set_value_to_true_if_option_present() {
        Assertions.assertTrue(OptionParsers.bool().parse(List.of("-l"), option("l")));
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
