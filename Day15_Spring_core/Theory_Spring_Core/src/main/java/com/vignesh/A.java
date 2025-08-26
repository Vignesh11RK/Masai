package com.vignesh;

public class A {// DEPENDENT
    B b1 =new B();  // DEPENDENCY

    public  void funA(){
        System.out.println("Inside fun A");
        b1.funB();
    }

}
