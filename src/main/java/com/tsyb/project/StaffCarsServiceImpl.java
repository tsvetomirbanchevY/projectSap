package com.tsyb.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        String userName = staffCar.getUser().getUserName();
        Users user =  usersRepository.findByUserName(userName);
        staffCar.setUser(user);
        user.addStaffCars(staffCar);
        Cars car = carsRepository.findCarsById(staffCar.getCar().getId());
        staffCar.setCar(car);
        car.addStaffCars(staffCar);
        carsRepository.save(car);
        usersRepository.save(user);
        staffCarsRepository.save(staffCar);
    }

    @Override
    public void update(StaffCars staffCar) {
        int idCar = staffCar.getCar().getId();
        String userName = staffCar.getUser().getUserName();
        staffCar.setCar(carsRepository.findCarsById(idCar));
        staffCar.setUser(usersRepository.findByUserName(userName));
        staffCarsRepository.save(staffCar);
    }

    @Override
    public void deleteById(int theId) {
        staffCarsRepository.deleteById(theId);
    }

}


