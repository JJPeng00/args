package com.jjpeng.args;

class IntOptionParser extends StringOptionParser {

    private IntOptionParser() {
        super(Integer::parseInt);
    }

    public static OptionParser createIntOptionParser() {
        return new StringOptionParser(Integer::parseInt);
    }
}
