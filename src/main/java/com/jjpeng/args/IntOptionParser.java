package com.jjpeng.args;

class IntOptionParser extends StringOptionParser {

    @Override
    protected Object parseValue(String value) {
        return Integer.parseInt(value);
    }
}
