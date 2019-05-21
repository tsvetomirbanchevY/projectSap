package com.tsyb.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    @Query("select f from Invoice f " +
            "join Users u on " +
            "f.user = u.id " +
            "join Trips t on " +
            "t.user = u.id " +
            "where u.id = ?1 " +
            "and t.date = CURRENT_DATE")
    List<Invoice> findAllDaily(int userId);
}
