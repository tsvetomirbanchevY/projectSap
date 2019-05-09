package com.tsyb.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class VoucherServiceImpl implements VoucherService {


    private VoucherRepository voucherRepository;

    @Autowired
    public VoucherServiceImpl(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    @Override
    public List<Voucher> findAll() {
        return voucherRepository.findAll();
    }

    @Override
    public Voucher findById(int id) {
        Optional<Voucher> result = voucherRepository.findById(id);

        Voucher voucher = null;

        if (result.isPresent()) {
            voucher = result.get();
        } else {
            throw new RuntimeException("Did not find voucher id - " + id);
        }

        return voucher;
    }

    @Override
    public void save(Voucher voucher) {
        voucherRepository.save(voucher);
    }

    @Override
    public void deleteById(int theId) {
        voucherRepository.deleteById(theId);
    }

}


