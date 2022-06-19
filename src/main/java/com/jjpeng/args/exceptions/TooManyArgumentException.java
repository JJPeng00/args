package com.jjpeng.args.exceptions;

public class TooManyArgumentException extends RuntimeException{
    private String option;

    public TooManyArgumentException(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}
