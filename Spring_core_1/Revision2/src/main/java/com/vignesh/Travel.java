package com.vignesh;

public class Travel {
    Vehicle v; // dependency
    int numberOfPerson;

//    public void setV(Vehicle v) {
//        this.v = v;
//    }
//
//    public void setNumberOfPerson(int numberOfPerson) {
//        this.numberOfPerson = numberOfPerson;
//    }

    public void journey(){
        v.go();
        System.out.println("Journey started with" +numberOfPerson+ "people");
    }

    public Travel(Vehicle v, int numberOfPerson) {
        this.v = v;
        this.numberOfPerson = numberOfPerson;
    }
}
