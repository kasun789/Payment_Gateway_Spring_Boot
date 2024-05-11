package com.falcon.PaymentGateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private String token;
    private double amount;
    private int systemUserId;
    private String currency;
}
