package com.jjpeng.args;

import com.jjpeng.args.exceptions.InsufficientArgumentException;
import com.jjpeng.args.exceptions.TooManyArgumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SingleValueOptionParserTest {

    //sad path: -p 8080 8081
    @Test
    public void should_not_accept_extra_argument_for_single_value_option() {
        TooManyArgumentException e = Assertions.assertThrows(TooManyArgumentException.class, () -> {
            SingleValueOptionParser.unary(Integer::parseInt, (Integer) 0).parse(List.of("-p", "8080", "8081"), BooleanOptionParserTest.option("p"));
        });

        Assertions.assertEquals("p", e.getOption());
    }

    //sad path: -p
    @Test
    public void should_not_accept_insufficient_argument_for_single_value_option() {
        InsufficientArgumentException e = Assertions.assertThrows(InsufficientArgumentException.class, () -> {
            SingleValueOptionParser.unary(Integer::parseInt, (Integer) 0).parse(List.of("-p"), BooleanOptionParserTest.option("p"));
        });

        Assertions.assertEquals("p", e.getOption());
    }

    //sad path: -p -l
    @Test
    public void should_not_accept_insufficient_argument_with_other_argument_for_single_value_option() {
        InsufficientArgumentException e = Assertions.assertThrows(InsufficientArgumentException.class, () -> {
            SingleValueOptionParser.unary(Integer::parseInt, (Integer) 0).parse(List.of("-p", "-l"), BooleanOptionParserTest.option("p"));
        });

        Assertions.assertEquals("p", e.getOption());
    }

    @Test
    public void should_set_default_value_to_0_for_int_option() {
        Assertions.assertEquals(0, SingleValueOptionParser.unary(Integer::parseInt, (Integer) 0).parse(List.of(), BooleanOptionParserTest.option("p")));
    }

    //happy path
    @Test
    public void should_parse_value_if_flag_present() {
        Assertions.assertEquals(8080, SingleValueOptionParser.unary(Integer::parseInt, (Integer) 0).parse(List.of("-p", "8080"), BooleanOptionParserTest.option("p")));
    }

}
