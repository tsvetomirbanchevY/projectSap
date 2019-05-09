package com.tsyb.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class InvoiceServiceImpl implements InvoiceService {


    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice findById(int id) {
        Optional<Invoice> result = invoiceRepository.findById(id);

        Invoice invoice = null;

        if (result.isPresent()) {
            invoice = result.get();
        } else {
            throw new RuntimeException("Did not find invoice id - " + id);
        }

        return invoice;
    }

    @Override
    public void save(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    @Override
    public void deleteById(int theId) {
        invoiceRepository.deleteById(theId);
    }

}


