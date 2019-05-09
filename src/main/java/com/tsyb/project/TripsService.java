package com.tsyb.project;

import java.util.List;

public interface TripsService {

    List<Trips> findAll();

    Trips findById(int id);

    void save(Trips trip);

    void deleteById(int id);

}



