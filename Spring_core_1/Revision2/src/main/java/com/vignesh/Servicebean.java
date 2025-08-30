package com.vignesh;

public class Servicebean {
    public DAObean dao;


    public void setDao(DAObean dao) {
        this.dao = dao;
    }

    public void calculateInterest(){
        dao.findaccount();
        System.out.println("Interest calculated sucessfully");
    }

}
