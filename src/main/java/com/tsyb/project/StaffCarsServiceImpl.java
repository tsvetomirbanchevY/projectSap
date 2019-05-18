package com.tsyb.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.util.List;
import java.util.Optional;


@Service
public class StaffCarsServiceImpl implements StaffCarsService {


    private StaffCarsRepository staffCarsRepository;

    @Autowired
    GenericWebApplicationContext mediator;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    CarsRepository carsRepository;

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
        Users staffUser = (Users)mediator.getBean("usertemp");
        staffCar.setUser(staffUser);
        List<StaffCars> userStaffList = staffUser.getStaffCars();
        userStaffList.add(staffCar);
        staffUser.setStaffCars(userStaffList);
        Cars car = carsRepository.findCarsById(staffCar.getCar().getId());
        staffCar.setCar(car);
        List<StaffCars> carStaffList = car.getStaffCars();
        carStaffList.add(staffCar);
        car.setStaffCars(carStaffList);
        carsRepository.save(car);
        usersRepository.save(staffUser);
        staffCarsRepository.save(staffCar);
    }

    @Override
    public void deleteById(int theId) {
        staffCarsRepository.deleteById(theId);
    }

}


