package com.jjpeng.args;

public class ArgsTest {
    //对这段代码将来会如何被用户使用要有一个构思，作者一般先把它写成一个测试

    //任务分解：先考虑将一个复杂的任务拆分成多个正常的、显而易见的、简单的happy path的任务；在考虑不常见的边边角角的问题和sad path
    //在这个参数解析的例子中我们可以先考虑单个参数的情况再到多个参数的情况之后再处理sad path和默认值的需求
    //Single Option:
    //TODO:  -bool -l
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
}
