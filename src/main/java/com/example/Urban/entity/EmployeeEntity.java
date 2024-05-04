package com.example.Urban.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity(name="employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHeadquarter() {
        return headquarter;
    }

    public void setHeadquarter(String headquarter) {
        this.headquarter = headquarter;
    }

    @Column(name="image")
    String image;

    @Column(name="name")
    String name;

    @Column(name="email")
    String email;

    @Column(name="phone")
    String phone;

    @Column(name="gender")
    String gender;

    @Column(name="address")
    String address;

    @Column(name="position")
    String position;

    @Column(name="headquarter")
    String headquarter;

    @ToString.Exclude
    @OneToMany(mappedBy = "employee")
    private List<EventEntity> events;

    @ToString.Exclude
    @OneToOne(mappedBy = "employee")
    private AccountEntity account;
    public int Getid(){
        return id;
    }
    public void Setid(int id){
        this.id=id; 
    }

}
