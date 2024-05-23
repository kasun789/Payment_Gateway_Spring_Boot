package com.falcon.PaymentGateway.Controller;

import com.falcon.PaymentGateway.Model.PaymentDetail;
import com.falcon.PaymentGateway.Service.PaymentService;
import com.falcon.PaymentGateway.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/paymentProcess")
@CrossOrigin
public class PaymentController {
    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentServ){
        this.paymentService = paymentServ;
    }

    @PostMapping("/checkOut")
    public Map<String,Object> checkOutPayment(@RequestBody PaymentDto paymentDto){
        Map<String,Object> response = new HashMap<>();
        PaymentDetail paymentDetail = new PaymentDetail();
        paymentDetail.setCurrency(paymentDto.getCurrency());
        paymentDetail.setAmount(paymentDto.getAmount());
        paymentDetail.setSystemUserId(1);

        response = paymentService.savePayment(paymentDto.getToken(),paymentDetail);
        return response;
    }
}
