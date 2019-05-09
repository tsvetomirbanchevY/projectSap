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
    @Column(name = "city")
    private String city;
    @Column(name = "isavailable")
    private boolean isAvailable;
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

    public Cars(Integer id, String brand, String model, String city, boolean isAvailable, String address, List<Trips> trips, List<StaffCars> staffCars) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.city = city;
        this.isAvailable = isAvailable;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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