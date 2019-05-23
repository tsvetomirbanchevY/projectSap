package com.tsyb.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {

    @Query("select v from Voucher v " +
            "join Users u on " +
            "v.user = u.id " +
            "where u.id = ?1" )
    List<Invoice> findAllMyVouchers(int userId);
}
