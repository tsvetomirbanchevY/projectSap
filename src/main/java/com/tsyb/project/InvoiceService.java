package com.tsyb.project;

import java.util.List;

public interface InvoiceService {

    List<Invoice> findAll();

    Invoice findById(int id);

    void save(Invoice invoice);

    void deleteById(int id);

}


