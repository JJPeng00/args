package com.jjpeng.args;

import com.jjpeng.args.exceptions.InsufficientArgumentException;
import com.jjpeng.args.exceptions.TooManyArgumentException;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;

class OptionParsers {

    public static OptionParser<Boolean> bool() {
        return (arguments, option) ->
                values(arguments, option, 0).map(it -> true).orElse(false);
    }

    public static <T> OptionParser<T> unary(Function<String, T> valueParser, T defaultValue) {
        return (arguments, option) -> values(arguments, option, 1)
                        .map(it -> parseValue(it.get(0), valueParser)).orElse(defaultValue);
    }

    private static Optional<List<String>> values(List<String> arguments, Option option, int expectedSize) {
        int index = arguments.indexOf("-" + option.value());
        if (index == -1) return Optional.empty();

        List<String> values = getValues(arguments, index);
        if (values.size() < expectedSize) throw new InsufficientArgumentException(option.value());
        if (values.size() > expectedSize) throw new TooManyArgumentException(option.value());
        return Optional.of(values);

    }

    private static <T> T parseValue(String value, Function<String, T> valueParser) {
        return valueParser.apply(value);
    }

    private static List<String> getValues(List<String> arguments, int index) {
        int followingFlag = IntStream.range(index + 1, arguments.size())
                .filter(it -> arguments.get(it).startsWith("-"))
                .findFirst().orElse(arguments.size());
        return arguments.subList(index + 1, followingFlag);
    }
}
