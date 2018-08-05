package com.kn.demo;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GetDemoTest {


    @Test(description = "字段长度校验")
    public void case1(){
        System.out.println("test1");
    }

    @Test(description = "字段类型校验")
    public void case2(){
        System.out.println("test2");
    }


    @Test(description = "反例异常测试")
    public void case3() throws Exception {
        System.out.println("test3");
        throw new Exception("ceshiceshi");
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
