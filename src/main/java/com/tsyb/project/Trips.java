package com.tsyb.project;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

@Entity
@Table(name = "trips")
public class Trips {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "startroute")
    private String start;
    @Column(name = "endroute")
    private String end;
    @Column(name = "price")
    private Double price;
    @Column(name = "dateoftrip")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "car_id")
    private Cars car;
    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private Users user;
    @JsonManagedReference
    @ManyToMany(mappedBy = "trips", cascade = {CascadeType.ALL})
    List<Invoice> invoices;

    public Trips()
    {
        //default
    }

    public Trips(Integer id, String start, String end, Double price, Date date, Cars car, Users user, List<Invoice> invoices) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.price = price;
        this.date = date;
        this.car = car;
        this.user = user;
        this.invoices = invoices;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Cars getCar() {
        return car;
    }

    public void setCar(Cars car) {
        this.car = car;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public void addInvoice(Invoice tempInvoice){
        if(invoices == null){
            invoices = new ArrayList<>();
        }

        invoices.add(tempInvoice);
    }
}
