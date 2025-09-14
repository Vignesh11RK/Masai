package com.vignesh;

public class Bike implements Vehicle{
    public void ride(){
        System.out.println("Bike ride has started");
    }

    @Override
    public void go() {
     ride();
    }
}
