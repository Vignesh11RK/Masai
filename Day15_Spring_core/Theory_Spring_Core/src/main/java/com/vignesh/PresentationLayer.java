package com.vignesh;

public class PresentationLayer {

    ServiceLayer service;

    public void setService(ServiceLayer service) {
        this.service = service;
    }

    public void present(){
        service.service();
        System.out.println("Presentation");
    }


}