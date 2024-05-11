package com.falcon.PaymentGateway.Service;

import com.falcon.PaymentGateway.Model.PaymentDetail;
import com.falcon.PaymentGateway.Repository.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class PaymentServiceImp implements PaymentService{
    private PaymentRepository paymentRepository;
    @Value("${stripe.secretKey}")
    private String secretKey;
    @Autowired
    public PaymentServiceImp(PaymentRepository paymentRepo){
        this.paymentRepository = paymentRepo;
    }

    @Override
    public void savePayment(String token, PaymentDetail paymentDetail){
        Stripe.apiKey = secretKey;
        Map<String,Object> params = new HashMap<>();
        params.put("amount",(int) paymentDetail.getAmount()*100);
        params.put("currency",paymentDetail.getCurrency());
        params.put("source",token);
        params.put("description","Fee");

        try{
            Charge charge = Charge.create(params);
            paymentDetail.setPaymentStatus(1);
            paymentRepository.save(paymentDetail);
        }
        catch (StripeException ex){
            paymentDetail.setPaymentStatus(1);
            paymentRepository.save(paymentDetail);
        }
    }
}
