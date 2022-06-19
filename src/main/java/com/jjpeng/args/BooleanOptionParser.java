package com.jjpeng.args;

import com.jjpeng.args.exceptions.TooManyArgumentException;

import java.util.List;

class BooleanOptionParser implements OptionParser<Boolean> {

    @Override
    public Boolean parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value());

        List<String> values = SingleValueOptionParser.getValues(arguments, index);

        if (values.size() > 0) throw new TooManyArgumentException(option.value());

        return index != -1;
    }
}
