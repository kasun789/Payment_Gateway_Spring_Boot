package com.falcon.PaymentGateway.Service;

import com.falcon.PaymentGateway.Model.PaymentDetail;

public interface PaymentService {
    void savePayment(String token,PaymentDetail paymentDetail);
}
