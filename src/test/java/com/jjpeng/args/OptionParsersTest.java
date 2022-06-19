package com.jjpeng.args;

import com.jjpeng.args.exceptions.InsufficientArgumentException;
import com.jjpeng.args.exceptions.TooManyArgumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

public class OptionParsersTest {

    @Nested
    class UnaryOptionParser {

        //sad path: -p 8080 8081
        @Test
        public void should_not_accept_extra_argument_for_single_value_option() {
            TooManyArgumentException e = Assertions.assertThrows(TooManyArgumentException.class, () -> {
                OptionParsers.unary(Integer::parseInt, (Integer) 0).parse(List.of("-p", "8080", "8081"), BooleanOptionParser.option("p"));
            });

            Assertions.assertEquals("p", e.getOption());
        }

        //sad path: -p
        @Test
        public void should_not_accept_insufficient_argument_for_single_value_option() {
            InsufficientArgumentException e = Assertions.assertThrows(InsufficientArgumentException.class, () -> {
                OptionParsers.unary(Integer::parseInt, (Integer) 0).parse(List.of("-p"), BooleanOptionParser.option("p"));
            });

            Assertions.assertEquals("p", e.getOption());
        }

        //sad path: -p -l
        @Test
        public void should_not_accept_insufficient_argument_with_other_argument_for_single_value_option() {
            InsufficientArgumentException e = Assertions.assertThrows(InsufficientArgumentException.class, () -> {
                OptionParsers.unary(Integer::parseInt, (Integer) 0).parse(List.of("-p", "-l"), BooleanOptionParser.option("p"));
            });

            Assertions.assertEquals("p", e.getOption());
        }

        @Test
        public void should_set_default_value_to_0_for_int_option() {
            Assertions.assertEquals(0, OptionParsers.unary(Integer::parseInt, (Integer) 0).parse(List.of(), BooleanOptionParser.option("p")));
        }

        //happy path
        @Test
        public void should_parse_value_if_flag_present() {
            Assertions.assertEquals(8080, OptionParsers.unary(Integer::parseInt, (Integer) 0).parse(List.of("-p", "8080"), BooleanOptionParser.option("p")));
        }
    }

    @Nested
    class BooleanOptionParser {
    
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
}
