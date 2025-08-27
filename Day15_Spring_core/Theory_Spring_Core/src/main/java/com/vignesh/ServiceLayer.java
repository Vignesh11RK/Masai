package com.vignesh;

public class ServiceLayer {
    DaoBean dao;

    public void setDao(DaoBean dao) {
        this.dao = dao;
    }

    public void service(){
        dao.fetch();
        System.out.println("Service Layer fetched");
    }
}
