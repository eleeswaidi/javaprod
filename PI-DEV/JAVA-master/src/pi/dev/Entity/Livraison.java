/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev.Entity;

/**
 *
 * @author Ach
 */
public class Livraison {
     private int id;
    private String nameClient;
    private String street;
    private String city;
    private int zipCode;
    private int phoneNum;
    private String email;

    public Livraison(int id, String nameClient, String street, String city, int zipCode, int phoneNum, String email) 
    {
        this.id = id;
        this.nameClient = nameClient;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.phoneNum = phoneNum;
        this.email = email;
    }

     public Livraison(String nameClient, String street, String city, int zipCode, int phoneNum, String email) 
    {
        this.nameClient = nameClient;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
    
    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id= " + id + ", Client Name= " +
                nameClient + ", Street= " + street + ", City= " + city +
                ", Zip Code= " + zipCode + ", Phone Number= " +
                phoneNum + ", Email= " + email + '}';
    }
       
}
