package com.jjpeng.args;

import com.jjpeng.args.exceptions.InsufficientArgumentException;
import com.jjpeng.args.exceptions.TooManyArgumentException;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

class SingleValueOptionParser<T> implements OptionParser<T> {
    Function<String, T> valueParser;
    private T defaultValue;

    public SingleValueOptionParser(Function<String, T> valueParser, T defaultValue) {
        this.valueParser = valueParser;
        this.defaultValue = defaultValue;
    }

    @Override
    public T parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value());
        if (index == -1) return defaultValue;

        List<String> values = getValues(arguments, index);

        if (values.size() < 1) throw new InsufficientArgumentException(option.value());

        if (values.size() > 1) throw new TooManyArgumentException(option.value());

        return valueParser.apply(values.get(0));
    }

    private List<String> getValues(List<String> arguments, int index) {
        int followingFlag = IntStream.range(index + 1, arguments.size())
                .filter(it -> arguments.get(it).startsWith("-"))
                .findFirst().orElse(arguments.size());
        return arguments.subList(index + 1, followingFlag);
    }
}
