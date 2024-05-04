package com.falcon.PaymentGateway.Controller;

import com.falcon.PaymentGateway.Model.PaymentDetail;
import com.falcon.PaymentGateway.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paymentProcess")
public class PaymentController {
    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentServ){
        this.paymentService = paymentServ;
    }

    @PostMapping("/checkOut")
    public String checkOutPayment(@RequestParam("token") String token,@RequestParam("amount")  double amount,@RequestParam("systemUserId")  int systemUserId,@RequestParam("currency")  String currency){
        PaymentDetail paymentDetail = new PaymentDetail();
        paymentDetail.setCurrency(currency);
        paymentDetail.setAmount(amount);
        paymentDetail.setSystemUserId(1);
        try{
            paymentService.savePayment(token,paymentDetail);
            return "Payment Successfully Done";
        }
        catch (Exception ex) {
            return "Payment Successfully Done";
        }
    }
}
