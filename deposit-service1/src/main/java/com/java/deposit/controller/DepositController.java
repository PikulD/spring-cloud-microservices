package com.java.deposit.controller;

import com.java.deposit.controller.dto.DepositRequestDTO;
import com.java.deposit.controller.dto.DepositResponseDTO;
import com.java.deposit.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepositController {

    private final DepositService depositService;

    @Autowired
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping("/deposit")
    public DepositResponseDTO deposit(@RequestBody DepositRequestDTO requestDTO){
        return depositService.deposit(requestDTO.getAccountId(), requestDTO.getBillId(), requestDTO.getAmount());
    }

}
