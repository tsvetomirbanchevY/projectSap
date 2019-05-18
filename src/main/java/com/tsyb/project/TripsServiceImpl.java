package com.tsyb.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.util.List;
import java.util.Optional;


@Service
public class TripsServiceImpl implements TripsService {


    private TripsRepository tripsRepository;

    @Autowired
    private CarsRepository carsRepository;

    @Autowired
    GenericWebApplicationContext mediator;

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
        trip.setUser((Users)mediator.getBean("usertemp"));
        trip.setPrice(10.00);
        Cars car = carsRepository.findCarsById(trip.getCar().getId());
        trip.setStart(car.getAddress());
        trip.setCar(car);
        tripsRepository.save(trip);
        car.setAvailable(false);
        car.setAddress(trip.getEnd());
        car.setBattery(car.getBattery()-20);
        List<Trips> tripList = car.getTrips();
        tripList.add(trip);
        car.setTrips(tripList);
        carsRepository.save(car);
        mediator.registerBean("cartemp", Cars.class, () -> car);

    }

    @Override
    public void deleteById(int theId) {
        tripsRepository.deleteById(theId);
    }

}


