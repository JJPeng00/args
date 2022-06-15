package com.jjpeng.args;

class IntOptionParser extends StringOptionParser {

    private IntOptionParser() {
        super(Integer::parseInt);
    }

}
