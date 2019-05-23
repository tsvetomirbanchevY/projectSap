package com.tsyb.project;

import java.util.List;

public interface StaffCarsService {

    List<StaffCars> findAll();

    StaffCars findById(int id);

    void save(StaffCars staffcar);

    void deleteById(int id);

    void update(StaffCars staffCar);

}


