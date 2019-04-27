package com.test;

import java.util.Random;

//这个例子想说明一下static final 与 final的区别
public class StaticAndFinalTest {
    
    private static Random rand = new Random(47); //47作为随机种子，为的就是产生随机数。
    
    private final int a = rand.nextInt(20);
    
    private static final int B = rand.nextInt(20);
    private static int c = rand.nextInt(20);
    
    public static void main(String[] args) {
        StaticAndFinalTest sf = new StaticAndFinalTest();

        System.out.println("sf : " + "a=" + sf.a);
        System.out.println("sf : " + "B=" + sf.B);
        System.out.println("before change StaticAndFinalTest : " + "c=" + StaticAndFinalTest.c);
        System.out.println("before change sf : " + "c=" + sf.c);
        
        sf.c = 22;
        System.out.println("after change StaticAndFinalTest : " + "c=" + StaticAndFinalTest.c);
        System.out.println("after change sf : " + "c=" + sf.c);
        
        System.out.println("------------------------------");
        StaticAndFinalTest sf1 = new StaticAndFinalTest();
        System.out.println("sf1 : " + "a=" + sf1.a);
        System.out.println("sf1 : " + "B=" + sf1.B);
        
        System.out.println("sf1 : " + "c=" + sf1.c);
        sf1.c = 33;
        System.out.println("after change2 StaticAndFinalTest : " + "c=" + StaticAndFinalTest.c);
        System.out.println("after change2 sf : " + "c=" + sf.c);
    }

}