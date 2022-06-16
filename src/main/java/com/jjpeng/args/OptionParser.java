package com.jjpeng.args;

import java.util.List;

interface OptionParser<T> {
    T parse(List<String> arguments, Option option);
}
