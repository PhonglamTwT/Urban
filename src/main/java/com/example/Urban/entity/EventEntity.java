package com.example.Urban.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@Entity(name="event")
public class EventEntity {
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

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="day")
    private Date day;

    @Column(name="worktime")
    private String worktime;

    @Column(name="workplace")
    private String workplace;

    @Column(name="status")
    private String status;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="employee_id")
    private EmployeeEntity employee;
    public int Getid(){
        return id;
    }
    public void Setid(int id){
        this.id=id; 
    }

}
