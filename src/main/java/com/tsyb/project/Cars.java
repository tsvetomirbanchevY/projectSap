package com.tsyb.project;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "cars")
public class Cars
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "regplate")
    private String regPlate;
    @Column(name = "isavailable")
    private boolean isAvailable;
    @Column(name = "battery")
    private int battery;
    @Column(name = "address")
    private String address;
    @JsonManagedReference
    @OneToMany(mappedBy = "car", cascade = {CascadeType.ALL})
    private List<Trips> trips;
    @JsonManagedReference
    @OneToMany(mappedBy = "car", cascade = {CascadeType.ALL})
    List<StaffCars> staffCars;

    public Cars()
    {
        //default
    }

    public Cars(Integer id, String brand, String model, String regPlate, boolean isAvailable, int battery, String address, List<Trips> trips, List<StaffCars> staffCars) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.regPlate = regPlate;
        this.isAvailable = isAvailable;
        this.battery = battery;
        this.address = address;
        this.trips = trips;
        this.staffCars = staffCars;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegPlate() {
        return regPlate;
    }

    public void setRegPlate(String regPlate) {
        this.regPlate = regPlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Trips> getTrips() {
        return trips;
    }

    public void setTrips(List<Trips> trips) {
        this.trips = trips;
    }

    public List<StaffCars> getStaffCars() {
        return staffCars;
    }

    public void setStaffCars(List<StaffCars> staffCars) {
        this.staffCars = staffCars;
    }

    public void addTrip(Trips tempTrip){
        if(trips == null){
            trips = new ArrayList<>();
        }

        trips.add(tempTrip);
    }
    public void addStaffCars(StaffCars tempStaffTrip){
        if(staffCars == null){
            staffCars = new ArrayList<>();
        }

        staffCars.add(tempStaffTrip);
    }
}