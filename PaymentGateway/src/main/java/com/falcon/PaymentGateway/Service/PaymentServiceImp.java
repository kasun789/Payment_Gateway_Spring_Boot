package com.falcon.PaymentGateway.Service;

import com.falcon.PaymentGateway.Model.PaymentDetail;
import com.falcon.PaymentGateway.Repository.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
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
    public Map<String,Object> savePayment(String token, PaymentDetail paymentDetail){
        Stripe.apiKey = secretKey;
        Map<String,Object> params = new HashMap<>();
        Map<String,Object> response = new HashMap<>();

        params.put("amount",(int) paymentDetail.getAmount()*100);
        params.put("currency",paymentDetail.getCurrency());
        params.put("source",token);
        params.put("description","Fee");

        try{
            PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                    .setAmount(Long.valueOf((Integer) params.get("amount") ))
                    .setCurrency("usd")
                    .addPaymentMethodType("card")
                    .build();

            PaymentIntent intent = PaymentIntent.create(createParams);
            response.put("clientSecret", intent.getClientSecret());
            paymentDetail.setPaymentStatus(1);
            paymentRepository.save(paymentDetail);
            return response;
        }
        catch (StripeException ex){
            paymentDetail.setPaymentStatus(1);
            paymentRepository.save(paymentDetail);
            response.put("Error",ex);
            return response;
        }
    }
}
