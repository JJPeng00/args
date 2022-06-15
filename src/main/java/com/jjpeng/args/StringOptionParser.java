package com.jjpeng.args;

import java.util.List;
import java.util.function.Function;

class StringOptionParser implements OptionParser {
    Function<String, Object> valueParser = String::valueOf;

    public StringOptionParser() {
    }

    public StringOptionParser(Function<String, Object> valueParser) {
        this.valueParser = valueParser;
    }

    @Override
    public Object parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value());
        String value = arguments.get(index + 1);
        return valueParser.apply(value);
    }
}
