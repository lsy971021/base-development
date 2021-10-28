package com.lsy.oom;

public class Test {

    /**
     * StackOverFlowError
     */
    @org.junit.Test
    public void test() {
        cycle();
    }
    public void cycle(){
        cycle2();
    }
    public void cycle2(){
        cycle();
    }
}
