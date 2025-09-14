package com.vignesh;

public class PresentaionLayer {
    private Servicebean service;

    public void setService(Servicebean service) {
        this.service = service;
    }

    public void present(){
        service.calculateInterest();
        System.out.println("Presenting the interest value");
    }
}
