package com.vignesh;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
//        MyBusinessClass mb=ctx.getBean("mb", MyBusinessClass.class);
//        mb.fun1();

//        Travel tr=new Travel();
//        tr.setV(new Car());
//        tr.journey();

//        Travel tr=ctx.getBean("t",Travel.class);
//        tr.journey();

        PresentaionLayer pl=ctx.getBean("pl",PresentaionLayer.class);
        pl.present();
    }

}