package com.java.bill.controller;

import com.java.bill.controller.dto.BillRequestDTO;
import com.java.bill.controller.dto.BillResponseDTO;
import com.java.bill.repository.BillRepository;
import com.java.bill.service.BillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }


    @GetMapping("/{billId}")
    public BillResponseDTO getBill(@PathVariable Long billId){
        return  new BillResponseDTO(billService.getBillById(billId));

    }

    @PostMapping("/")
    public  Long createBill(@RequestBody BillRequestDTO billRequestDTO){
        return billService.createBill(billRequestDTO.getAccountId(), billRequestDTO.getAmount(),
                billRequestDTO.getIsDefault(), billRequestDTO.getOverdraftEnable());
    }

    @PutMapping("/{billId}")
    public BillResponseDTO updateBill(@PathVariable Long billId, @RequestBody BillRequestDTO billRequestDTO){
        return new BillResponseDTO(billService.updateBill(billId, billRequestDTO.getAccountId(), billRequestDTO.getAmount(),
                billRequestDTO.getIsDefault(), billRequestDTO.getOverdraftEnable()));
    }

    @DeleteMapping("/{billId}")
    public BillResponseDTO deleteBill(@PathVariable Long billId){
        return new BillResponseDTO(billService.deleteBill(billId));
    }

    @GetMapping("account/{accountId}")
    public List<BillResponseDTO> getBillsByAccountId(@PathVariable Long accountId){
        return billService.getBillsByAccountId(accountId).stream().map(BillResponseDTO::new).collect(Collectors.toList());
    }
}
