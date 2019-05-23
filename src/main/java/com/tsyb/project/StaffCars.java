package com.tsyb.project;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "staff_cars")
public class StaffCars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private Users user;
    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "car_id")
    private Cars car;
    @Column(name = "car_main")
    private String carMain;

    public StaffCars()
    {
        //default
    }

    public StaffCars(Integer id, Users user, Cars car, String carMain) {
        this.id = id;
        this.user = user;
        this.car = car;
        this.carMain = carMain;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Cars getCar() {
        return car;
    }

    public void setCar(Cars car) {
        this.car = car;
    }

    public String getCarMain() {
        return carMain;
    }

    public void setCarMain(String carMain) {
        this.carMain = carMain;
    }
}