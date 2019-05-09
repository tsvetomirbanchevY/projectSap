package com.tsyb.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StaffCarsServiceImpl implements StaffCarsService {


    private StaffCarsRepository staffCarsRepository;

    @Autowired
    public StaffCarsServiceImpl(StaffCarsRepository staffCarsRepository) {
        this.staffCarsRepository = staffCarsRepository;
    }

    @Override
    public List<StaffCars> findAll() {
        return staffCarsRepository.findAll();
    }

    @Override
    public StaffCars findById(int id) {
        Optional<StaffCars> result = staffCarsRepository.findById(id);

        StaffCars staffCar = null;

        if (result.isPresent()) {
            staffCar = result.get();
        } else {
            throw new RuntimeException("Did not find staffCars id - " + id);
        }

        return staffCar;
    }

    @Override
    public void save(StaffCars staffCar) {
        staffCarsRepository.save(staffCar);
    }

    @Override
    public void deleteById(int theId) {
        staffCarsRepository.deleteById(theId);
    }

}


