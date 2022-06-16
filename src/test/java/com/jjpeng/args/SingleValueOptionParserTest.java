package com.jjpeng.args;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SingleValueOptionParserTest {

    //sad path: -p 8080 8081
    @Test
    public void should_not_accept_extra_argument_for_single_value_option() {
        TooManyArgumentException e = Assertions.assertThrows(TooManyArgumentException.class, () -> {
            new SingleValueOptionParser<>(Integer::parseInt).parse(List.of("-p", "8080", "8081"), BooleanOptionParserTest.option("p"));
        });

        Assertions.assertEquals("p", e.getOption());
    }

}
