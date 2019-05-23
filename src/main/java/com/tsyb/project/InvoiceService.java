package com.tsyb.project;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface InvoiceService {

    List<Invoice> findAll();

    Invoice findById(int id);

    void save(Invoice invoice);

    void deleteById(int id);

    void update(Invoice invoice);

    List<Invoice> findAllDaily(int userId, Date date);

    List<Invoice> findAllMonthly(int userId);



}


