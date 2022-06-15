package com.jjpeng.args;

class IntOptionParser extends StringOptionParser {

    public IntOptionParser() {
        super(Integer::parseInt);
    }
}
