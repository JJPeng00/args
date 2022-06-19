package com.jjpeng.args;

import com.jjpeng.args.exceptions.TooManyArgumentException;

import java.util.List;

class BooleanOptionParser implements OptionParser<Boolean> {

    @Override
    public Boolean parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value());
        if (index + 1 < arguments.size() &&
                !arguments.get(index + 1).startsWith("-")) throw new TooManyArgumentException(option.value());
        return index != -1;
    }
}
