package com.tsyb.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarsRepository extends JpaRepository<Cars, Integer> {
    @Query("select c from Cars c " +
            "join Trips t on " +
            "t.car = c.id " +
            "join Users u on " +
            "t.user = u.id " +
            "where c.isAvailable = true " +
            "and c.address like %?1%")
    List<Cars> findByIsAvailable(String city);

    Cars findCarsById(int id);
}
