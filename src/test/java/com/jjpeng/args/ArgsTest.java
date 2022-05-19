package com.jjpeng.args;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArgsTest {
    //对这段代码将来会如何被用户使用要有一个构思，作者一般先把它写成一个测试

    //任务分解：先考虑将一个复杂的任务拆分成多个正常的、显而易见的、简单的happy path的任务；在考虑不常见的边边角角的问题和sad path
    //在这个参数解析的例子中我们可以先考虑单个参数的情况再到多个参数的情况之后再处理sad path和默认值的需求
    //Single Option:
    //TODO:  -bool -l
    @Test
    public void should_set_boolean_option_to_true_if_flag_present() {
        BooleanOption option = Args.parse(BooleanOption.class, "-l");
        assertTrue(option.logging());
    }

    static record BooleanOption(@Option("l") boolean logging) {}
    //TODO:  -int -p 8080
    //TODO:  -string -d /usr/logs
    //TODO:  Multi options: -l -p 8080 -d /usr/logs
    //Sad path:
    //TODO:  -bool -l t/ -l t f / -l 20
    //TODO:  -int -p/ -p 8080 / -p /jur
    //TODO:  -string -d/ -d /is/logs /usr/logs
    //Default value
    //TODO: -bool:false
    //TODO:  -int:0
    //TODO:  -string:""


    @Test
    @Disabled
    public void should_example_1() {
        Options options = Args.parse(Options.class, "-l", "-p", "8080", "-d", "/usr/logs");
        assertTrue(options.logging());
        assertEquals(8080, options.port());
        assertEquals("/user/logs", options.directory());
    }

    @Test
    @Disabled
    public void should_example_2() {
        ListOptions options = Args.parse(ListOptions.class, "-g", "this", "is", "a", "list", "-d", "1", "2", "3", "5");
        assertArrayEquals(new String[]{"this", "is", "a", "list"}, options.group());
        assertArrayEquals(new int[]{1, 2, 3, 5}, options.decimals());
    }

    static record Options(@Option("l") boolean logging,@Option("p") int port, @Option("d")String directory){

    }

    static record ListOptions(@Option("g") String[] group, @Option("d") int[] decimals) {

    }
}
