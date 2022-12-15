package com.java.bill.service;

import com.java.bill.entity.Bill;
import com.java.bill.exception.BillNotFoundException;
import com.java.bill.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class BillService {

    private final BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Bill getBillById(Long billId){
        return billRepository.findById(billId)
                .orElseThrow(()-> new BillNotFoundException("Unable to find bill with id: " + billId));
    }

    public Long createBill(Long accountId, BigDecimal amount, Boolean isDefault, Boolean overdraftEnable){
        Bill bill = new Bill(accountId,amount, isDefault, OffsetDateTime.now(),overdraftEnable);
        return billRepository.save(bill).getBillId();
    }

    public Bill updateBill(Long billId, Long accountId, BigDecimal amount, Boolean isDefault, Boolean overdraftEnable){
        Bill bill = new Bill(accountId, amount, isDefault, overdraftEnable);
        bill.setBillId(billId);
        return billRepository.save(bill);
    }

    public Bill deleteBill(Long billId){
        Bill deleteBill = getBillById(billId);
        billRepository.deleteById(billId);
        return deleteBill;
    }

    public List<Bill> getBillsByAccountId(Long accountId){
        return billRepository.getBillByAccountId(accountId);
    }
}
