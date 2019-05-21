package com.tsyb.project;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Base64;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "bankaccount")
    private String bankAccount;
    @Column(name = "city")
    private String city;
    @Column(name = "isvalid")
    private boolean isValid;
    @Column(name = "phone")
    private String phone;
    @JsonBackReference
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Roles> roles;
    @JsonManagedReference
    @OneToMany(mappedBy = "userStaff", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Voucher> staffVouchers;
    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Voucher> vouchers;
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private List<StaffCars> staffCars;
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.ALL})
    private List<Trips> trips;
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.ALL})
    private List<Invoice> invoices;
    @Lob
    @Column(name="license")
    private byte[] license;

    public Users() {
        //default
    }

    public Users(Integer id, String userName, String password, String email, String firstName, String lastName, String bankAccount, String city, boolean isValid, String phone, List<Roles> roles, List<Voucher> staffVouchers, List<Voucher> vouchers, List<StaffCars> staffCars, List<Trips> trips, List<Invoice> invoices, byte[] license) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bankAccount = bankAccount;
        this.city = city;
        this.isValid = isValid;
        this.phone = phone;
        this.roles = roles;
        this.staffVouchers = staffVouchers;
        this.vouchers = vouchers;
        this.staffCars = staffCars;
        this.trips = trips;
        this.invoices = invoices;
        this.license = license;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public List<Voucher> getStaffVouchers() {
        return staffVouchers;
    }

    public void setStaffVouchers(List<Voucher> staffVouchers) {
        this.staffVouchers = staffVouchers;
    }

    public List<Voucher> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<Voucher> vouchers) {
        this.vouchers = vouchers;
    }

    public List<StaffCars> getStaffCars() {
        return staffCars;
    }

    public void setStaffCars(List<StaffCars> staffCars) {
        this.staffCars = staffCars;
    }

    public List<Trips> getTrips() {
        return trips;
    }

    public void setTrips(List<Trips> trips) {
        this.trips = trips;
    }

    public byte[] getLicense() {
        return license;
    }

    public void setLicense(byte[] license) {
        this.license = license;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public void addRoles(Roles tempRole) {
        if (roles == null) {
            roles = new ArrayList<>();
        }

        roles.add(tempRole);
    }
    public void addVouchers(Voucher tempVoucher) {
        if (vouchers == null) {
            vouchers = new ArrayList<>();
        }

        vouchers.add(tempVoucher);
    }
    public void addStaffVouchers(Voucher tempStaffVoucher){
        if(vouchers == null){
            vouchers = new ArrayList<>();
        }

        vouchers.add(tempStaffVoucher);
    }

    public void addStaffCars(StaffCars tempStaffTrip){
        if(staffCars == null){
            staffCars = new ArrayList<>();
        }

        staffCars.add(tempStaffTrip);
    }
    public void addTrip(Trips tempTrip){
        if(trips == null){
            trips = new ArrayList<>();
        }

        trips.add(tempTrip);
    }

    public void addInvoice(Invoice tempInvoice){
        if(invoices == null){
            invoices = new ArrayList<>();
        }

        invoices.add(tempInvoice);
    }

    public String generateBase64Image()
    {
       return Base64.getEncoder().encodeToString(getLicense());

    }
}
