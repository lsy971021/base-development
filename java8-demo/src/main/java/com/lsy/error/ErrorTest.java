package com.lsy.error;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ErrorTest {

    /**
     * StackOverFlowError
     */
    @Test
    public void StackOverflowErrorTest() {
        cycle();
    }
    public void cycle(){
        StackOverflowErrorTest();
    }


    /**
     * OOM
     * VM args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     */
    @Test
    public void oomErrorTest(){
        List<ErrorTest> list = new ArrayList<>();
        while (true) {
            list.add(new ErrorTest());
        }
    }



}
