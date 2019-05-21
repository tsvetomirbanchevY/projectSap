package com.tsyb.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class InvoiceServiceImpl implements InvoiceService {


    private InvoiceRepository invoiceRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    GenericWebApplicationContext mediator;

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
        Trips trip =(Trips)mediator.getBean("triptemp");
        invoice.setTotalCost(trip.getPrice());
        invoice.setUser((Users)mediator.getBean("usertemp"));
        invoice.setFirm("Sparks");
        invoice.setDateOfInvoice(trip.getDate());
        invoice.addTrip(trip);
       invoiceRepository.save(invoice);
    }



    @Override
    public void update(Invoice invoice) {
        String name = invoice.getUser().getUserName();
        Users user = usersRepository.findByUserName(name);
        invoice.setUser(user);
        invoiceRepository.save(invoice);
    }


    @Override
    public void deleteById(int theId) {
        invoiceRepository.deleteById(theId);
    }


    @Override
    public List<Invoice> findAllDaily(int userId)
    {
        return invoiceRepository.findAllDaily(userId);
    }

}


