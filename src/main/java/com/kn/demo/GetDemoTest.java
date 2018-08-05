package com.kn.demo;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GetDemoTest {


    @Test
    public void case1(){
        System.out.println("test1");
    }

    @Test
    public void case2(){
        System.out.println("test2");
    }

    @AfterTest
    public void beforeCase(){
        System.out.println("aftertest");
    }
    @BeforeTest
    public void afterCase1(){


        System.out.println("beforetest");
    }
}
