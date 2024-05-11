package com.falcon.PaymentGateway.Controller;

import com.falcon.PaymentGateway.Model.PaymentDetail;
import com.falcon.PaymentGateway.Service.PaymentService;
import com.falcon.PaymentGateway.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paymentProcess")
public class PaymentController {
    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentServ){
        this.paymentService = paymentServ;
    }

    @PostMapping("/checkOut")
    public String checkOutPayment(@RequestBody PaymentDto paymentDto){
        PaymentDetail paymentDetail = new PaymentDetail();
        paymentDetail.setCurrency(paymentDto.getCurrency());
        paymentDetail.setAmount(paymentDto.getAmount());
        paymentDetail.setSystemUserId(1);
        try{
            paymentService.savePayment(paymentDto.getToken(),paymentDetail);
            return "Payment Successfully Done";
        }
        catch (Exception ex) {
            return "Payment UnSuccessfully Done";
        }
    }
}
