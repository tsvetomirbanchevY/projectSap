package com.tsyb.project;

import java.util.List;

public interface CarsService {

    List<Cars> findAll();

    Cars findById(int id);

    void save(Cars car);

    void deleteById(int id);

    List<Cars> findByIsAvailable(String city);

    Cars findCarsById(int id);


}


