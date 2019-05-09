package com.tsyb.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TripsServiceImpl implements TripsService {


    private TripsRepository tripsRepository;

    @Autowired
    public TripsServiceImpl(TripsRepository tripsRepository) {
        this.tripsRepository = tripsRepository;
    }

    @Override
    public List<Trips> findAll() {
        return tripsRepository.findAll();
    }

    @Override
    public Trips findById(int id) {
        Optional<Trips> result = tripsRepository.findById(id);

        Trips trip = null;

        if (result.isPresent()) {
            trip = result.get();
        } else {
            throw new RuntimeException("Did not find trip id - " + id);
        }

        return trip;
    }

    @Override
    public void save(Trips trip) {
        tripsRepository.save(trip);
    }

    @Override
    public void deleteById(int theId) {
        tripsRepository.deleteById(theId);
    }

}


