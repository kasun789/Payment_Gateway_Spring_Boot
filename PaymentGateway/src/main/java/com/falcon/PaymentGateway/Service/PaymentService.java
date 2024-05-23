package com.falcon.PaymentGateway.Service;

import com.falcon.PaymentGateway.Model.PaymentDetail;

import java.util.Map;

public interface PaymentService {
    Map<String, Object> savePayment(String token, PaymentDetail paymentDetail);
}
