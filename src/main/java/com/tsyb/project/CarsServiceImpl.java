package com.tsyb.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CarsServiceImpl implements CarsService {


    private CarsRepository carsRepository;

    @Autowired
    public CarsServiceImpl(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @Override
    public List<Cars> findAll() {
        return carsRepository.findAll();
    }

    @Override
    public Cars findById(int id) {
        Optional<Cars> result = carsRepository.findById(id);

        Cars car = null;

        if (result.isPresent()) {
            car = result.get();
        } else {
            throw new RuntimeException("Did not find car id - " + id);
        }

        return car;
    }

    @Override
    public void save(Cars car) {
        carsRepository.save(car);
    }

    @Override
    public void deleteById(int id) {
        carsRepository.deleteById(id);
    }

}


