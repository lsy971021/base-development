package com.lsy.test;

//import org.junit.jupiter.api.Test;

import java.lang.annotation.Documented;

public class AnnotationTest {
    @Documented
    @interface test1{}

    @interface UnDocumentedAnnotation{
    }

    @test1
    @UnDocumentedAnnotation
    public class UseDocumentedAnnotation{
//        @Test
        public void t() {
            System.err.println(111);
        }
    }
}
