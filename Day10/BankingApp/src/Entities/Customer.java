package Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Customer {
    private String customerID;
    private String name;
    private String phone ;
    private String email;
    private String password;
    private LocalDate date ;

    public Customer(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Customer(String customerID, String name, String phone, String email, String password, LocalDate date) {
        this.customerID = customerID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.date = date;
    }

    public Customer(){
   super();
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", date=" + date +
                '}';
    }


    //Hashcode for input validation


    @Override
    public boolean equals(Object obj) {
        if(obj ==null){
            return false;
        }

        if(this == obj){
            return true;
        }

        Customer customer=(Customer)obj;
        return Objects.equals(customerID,customer.customerID) && Objects.equals(email,customer.email);
    }

//    @Override
    public int hashcode(){
        return Objects.hash(customerID,email);
    }

}
